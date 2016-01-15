package org.knowm.datasets.numenta;

import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

public class NumentaDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0B5VXh50WzAw2dWYzdnI0NFFISGs";
    String propsFileID = "0B5VXh50WzAw2T1gtV0dBWE1mLUE";
    String scriptFileID = "0B5VXh50WzAw2TjBlZ1REUWprTmM";
    init("DB_NUMENTA", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, false);
  }

  public static void createTable(String tableName) {

    // @formatter:off
    String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (`timestamp` BIGINT NOT NULL, `value` double NOT NULL, `label` TINYINT NOT NULL, UNIQUE KEY `timestamp` (`timestamp`)) ENGINE=MyISAM DEFAULT CHARSET=utf8;";
    // @formatter:on
    Yank.execute(CREATE_TABLE_SQL, null);
  }

  public static void dropTable(String tableName) {

    String DROP_TABLE = "DROP TABLE IF EXISTS " + tableName;
    Yank.execute(DROP_TABLE, null);
  }

  public static void insertSeriesPoint(String tableName, SeriesPoint point) {

    Object[] params = new Object[] {

        // @formatter:off
        point.getTimestamp(),
        point.getValue(),
        point.getLabel()
        // @formatter:on
    };
    String INSERT = "REPLACE INTO " + tableName + " (timestamp, value, label) VALUES (?, ?, ?)";
    Yank.execute(INSERT, params);
  }

  public static List<SeriesPoint> selectAll(String tableName) {

    String SELECT_ALL_SQL = "SELECT * FROM " + tableName;
    return Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, null);
  }

}
