package database.mongodb;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class DatabaseConnectorTest {

    @Before
    public  void cleanDb(){
        DBCleaner.cleanDB();
    }


    @Test
    public void testGetCollectionByName(){
        DatabaseConnector dao = new DatabaseConnector();
        MongoCollection collection = dao.getCollectionByName("user");
        assertNotNull(collection);
        Document document = new Document("param","value");
        collection.insertOne(document);
        Document documentFromCollection = (Document) collection.find().first();
        assertEquals(documentFromCollection.get("param"),"value");
        collection.findOneAndDelete(document);
        documentFromCollection = (Document) collection.find().first();
        assertNull(documentFromCollection);

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetCollectionByName_Incorrect(){
        DatabaseConnector dao = new DatabaseConnector();
        thrown.expect(IllegalArgumentException.class);
        dao.getCollectionByName("undefined test collection");

    }

}