package database.DAO;

import domain.DataSheet;
import domain.User;

import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public interface DataSheetDAO {
     DataSheet getDatasheetById(String  id);
     DataSheet createDataSheet(DataSheet dataSheet);
     void deleteDataSheet(DataSheet dataSheet);
     void updateDataSheet(DataSheet dataSheet);
     List<DataSheet> getDataSheetsByUser(User user);

}
