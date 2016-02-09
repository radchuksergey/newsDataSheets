package database.DAO;

import domain.DataSheet;
import domain.Widget;

import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public interface WidgetDAO {
    Widget createWidget(Widget widget) throws DBException;
    Widget getWidgetById(String widgetId) throws DBException;
    void updateWidget(Widget widget) throws DBException;
    void deleteWidget(Widget widget) throws DBException;
    List<Widget> getWidgetsListByDataSheet(DataSheet dataSheet) throws DBException;
}
