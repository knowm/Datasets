package org.knowm.datasets.breastcancerwisconsinorginal;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author alexnugent
 */
public class BreastCancerDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17TmRYcmNScnYzS1E";
    String propsFileID = "0ByP7_A9vXm17X3hFNFA3NGViWlE";
    String scriptFileID = "0ByP7_A9vXm17NEQycTM1Q0Y3QXM";

    init("DB_BREAST_CANCER", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, false);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS " + "BREAST_CANCER", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(BreastCancer breastCancer) {

    Object[] params = new Object[] {

        // @formatter:off
        breastCancer.getId(), breastCancer.getSampleCodeNumber(), breastCancer.getClumpThickness(), breastCancer.getUniformityOfCellSize(),
        breastCancer.getUniformityOfCellShape(), breastCancer.getMarginalAdhesion(), breastCancer.getSingleEpithelialCellSize(),
        breastCancer.getBareNuclei(), breastCancer.getBlandChromatin(), breastCancer.getNormalNucleoli(), breastCancer.getMitoses(),
        breastCancer.getCellClass()
        // @formatter:on
    };
    String BREAST_CANCER_INSERT = "INSERT INTO BREAST_CANCER (ID, SAMPLECODENUMBER, CLUMPTHICKNESS, UNIFORMITYOFCELLSIZE, UNIFORMITYOFCELLSHAPE, MARGINALADHESION, SINGLEEPITHELIALCELLSIZE, BARENUCLEI, BLANDCHROMATIN,"
        + " NORMALNUCLEOLI, MITOSES, CELLCLASS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return Yank.execute(BREAST_CANCER_INSERT, params);

  }

  public static int getTrainTestSplit() {

    return 500;
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM BREAST_CANCER";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }

  public static BreastCancer selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM BREAST_CANCER WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, BreastCancer.class, params);
  }

}
