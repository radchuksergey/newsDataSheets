package database.mongodb;

import database.DAO.DBException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 02.02.2016.
 */
public class DBCleaner {
    private static DatabaseConnector dao;
    private static List<String> collectionNames = new ArrayList<String>();
    static{
        collectionNames.add("user");
        collectionNames.add("widget");
        collectionNames.add("datasheet");
        try{
            dao = new DatabaseConnector();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void cleanDB() throws DBException {
        for(String collectionName : collectionNames){
            dao.getCollectionByName(collectionName).deleteMany(new Document());
        }
    }
}
