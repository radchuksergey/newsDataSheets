package domain;

/**
 * Created by sergey on 25.01.2016.
 */
public class Widget {
    private String id ;
    private String dataSheetId;
    private int sequenceNumber;
    private String content;

    public Widget(String dataSheetId, int sequenceNumber, String content) {
        this.id = null;
        this.dataSheetId = dataSheetId;
        this.sequenceNumber = sequenceNumber;
        this.content = content;
    }

    public Widget(String id, String dataSheetId, int sequenceNumber, String content) {
        this.id = id;
        this.dataSheetId = dataSheetId;
        this.sequenceNumber = sequenceNumber;
        this.content = content;
    }

    public Widget(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataSheetId() {
        return dataSheetId;
    }

    public void setDataSheetId(String dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
