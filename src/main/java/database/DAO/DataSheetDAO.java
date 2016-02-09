package database.DAO;

import domain.DataSheet;
import domain.User;

import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public interface DataSheetDAO {
     DataSheet getDatasheetById(String  id) throws DBException;
     DataSheet createDataSheet(DataSheet dataSheet) throws DBException;
     void deleteDataSheet(DataSheet dataSheet) throws DBException;
     void updateDataSheet(DataSheet dataSheet) throws DBException;
     List<DataSheet> getDataSheetsByUser(User user) throws DBException;

}
