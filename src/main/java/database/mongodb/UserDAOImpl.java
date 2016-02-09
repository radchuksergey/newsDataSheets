package database.mongodb;

import database.DAO.UserDAO;
import com.mongodb.client.MongoCollection;
import domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by sergey on 31.01.2016.
 */
public class UserDAOImpl implements UserDAO {
    private static final String COLLECTION_NAME = "user";
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    @Override
    public User getUserByID(String userID) {
        MongoCollection collection  = databaseConnector.getCollectionByName(COLLECTION_NAME);
        Document recordFromDb = (Document) collection.find(new Document("_id", new ObjectId(userID))).first();
        if (recordFromDb != null) {
            return new User(recordFromDb.get("_id").toString(), recordFromDb.getString("userName"), recordFromDb.getString("encryptedPassword"), recordFromDb.getString("salt"), recordFromDb.getString("userType"));
        }
        else return null;
    }

    @Override
    public User createUser(User user) {
        MongoCollection collection = databaseConnector.getCollectionByName(COLLECTION_NAME);
        Document document = new Document();
        document.put("userName",user.getUserName());
        document.put("encryptedPassword",user.getEncryptedPassword());
        document.put("salt", user.getSalt());
        document.put("userType", user.getUserType().toString());
        collection.insertOne(document);
        Document recordFromDB = (Document) collection.find(document).first();
        User createdUser =  new User(recordFromDB.get("_id").toString(),recordFromDB.getString("userName"), recordFromDB.getString("encryptedPassword"),recordFromDB.getString("salt"), recordFromDB.getString("userType"));
        return createdUser;
    }

    @Override
    public void deleteUser(String userID) {
        MongoCollection collection = databaseConnector.getCollectionByName(COLLECTION_NAME);
        collection.findOneAndDelete(new Document("_id", new ObjectId(userID)));
    }

    @Override
    public void deleteUser(User user) {
        deleteUser(user.getId());
    }

    @Override
    public void updateUser(User user) {
        MongoCollection collection = databaseConnector.getCollectionByName(COLLECTION_NAME);
        Document setForUpdate = new Document();
        setForUpdate.put("encryptedPassword",user.getEncryptedPassword());
        setForUpdate.put("salt", user.getSalt());
        setForUpdate.put("userType", user.getUserType().toString());
        Document whereQuery = new Document("_id", new ObjectId(user.getId()));
        collection.updateOne(whereQuery,new Document("$set", setForUpdate));
    }
}
