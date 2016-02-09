package database.mongodb;

import database.DAO.DataSheetDAO;
import database.DAO.UserDAO;
import database.DAO.WidgetDAO;
import domain.DataSheet;
import domain.User;
import domain.Widget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class WidgetDAOImplTest {
    private UserDAO userDAO = new UserDAOImpl();
    private DataSheetDAO dataSheetDAO = new DataSheetDAOImpl();
    private WidgetDAO widgetDAO = new WidgetDAOImpl();

    @Before
    public void cleanDb(){
        DBCleaner.cleanDB();
    }


    @Test
    public void testCreateWidget() throws Exception {
        User user = new User("test user","test password","test salt","USER");
        user = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);
        Widget widget = new Widget(dataSheet.getId(),1,"test content");
        Widget createdWidgetFromDb = widgetDAO.createWidget(widget);
        assertEquals(widget.getContent(), createdWidgetFromDb.getContent());
        assertEquals(widget.getDataSheetId(), createdWidgetFromDb.getDataSheetId());
        assertNotEquals(widget.getId(), createdWidgetFromDb.getId());

    }

    @Test
    public void testGetWidgetById() throws Exception {
        User user = new User("test user","test password","test salt","USER");
        user = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);
        Widget widget = new Widget(dataSheet.getId(),1,"test content");
        widget = widgetDAO.createWidget(widget);
        Widget widgetFromDb = widgetDAO.getWidgetById(widget.getId());
        assertEquals(widget.getContent(), widgetFromDb.getContent());
        assertEquals(widget.getDataSheetId(), widgetFromDb.getDataSheetId());
        assertEquals(widget.getId(), widgetFromDb.getId());
    }

    @Test
    public void testUpdateWidget() throws Exception {
        User user = new User("test user","test password","test salt","USER");
        user = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);
        Widget widget = new Widget(dataSheet.getId(),1,"test content");
        widget = widgetDAO.createWidget(widget);
        widget.setContent("updated content");
        widgetDAO.updateWidget(widget);
        Widget widgetFromDb = widgetDAO.getWidgetById(widget.getId());
        assertEquals(widget.getContent(), widgetFromDb.getContent());
        assertEquals(widget.getDataSheetId(), widgetFromDb.getDataSheetId());
        assertEquals(widget.getId(), widgetFromDb.getId());
    }

    @Test
    public void testDeleteWidget() throws Exception {
        User user = new User("test user","test password","test salt","USER");
        user = userDAO.createUser(user);
        DataSheet dataSheet = new DataSheet(user.getId(),"test datasheet title");
        dataSheet = dataSheetDAO.createDataSheet(dataSheet);
        Widget widget = new Widget(dataSheet.getId(),1,"test content");
        widget = widgetDAO.createWidget(widget);
        widgetDAO.deleteWidget(widget);
        Widget widgetFromDb = widgetDAO.getWidgetById(widget.getId());
        assertNull(widgetFromDb);
    }

    @Test
    public void testGetWidgetsListByDataSheet() throws Exception {

    }

}