package database.DAO;

import domain.DataSheet;
import domain.Widget;

import java.util.List;

/**
 * Created by sergey on 31.01.2016.
 */
public interface WidgetDAO {
    Widget createWidget(Widget widget);
    Widget getWidgetById(String widgetId);
    void updateWidget(Widget widget);
    void deleteWidget(Widget widget);
    List<Widget> getWidgetsListByDataSheet(DataSheet dataSheet);
}
