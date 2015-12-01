package org.knowm.datasets.ucsdanomaly;

import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class UCSDAnomalyDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17eFhpQUliNzhsSVk";
    String propsFileID = "0ByP7_A9vXm17SVJ0ZmI1Q0Q2djg";
    String scriptFileID = "0ByP7_A9vXm17OXZvU3RyM0ZVODA";
    String lobsFileID = "0ByP7_A9vXm17N0VCUE5OQUtxY2s";

    init("DB_UCSD_ANOMALY", dataFilesDir, dataFileID, propsFileID, scriptFileID, lobsFileID, false);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS UCSD_ANONMALIES", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int getTrainTestSplit() {

    return 34;
  }

  public static int insert(UCSDAnomaly uCSDAnomaly) {

    Object[] params = new Object[] {

        // @formatter:off
        uCSDAnomaly.getId(), uCSDAnomaly.getTifid(), uCSDAnomaly.getTifbytes(), uCSDAnomaly.isIsanomaly()
        // @formatter:on
    };
    String INSERT = "INSERT INTO UCSD_ANONMALIES (id, tifid, tifbytes, isanomaly) VALUES (?, ?, ?, ?)";
    return Yank.execute(INSERT, params);
  }

  public static UCSDAnomaly selectSingle(int id, int tifid) {

    Object[] params = new Object[] { id, tifid };

    String SELECT_SINGLE = "SELECT * FROM UCSD_ANONMALIES WHERE id = ? and tifid = ?";

    return Yank.queryBean(SELECT_SINGLE, UCSDAnomaly.class, params);
  }

  public static List<UCSDAnomaly> selectClip(int id) {

    Object[] params = new Object[] { id };

    String SELECT_CLIP = "SELECT * FROM UCSD_ANONMALIES WHERE id = ?";

    return Yank.queryBeanList(SELECT_CLIP, UCSDAnomaly.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM UCSD_ANONMALIES";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
