package com.xeiam.datasets.nab;

import com.xeiam.yank.Yank;

public class TimeSeriesDAO {

  public static void createTable(String tableName) {

    // @formatter:off
    String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `"
        + tableName
        + "` (`timestamp` DATE NOT NULL DEFAULT '', "
        + "`vale` int(11) NOT NULL, "
        + "`Fitness` double NOT NULL, "
        + "`Genome` varchar(10000) NOT NULL DEFAULT '',"
        + "`Chromosome` varchar(10000) NOT NULL DEFAULT '',"
        + "UNIQUE KEY `PopulationId` (`PopulationId`,`PhenotypeId`)"
        + " ) ENGINE=MyISAM DEFAULT CHARSET=utf8;";
    // @formatter:on

    Yank.execute(CREATE_TABLE_SQL, null);
  }

  public static void dropTable(String tableName) {

    String DROP_TABLE = "DROP TABLE IF EXISTS " + tableName;
    Yank.execute(DROP_TABLE, null);
  }
}
