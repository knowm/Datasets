/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
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
package org.knowm.datasets.lshtc4;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class LSHTC4DAO extends LSHTC4ParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS LSHTC4", null);
  }

  public static int getTrainTestSplit() {

    return 452167;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(LSHTC4 lSHTC4) {

    Object[] params = new Object[] {

        // @formatter:off
        lSHTC4.getId(), lSHTC4.getLabels(), lSHTC4.getFeatures()
        // @formatter:on
    };
    String LSHTC4_INSERT = "INSERT INTO LSHTC4 (id, labels, features) VALUES (?, ?, ?)";
    return Yank.execute(LSHTC4_INSERT, params);

  }

  public static LSHTC4 selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM LSHTC4 WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, LSHTC4.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM LSHTC4";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
