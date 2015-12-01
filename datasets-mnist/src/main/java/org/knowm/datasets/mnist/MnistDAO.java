package org.knowm.datasets.mnist;

import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class MnistDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17ZGEyOFNjVzdVdlU";
    String propsFileID = "0ByP7_A9vXm17SV96TE5jdnY0UDQ";
    String scriptFileID = "0ByP7_A9vXm17U2s1d0FzdHlILUE";

    init("DB_MNIST", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS MNIST", null);
  }

  public static int getTrainTestSplit() {

    return 60000;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(Mnist mnist) {

    Object[] params = new Object[] {

        // @formatter:off
        mnist.getId(), mnist.getLabel(), mnist.getImagedata()
        // @formatter:on
    };
    String CENSUS_INCOME_INSERT = "INSERT INTO MNIST (id, label, imagedata) VALUES (?, ?, ?)";
    return Yank.execute(CENSUS_INCOME_INSERT, params);

  }

  public static List<Mnist> selectAll() {

    String SELECT_ALL = "SELECT * FROM MNIST";

    return Yank.queryBeanList(SELECT_ALL, Mnist.class, null);
  }

  public static Mnist selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM MNIST WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, Mnist.class, params);
  }

  // public static List<Mnist> selectTrainData() {
  //
  // String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 1, 60000";
  //
  // return Yank.queryBeanList(  SELECT_TRAIN, Mnist.class, null);
  // }
  //
  // public static List<Mnist> selectTestData() {
  //
  // String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 60000, 70000";
  //
  // return Yank.queryBeanList(  SELECT_TRAIN, Mnist.class, null);
  // }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM MNIST";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }

}
