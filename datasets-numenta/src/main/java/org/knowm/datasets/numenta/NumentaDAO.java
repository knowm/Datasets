package org.knowm.datasets.numenta;

import java.util.ArrayList;
import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

public class NumentaDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    // / Needs to point to Google drive
    String dataFileID = "0B5VXh50WzAw2dWYzdnI0NFFISGs";
    String propsFileID = "0B5VXh50WzAw2T1gtV0dBWE1mLUE";
    String scriptFileID = "0B5VXh50WzAw2TjBlZ1REUWprTmM";
    init("DB_NUMENTA", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, false);
  }

  //
  // public static void createTable(String tableName) {
  //
  //    // @formatter:off
  //    String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (`series` VARCHAR(200) NOT NULL,`timestamp` BIGINT NOT NULL, `value` double NOT NULL, `label` TINYINT NOT NULL, PRIMARY KEY (series,timestamp)) ENGINE=MyISAM DEFAULT CHARSET=utf8;";
  //    // @formatter:on
  // Yank.execute(CREATE_TABLE_SQL, null);
  // }

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
    String SELECT_ALL_SQL = "SELECT * FROM NUMENTA WHERE series = ?";
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
