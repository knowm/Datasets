package org.knowm.datasets.hjabirdsong;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class TenFoldDAO extends DatasetsDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS TEN_FOLD", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE_TENFOLD", null);
  }

  public static int insert(TenFold tenFold) {

    Object[] params = new Object[] {

        // @formatter:off
        tenFold.getBagid(), tenFold.getFold()
        // @formatter:on
    };
    String TEN_FOLD_INSERT = "INSERT INTO TEN_FOLD (bagid, fold) VALUES (?, ?)";
    return Yank.execute(TEN_FOLD_INSERT, params);
  }

  public static TenFold selectSingle(int bagid) {

    Object[] params = new Object[] { bagid };

    String SELECT_SINGLE = "SELECT * FROM TEN_FOLD WHERE bagid = ?";

    return Yank.queryBean(SELECT_SINGLE, TenFold.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM TEN_FOLD";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
