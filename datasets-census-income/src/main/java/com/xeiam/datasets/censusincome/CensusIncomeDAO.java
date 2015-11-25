/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.datasets.censusincome;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.Yank;

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
