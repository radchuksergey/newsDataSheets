package database.mongodb;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 02.02.2016.
 */
public class DBCleaner {
    private static DatabaseConnector dao = new DatabaseConnector();
    private static List<String> collectionNames = new ArrayList<String>();
    static{
        collectionNames.add("user");
        collectionNames.add("widget");
        collectionNames.add("datasheet");
    }
    public static void cleanDB(){
        for(String collectionName : collectionNames){
            dao.getCollectionByName(collectionName).deleteMany(new Document());
        }
    }
}
