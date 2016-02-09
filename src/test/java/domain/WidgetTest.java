package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Radchuk on 08.02.2016.
 */
public class WidgetTest {

    @Test
    public void testCreateWidgetWithID(){
        Widget widget = new Widget("test_id", "test_datasheet_id", 11, "Widget Content");
        assertNotNull(widget);
        assertEquals(widget.getId(),"test_id");
        assertEquals(widget.getDataSheetId(),"test_datasheet_id");
        assertEquals(widget.getSequenceNumber(),11);
        assertEquals(widget.getContent(),"Widget Content");

    }

    @Test
    public void  testCreateWidgetWithoutID(){
        Widget widget = new Widget("test_datasheet_id", 11, "Widget Content");
        assertNotNull(widget);
        assertNull(widget.getId());
        assertEquals(widget.getDataSheetId(),"test_datasheet_id");
        assertEquals(widget.getSequenceNumber(),11);
        assertEquals(widget.getContent(),"Widget Content");
    }
}