package database.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.DAO.DBException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by sergey on 31.01.2016.
 */
public class DatabaseConnector {
    private static final String DB_CONFIG_FILE = "mongodb.properties";

    private String dbHost = "";
    private String dbPort = "";
    private  String dbName = "";
    private String dbUser = "";
    private  String dbPwd = "";
    private MongoDatabase database;

    private void initDatabaseConnectionProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DatabaseConnector.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
            dbHost = properties.getProperty("dbHost");
            dbPort = properties.getProperty("dbPort");
            dbName = properties.getProperty("dbName");
            dbUser = properties.getProperty("dbUser");
            dbPwd = properties.getProperty("dbPwd");
        } catch (IOException e){
            System.out.println("Exception while reading configuration from file = " + DB_CONFIG_FILE);
            e.printStackTrace();
        }
    }

    private void setDataBase() throws DBException {
        ServerAddress serverAddress = new ServerAddress(dbHost,Integer.valueOf(dbPort));
        MongoCredential credential = MongoCredential.createCredential(dbUser,dbName,dbPwd.toCharArray());
        try {
            MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
            this.database = mongoClient.getDatabase(dbName);
        } catch (MongoException e) {
            throw new DBException(e.getMessage());
        }

    }

    public MongoCollection getCollectionByName(String collectionName) throws DBException {
        boolean found = false;
        for (String name : database.listCollectionNames()){
            found = name.equals(collectionName);
            if (found) break;
        }
        if(found) return database.getCollection(collectionName);
        else throw new DBException(collectionName + " doesn't exist into database!");
    }

    public DatabaseConnector() throws DBException {
        initDatabaseConnectionProperties();
        setDataBase();
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }
}
