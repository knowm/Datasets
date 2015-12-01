package org.knowm.datasets.lshtc4;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class LSHTC4DAO extends LSHTC4ParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS LSHTC4", null);
  }

  public static int getTrainTestSplit() {

    return 452167;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(LSHTC4 lSHTC4) {

    Object[] params = new Object[] {

        // @formatter:off
        lSHTC4.getId(), lSHTC4.getLabels(), lSHTC4.getFeatures()
        // @formatter:on
    };
    String LSHTC4_INSERT = "INSERT INTO LSHTC4 (id, labels, features) VALUES (?, ?, ?)";
    return Yank.execute(LSHTC4_INSERT, params);

  }

  public static LSHTC4 selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM LSHTC4 WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, LSHTC4.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM LSHTC4";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
