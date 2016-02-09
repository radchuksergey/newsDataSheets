package database.mongodb;

import database.DAO.DataSheetDAO;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import domain.DataSheet;
import domain.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public class DataSheetDAOImpl implements DataSheetDAO{
    private final String COLLECTION_NAME = "datasheet";
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    @Override
    public DataSheet getDatasheetById(String id) throws IllegalArgumentException{
        Document whereQuery = new Document("_id", new ObjectId(id));
        Document resultRecord = (Document) databaseConnector.getCollectionByName(COLLECTION_NAME).find(whereQuery).first();
        if (resultRecord != null) return  new DataSheet(resultRecord.get("_id").toString(), resultRecord.getString("userId"), resultRecord.getString("datasheetTitle"));
        else return  null;
    }

    @Override
    public DataSheet createDataSheet(DataSheet dataSheet) throws MongoWriteException,IllegalArgumentException {
        Document inserQuery = new Document();
        inserQuery.put("userId",dataSheet.getUserId());
        inserQuery.put("datasheetTitle",dataSheet.getDataSheetTitle());
        databaseConnector.getCollectionByName(COLLECTION_NAME).insertOne(inserQuery);
        Document insertedRecord = (Document) databaseConnector.getCollectionByName(COLLECTION_NAME).find(inserQuery).first();
        DataSheet insertedDataSheet = new DataSheet(insertedRecord.get("_id").toString(), insertedRecord.getString("userId"), insertedRecord.getString("datasheetTitle"));
        return insertedDataSheet;
    }

    @Override
    public void deleteDataSheet(DataSheet dataSheet)throws MongoWriteException,IllegalArgumentException {
        Document whereQuery = new Document("_id", new ObjectId(dataSheet.getId()));
        databaseConnector.getCollectionByName(COLLECTION_NAME).deleteOne(whereQuery);

    }

    @Override
    public void updateDataSheet(DataSheet dataSheet)throws MongoWriteException,IllegalArgumentException {
        Document whereQuery = new Document("_id", new ObjectId(dataSheet.getId()));
        Document setForUpdate = new Document("datasheetTitle",dataSheet.getDataSheetTitle());
        databaseConnector.getCollectionByName(COLLECTION_NAME).updateOne(whereQuery, new Document("$set",setForUpdate));
    }

    @Override
    public List<DataSheet> getDataSheetsByUser(User user) {

        List<DataSheet> dataSheets = new ArrayList<DataSheet>();
        Document whereQuery = new Document("userId", user.getId());
        FindIterable<Document> queryResult = databaseConnector.getCollectionByName(COLLECTION_NAME).find(whereQuery);
        for (Document document : queryResult){
            dataSheets.add(new DataSheet(document.get("_id").toString(), document.getString("userId"), document.getString("datasheetTitle")));
        }
        return dataSheets;
    }
}
