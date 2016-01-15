package org.knowm.datasets.numenta;

import java.util.ArrayList;
import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

public class NumentaDAO extends DatasetsDAO {

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static void dropTable() {

    String DROP_TABLE = "DROP TABLE IF EXISTS NUMENTA";
    Yank.execute(DROP_TABLE, null);
  }

  public static void insertSeriesPoint(SeriesPoint point) {

    Object[] params = new Object[] {

    // @formatter:off
        point.getId(),
        point.getSeries(),
        point.getTimestamp(),
        point.getValue(),
        point.getLabel()
        // @formatter:on
        };
    String INSERT = "INSERT INTO NUMENTA (id, series, timestamp, value, label) VALUES (?, ?, ?, ?, ?)";
    Yank.execute(INSERT, params);
  }

  public static List<SeriesPoint> selectSeries(String series) {

    Object[] params = new Object[] { series };
    String SELECT_ALL_SQL = "SELECT * FROM NUMENTA WHERE series = ? ORDER BY timestamp ASC";
    return Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, params);
  }

  public static List<String> selectSeriesNames() {

    String SELECT_ALL_SQL = "SELECT DISTINCT SERIES FROM NUMENTA";
    List<SeriesPoint> uniqueSeries = Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, null);
    List<String> names = new ArrayList<String>();
    for (SeriesPoint p : uniqueSeries) {
      names.add(p.getSeries());
    }
    return names;
  }

  public static List<SeriesPoint> selectAll() {

    String SELECT_ALL_SQL = "SELECT * FROM NUMENTA";
    return Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, null);
  }
}
