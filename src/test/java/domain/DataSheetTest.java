package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class DataSheetTest {

    @Test
    public void testCreateDataSheetWithoutID(){
        DataSheet dataSheet  = new DataSheet("test_user_id","Test Title");
        assertNotNull(dataSheet);
        assertNull(dataSheet.getId());
        assertEquals(dataSheet.getUserId(),"test_user_id");
        assertEquals(dataSheet.getDataSheetTitle(),"Test Title");
    }

    @Test
    public void  testCreateDataSheetWithID(){

        List<Widget> testWidgetList = new ArrayList<Widget>();
        DataSheet dataSheet  = new DataSheet("test_id","test_user_id","Test Title");
        assertNotNull(dataSheet);
        assertEquals(dataSheet.getId(), "test_id");
        assertEquals(dataSheet.getUserId(), "test_user_id");
        assertEquals(dataSheet.getDataSheetTitle(),"Test Title");
    }
}