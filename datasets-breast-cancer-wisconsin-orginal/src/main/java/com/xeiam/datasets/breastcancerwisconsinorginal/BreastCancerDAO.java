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
package com.xeiam.datasets.breastcancerwisconsinorginal;

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBConnectionManager;
import com.xeiam.yank.DBProxy;
import com.xeiam.yank.PropertiesUtils;

/**
 * @author alexnugent
 */
public class BreastCancerDAO extends DatasetsDAO {

  public static File init() {

    return init("breastcancerdatapool", "DB_BREAST_CANCER");
  }

  public static void initTest() {

    DBConnectionManager.INSTANCE.init(PropertiesUtils.getPropertiesFromClasspath("DB_TEST.properties"));
  }

  public static int dropTable() {

    return DBProxy.executeSQL("breastcancerdatapool", "DROP TABLE IF EXISTS BREAST_CANCER", null);
  }

  public static int createTable() {

    String BREAST_CANCER_CREATE =
        "CREATE CACHED TABLE BREAST_CANCER (SAMPLECODENUMBER INTEGER NOT NULL, CLUMPTHICKNESS INTEGER NOT NULL, UNIFORMITYOFCELLSIZE INTEGER NOT NULL,"
            + " UNIFORMITYOFCELLSHAPE INTEGER NOT NULL, MARGINALADHESION INTEGER NOT NULL, SINGLEEPITHELIALCELLSIZE INTEGER NOT NULL, BARENUCLEI INTEGER NOT NULL, BLANDCHROMATIN INTEGER NOT NULL,"
            + "NORMALNUCLEOLI INTEGER NOT NULL, MITOSES INTEGER NOT NULL, CELLCLASS INTEGER NOT NULL)";
    return DBProxy.executeSQL("breastcancerdatapool", BREAST_CANCER_CREATE, null);
  }

  public static int insert(BreastCancer breastCancer) {

    Object[] params = new Object[] { 
        
        // @formatter:off
        breastCancer.getSampleCodeNumber(),
        breastCancer.getClumpThickness(),
        breastCancer.getUniformityOfCellSize(),
        breastCancer.getUniformityOfCellShape(),
        breastCancer.getMarginalAdhesion(),
        breastCancer.getSingleEpithelialCellSize(),
        breastCancer.getBareNuclei(),
        breastCancer.getBlandChromatin(),
        breastCancer.getNormalNucleoli(),
        breastCancer.getMitoses(),
        breastCancer.getCellClass()
        // @formatter:on
        };
    String BREAST_CANCER_INSERT =
        "INSERT INTO BREAST_CANCER (SAMPLECODENUMBER, CLUMPTHICKNESS, UNIFORMITYOFCELLSIZE, UNIFORMITYOFCELLSHAPE, MARGINALADHESION, SINGLEEPITHELIALCELLSIZE, BARENUCLEI, BLANDCHROMATIN,"
            + " NORMALNUCLEOLI, MITOSES, CELLCLASS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return DBProxy.executeSQL("breastcancerdatapool", BREAST_CANCER_INSERT, params);

  }

  public static List<BreastCancer> selectAll() {

    String SELECT_ALL = "SELECT * FROM BREAST_CANCER";

    return DBProxy.queryObjectListSQL("breastcancerdatapool", SELECT_ALL, BreastCancer.class, null);
  }

  /**
   * Selects the first 500 data entries
   * 
   * @return
   */
  public static List<BreastCancer> selectTrainData() {

    String SELECT_TRAIN = "SELECT * FROM BREAST_CANCER LIMIT 0, 500";

    return DBProxy.queryObjectListSQL("breastcancerdatapool", SELECT_TRAIN, BreastCancer.class, null);
  }

  /**
   * Selects the last 182 data entries
   * 
   * @return
   */
  public static List<BreastCancer> selectTestData() {

    String SELECT_TEST = "SELECT * FROM BREAST_CANCER LIMIT 500, 683";

    return DBProxy.queryObjectListSQL("breastcancerdatapool", SELECT_TEST, BreastCancer.class, null);
  }

}
