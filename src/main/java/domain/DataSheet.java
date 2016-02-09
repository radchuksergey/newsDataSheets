package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 25.01.2016.
 */
public class DataSheet {
    private String id;
    private String userId;
    private String dataSheetTitle;



    public DataSheet( String userId, String dataSheetTitle) {
        this.id = null;
        this.userId = userId;
        this.dataSheetTitle = dataSheetTitle;
    }

    public DataSheet(String id, String userId, String dataSheetTitle) {
        this.id = id;
        this.userId = userId;
        this.dataSheetTitle = dataSheetTitle;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataSheetTitle() {
        return dataSheetTitle;
    }

    public void setDataSheetTitle(String dataSheetTitle) {
        this.dataSheetTitle = dataSheetTitle;
    }


}
