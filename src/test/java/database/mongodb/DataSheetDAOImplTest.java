package database.mongodb;

import com.mongodb.MongoWriteException;
import database.DAO.DataSheetDAO;
import database.DAO.UserDAO;
import domain.DataSheet;
import domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class DataSheetDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();
    private DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void cleanDB(){
        DBCleaner.cleanDB();
    }

    @Test
    public void testCreateDataSheet(){
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        DataSheet dataSheetFromDb = dataSheetDAO.createDataSheet(dataSheet);
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetFromDb.getDataSheetTitle());
        assertEquals(dataSheet.getUserId(), dataSheetFromDb.getUserId());
        assertNotEquals(dataSheet.getId(), dataSheetFromDb.getId());
    }

    @Test
    public void testGetDataSheetsByUser(){
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        List<DataSheet> dataSheets = dataSheetDAO.getDataSheetsByUser(user);
        assertEquals(dataSheets.size(), 1);
        DataSheet dataSheetFromList = dataSheets.get(0);
        assertEquals(dataSheet.getId(), dataSheetFromList.getId());
        assertEquals(dataSheet.getUserId(), dataSheetFromList.getUserId());
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetFromList.getDataSheetTitle());
        DataSheet secondDataSheet = new DataSheet(user.getId(),"second test datasheet title");
        secondDataSheet = dataSheetDAO.createDataSheet(secondDataSheet);
        dataSheets = dataSheetDAO.getDataSheetsByUser(user);
        assertEquals(dataSheets.size(),2);
    }



    @Test
    public void testGetdataSheetById(){
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        DataSheet dataSheetDromDB = dataSheetDAO.getDatasheetById(dataSheet.getId());
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetDromDB.getDataSheetTitle());
        assertEquals(dataSheet.getUserId(),dataSheetDromDB.getUserId());
        assertEquals(dataSheet.getId(), dataSheetDromDB.getId());
    }

    @Test
    public void testCreateDataSheet_Twin(){
        thrown.expect(MongoWriteException.class);
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);

    }

    @Test
    public void testDeleteDataSheet(){
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        DataSheet dataSheetDromDB = dataSheetDAO.getDatasheetById(dataSheet.getId());
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetDromDB.getDataSheetTitle());
        assertEquals(dataSheet.getUserId(),dataSheetDromDB.getUserId());
        assertEquals(dataSheet.getId(), dataSheetDromDB.getId());
        dataSheetDAO.deleteDataSheet(dataSheet);
        dataSheetDromDB = dataSheetDAO.getDatasheetById(dataSheet.getId());
        assertNull(dataSheetDromDB);

    }

    @Test
    public void testUpdateDataSheet(){
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        dataSheet.setDataSheetTitle(" updated test datasheet title");
        dataSheetDAO.updateDataSheet(dataSheet);
        DataSheet dataSheetDromDB = dataSheetDAO.getDatasheetById(dataSheet.getId());
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetDromDB.getDataSheetTitle());
        assertEquals(dataSheet.getUserId(),dataSheetDromDB.getUserId());
        assertEquals(dataSheet.getId(), dataSheetDromDB.getId());

    }

}