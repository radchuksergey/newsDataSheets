package database.mongodb;

import com.mongodb.MongoWriteException;
import database.DAO.DBException;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void cleanDB() throws DBException {
        DBCleaner.cleanDB();
    }

    @Test
    public void testCreateDataSheet() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();

        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        DataSheet dataSheetFromDb = dataSheetDAO.createDataSheet(dataSheet);
        assertEquals(dataSheet.getDataSheetTitle(), dataSheetFromDb.getDataSheetTitle());
        assertEquals(dataSheet.getUserId(), dataSheetFromDb.getUserId());
        assertNotEquals(dataSheet.getId(), dataSheetFromDb.getId());
    }

    @Test
    public void testGetDataSheetsByUser() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
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
    public void testGetdataSheetById() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
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
    public void testCreateDataSheet_Twin() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
        thrown.expect(MongoWriteException.class);
        User user = new User("test_user","test password","test salt","USER");
        user  = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId()," first test datasheet title");
        dataSheet =  dataSheetDAO.createDataSheet(dataSheet);
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);

    }

    @Test
    public void testDeleteDataSheet() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
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
    public void testUpdateDataSheet() throws DBException {
        UserDAO userDAO = new UserDAOImpl();
        DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
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