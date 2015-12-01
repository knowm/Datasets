package org.knowm.datasets.cifar10;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class CifarDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17VERJam9EMm5sTkU";
    String propsFileID = "0ByP7_A9vXm17VHIzd1hSNW4zUXc";
    String scriptFileID = "0ByP7_A9vXm17eHlzcDJfalNoYkk";

    init("DB_CIFAR", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS CIFAR", null);
  }

  public static int getTrainTestSplit() {

    return 50000;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(Cifar CIFAR) {

    Object[] params = new Object[] {

        // @formatter:off
        CIFAR.getId(), CIFAR.getLabel(), CIFAR.getImagedata()
        // @formatter:on
    };
    String CIFAR_INSERT = "INSERT INTO CIFAR (id, label, imagedata) VALUES (?, ?, ?)";
    return Yank.execute(CIFAR_INSERT, params);

  }

  public static Cifar selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM CIFAR WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, Cifar.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM CIFAR";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
