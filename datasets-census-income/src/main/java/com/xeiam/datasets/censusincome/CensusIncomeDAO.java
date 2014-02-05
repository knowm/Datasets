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

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBConnectionManager;
import com.xeiam.yank.DBProxy;
import com.xeiam.yank.PropertiesUtils;

/**
 * @author alexnugent
 */
public class CensusIncomeDAO extends DatasetsDAO {

  public static File init() {

    return init("censusincomedatapool", "DB_CENSUS_INCOME");
  }

  public static void initTest() {

    DBConnectionManager.INSTANCE.init(PropertiesUtils.getPropertiesFromClasspath("DB_TEST.properties"));
  }

  public static int dropTable() {

    return DBProxy.executeSQL("censusincomedatapool", "DROP TABLE IF EXISTS CENSUS_INCOME", null);
  }

  public static int createTable() {

    String CENSUS_INCOME_CREATE =
        "CREATE CACHED TABLE CENSUS_INCOME (age INTEGER NOT NULL, workclass VARCHAR(256) NOT NULL, fnlwgt INTEGER NOT NULL,"
            + " education VARCHAR(256) NOT NULL, educationNum INTEGER NOT NULL, maritalStatus VARCHAR(256) NOT NULL, occupation VARCHAR(256) NOT NULL,"
            + "relationship VARCHAR(256) NOT NULL, race VARCHAR(256) NOT NULL, sex VARCHAR(256) NOT NULL, capitalGain INTEGER NOT NULL, capitalLoss INTEGER NOT NULL,"
            + " hoursPerWeek INTEGER NOT NULL, nativeCountry VARCHAR(256) NOT NULL, incomeLessThan50k TINYINT NOT NULL)";
    return DBProxy.executeSQL("censusincomedatapool", CENSUS_INCOME_CREATE, null);
  }

  public static int insert(CensusIncome censusIncome) {

    Object[] params = new Object[] {

        // @formatter:off
        censusIncome.getAge(),
        censusIncome.getWorkclass(),
        censusIncome.getFnlwgt(),
        censusIncome.getEducation(),
        censusIncome.getEducationNum(),
        censusIncome.getMaritalStatus(),
        censusIncome.getOccupation(),
        censusIncome.getRelationship(),
        censusIncome.getRace(),
        censusIncome.getSex(),
        censusIncome.getCapitalGain(),
        censusIncome.getCapitalLoss(),
        censusIncome.getHoursPerWeek(),
        censusIncome.getNativeCountry(),
        censusIncome.isIncomeLessThan50k()
        // @formatter:on
        };
    String CENSUS_INCOME_INSERT =
        "INSERT INTO CENSUS_INCOME (age, workclass, fnlwgt, education, educationNum, maritalStatus, occupation, relationship, race, sex, capitalGain, capitalLoss, hoursPerWeek, nativeCountry, incomeLessThan50k"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return DBProxy.executeSQL("censusincomedatapool", CENSUS_INCOME_INSERT, params);

  }

  public static List<CensusIncome> selectAll() {

    String SELECT_ALL = "SELECT * FROM CENSUS_INCOME";

    return DBProxy.queryObjectListSQL("censusincomedatapool", SELECT_ALL, CensusIncome.class, null);
  }

  public static List<CensusIncome> selectTrainData() {

    String SELECT_TRAIN = "SELECT * FROM CENSUS_INCOME LIMIT 0, 32561";

    return DBProxy.queryObjectListSQL("censusincomedatapool", SELECT_TRAIN, CensusIncome.class, null);
  }

  public static List<CensusIncome> selectTestData() {

    String SELECT_TRAIN = "SELECT * FROM CENSUS_INCOME LIMIT 32561, 48842";

    return DBProxy.queryObjectListSQL("censusincomedatapool", SELECT_TRAIN, CensusIncome.class, null);
  }

  public static List<CensusIncome> getShuffledTrainData() {

    List<CensusIncome> shuffledList = selectTrainData();
    Collections.shuffle(shuffledList);

    return shuffledList;
  }

  public static List<CensusIncome> getShuffledTestData() {

    List<CensusIncome> shuffledList = selectTestData();
    Collections.shuffle(shuffledList);

    return shuffledList;
  }

}
