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
package com.xeiam.datasets.ucsdanomaly;

import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.Yank;

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

    String BagLabels_CREATE = "CREATE CACHED TABLE UCSD_ANONMALIES (id INTEGER NOT NULL, tifid INTEGER NOT NULL, tifbytes blob NOT NULL, isanomaly TINYINT NOT NULL, PRIMARY KEY (id, tifid))";
    return Yank.execute(BagLabels_CREATE, null);
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
