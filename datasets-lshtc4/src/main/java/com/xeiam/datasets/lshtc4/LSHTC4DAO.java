package com.xeiam.datasets.lshtc4;

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBConnectionManager;
import com.xeiam.yank.DBProxy;
import com.xeiam.yank.PropertiesUtils;

/**
 * @author timmolter
 */
public class LSHTC4DAO extends DatasetsDAO {

  public static File init() {

    return init("lshtc4datapool", "DB_LSHTC4");
  }

  public static void initTest() {

    DBConnectionManager.INSTANCE.init(PropertiesUtils.getPropertiesFromClasspath("DB_TEST.properties"));
  }

  public static int dropTable() {

    return DBProxy.executeSQL("lshtc4datapool", "DROP TABLE IF EXISTS LSHTC4", null);
  }

  public static int createTable() {

    String LSHTC4_CREATE = "CREATE CACHED TABLE LSHTC4 (id INTEGER NOT NULL, labels VARCHAR(1344) NOT NULL, features VARCHAR(47449) NOT NULL, PRIMARY KEY (id))";
    return DBProxy.executeSQL("lshtc4datapool", LSHTC4_CREATE, null);
  }

  public static int insert(LSHTC4 lSHTC4) {

    Object[] params = new Object[] {

    // @formatter:off
        lSHTC4.getId(),
        lSHTC4.getLabels(),
        lSHTC4.getFeatures()
    // @formatter:on
        };
    String LSHTC4_INSERT = "INSERT INTO LSHTC4 (id, labels, features) VALUES (?, ?, ?)";
    return DBProxy.executeSQL("lshtc4datapool", LSHTC4_INSERT, params);

  }

  public static List<LSHTC4> selectAll() {

    String SELECT_ALL = "SELECT * FROM LSHTC4";

    return DBProxy.queryObjectListSQL("lshtc4datapool", SELECT_ALL, LSHTC4.class, null);
  }

  public static LSHTC4 selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM LSHTC4 WHERE id = ?";

    return DBProxy.querySingleObjectSQL("lshtc4datapool", SELECT_SINGLE, LSHTC4.class, params);
  }

  public static List<LSHTC4> selectTrainData() {

    String SELECT_TRAIN = "SELECT * FROM LSHTC4 LIMIT 452167, 2817604";

    return DBProxy.queryObjectListSQL("lshtc4datapool", SELECT_TRAIN, LSHTC4.class, null);
  }

  public static List<LSHTC4> selectTestData() {

    String SELECT_TRAIN = "SELECT * FROM LSHTC4 LIMIT 0, 452167";

    return DBProxy.queryObjectListSQL("lshtc4datapool", SELECT_TRAIN, LSHTC4.class, null);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM LSHTC4";

    return DBProxy.querySingleScalarSQL("lshtc4datapool", SELECT_COUNT, Long.class, null);
  }
}
