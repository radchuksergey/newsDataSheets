package database.mongodb;

import database.DAO.WidgetDAO;
import com.mongodb.client.FindIterable;
import domain.DataSheet;
import domain.Widget;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public class WidgetDAOImpl implements WidgetDAO {
    private final String COLLECTION_NAME = "widget";
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    @Override

    public Widget createWidget(Widget widget) {
        Document newWidgetRecord = new Document();
        newWidgetRecord.put("datasheetId",widget.getDataSheetId());
        newWidgetRecord.put("content",widget.getContent());
        newWidgetRecord.put("sequenceNumber", widget.getSequenceNumber());
        databaseConnector.getCollectionByName(COLLECTION_NAME).insertOne(newWidgetRecord);
        Document createdWidgetDoc = (Document) databaseConnector.getCollectionByName(COLLECTION_NAME).find(newWidgetRecord).first();
        return new Widget(createdWidgetDoc.get("_id").toString(),
                createdWidgetDoc.getString("datasheetId"),
                createdWidgetDoc.getInteger("sequenceNumber"),
                createdWidgetDoc.getString("content"));

    }

    @Override
    public Widget getWidgetById(String widgetId) {
        Document whereQuery = new Document("_id", new ObjectId(widgetId));
        Document resultDoc = (Document) databaseConnector.getCollectionByName(COLLECTION_NAME).find(whereQuery).first();
        if (resultDoc != null) return  new Widget(resultDoc.get("_id").toString(),
                resultDoc.getString("datasheetId"),
                resultDoc.getInteger("sequenceNumber"),
                resultDoc.getString("content"));
        else return  null;
    }

    @Override
    public void updateWidget(Widget widget) {
        Document whereQuery = new Document("_id", new ObjectId(widget.getId()));
        Document setForUpdate = new Document();
        setForUpdate.put("sequenceNumber", widget.getSequenceNumber());
        setForUpdate.put("content",widget.getContent());
        databaseConnector.getCollectionByName(COLLECTION_NAME).updateOne(whereQuery, new Document("$set", setForUpdate));

    }

    @Override
    public void deleteWidget(Widget widget) {
        Document whereQuery = new Document("_id", new ObjectId(widget.getId()));
        databaseConnector.getCollectionByName(COLLECTION_NAME).deleteOne(whereQuery);

    }

    @Override
    public List<Widget> getWidgetsListByDataSheet(DataSheet dataSheet) {
        Document whereQuery = new Document("datasheetId", new ObjectId(dataSheet.getId()));
        List<Widget> widgets = new ArrayList<Widget>();
        FindIterable<Document> findResult = databaseConnector.getCollectionByName(COLLECTION_NAME).find(whereQuery);
        for(Document widgetRecord : findResult){
            widgets.add(new Widget(widgetRecord.get("_id").toString(),
                    widgetRecord.getString("datasheetId"),
                    widgetRecord.getInteger("sequenceNumber"),
                    widgetRecord.getString("content")));
        }
        return  widgets;
    }
}
