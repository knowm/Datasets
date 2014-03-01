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

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBProxy;

/**
 * @author alexnugent
 */
public class BreastCancerDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17TmRYcmNScnYzS1E";
    String propsFileID = "0ByP7_A9vXm17X3hFNFA3NGViWlE";
    String scriptFileID = "0ByP7_A9vXm17NEQycTM1Q0Y3QXM";

    init("breastcancerconnectionpool", "DB_BREAST_CANCER", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, false);
  }

  public static int dropTable() {

    return DBProxy.executeSQL("breastcancerconnectionpool", "DROP TABLE IF EXISTS " + "BREAST_CANCER", null);
  }

  public static int createTable() {

    String BREAST_CANCER_CREATE =
        "CREATE CACHED TABLE BREAST_CANCER (id INTEGER NOT NULL, SAMPLECODENUMBER INTEGER NOT NULL, CLUMPTHICKNESS INTEGER NOT NULL, UNIFORMITYOFCELLSIZE INTEGER NOT NULL,"
            + " UNIFORMITYOFCELLSHAPE INTEGER NOT NULL, MARGINALADHESION INTEGER NOT NULL, SINGLEEPITHELIALCELLSIZE INTEGER NOT NULL, BARENUCLEI INTEGER NOT NULL, BLANDCHROMATIN INTEGER NOT NULL,"
            + "NORMALNUCLEOLI INTEGER NOT NULL, MITOSES INTEGER NOT NULL, CELLCLASS INTEGER NOT NULL, PRIMARY KEY (id))";
    return DBProxy.executeSQL("breastcancerconnectionpool", BREAST_CANCER_CREATE, null);
  }

  public static int insert(BreastCancer breastCancer) {

    Object[] params = new Object[] { 
        
        // @formatter:off
        breastCancer.getId(),
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
        "INSERT INTO BREAST_CANCER (ID, SAMPLECODENUMBER, CLUMPTHICKNESS, UNIFORMITYOFCELLSIZE, UNIFORMITYOFCELLSHAPE, MARGINALADHESION, SINGLEEPITHELIALCELLSIZE, BARENUCLEI, BLANDCHROMATIN,"
            + " NORMALNUCLEOLI, MITOSES, CELLCLASS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return DBProxy.executeSQL("breastcancerconnectionpool", BREAST_CANCER_INSERT, params);

  }

  public static int getTrainTestSplit() {

    return 500;
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM BREAST_CANCER";

    return DBProxy.querySingleScalarSQL("breastcancerconnectionpool", SELECT_COUNT, Long.class, null);
  }

  public static BreastCancer selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM BREAST_CANCER WHERE id = ?";

    return DBProxy.querySingleObjectSQL("breastcancerconnectionpool", SELECT_SINGLE, BreastCancer.class, params);
  }

}
