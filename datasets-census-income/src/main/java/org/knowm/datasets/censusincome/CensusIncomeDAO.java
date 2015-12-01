package org.knowm.datasets.censusincome;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class CensusIncomeDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17cU5QYzExY2dEeXc";
    String propsFileID = "0ByP7_A9vXm17THlaaXBoSzMzc3M";
    String scriptFileID = "0ByP7_A9vXm17QUprZUN6Q3pGZ2c";

    init("DB_CENSUS_INCOME", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS CENSUS_INCOME", null);
  }

  public static int getTrainTestSplit() {

    return 32561;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(CensusIncome censusIncome) {

    Object[] params = new Object[] {

        // @formatter:off
        censusIncome.getId(), censusIncome.getAge(), censusIncome.getWorkclass(), censusIncome.getFnlwgt(), censusIncome.getEducation(),
        censusIncome.getEducationNum(), censusIncome.getMaritalStatus(), censusIncome.getOccupation(), censusIncome.getRelationship(),
        censusIncome.getRace(), censusIncome.getSex(), censusIncome.getCapitalGain(), censusIncome.getCapitalLoss(), censusIncome.getHoursPerWeek(),
        censusIncome.getNativeCountry(), censusIncome.isIncomeLessThan50k()
        // @formatter:on
    };
    String CENSUS_INCOME_INSERT = "INSERT INTO CENSUS_INCOME (id, age, workclass, fnlwgt, education, educationNum, maritalStatus, occupation, relationship, race, sex, capitalGain, capitalLoss, hoursPerWeek, nativeCountry, incomeLessThan50k"
        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return Yank.execute(CENSUS_INCOME_INSERT, params);

  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM CENSUS_INCOME";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }

  public static CensusIncome selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM CENSUS_INCOME WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, CensusIncome.class, params);
  }

}
