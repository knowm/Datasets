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
package com.xeiam.datasets.mnist;

import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBProxy;

/**
 * @author timmolter
 */
public class MnistDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17ZGEyOFNjVzdVdlU";
    String propsFileID = "0ByP7_A9vXm17SV96TE5jdnY0UDQ";
    String scriptFileID = "0ByP7_A9vXm17U2s1d0FzdHlILUE";

    init("mnistconnectionpool", "DB_MNIST", dataFilesDir, dataFileID, propsFileID, scriptFileID, true);
  }

  public static int dropTable() {

    return DBProxy.executeSQL("mnistconnectionpool", "DROP TABLE IF EXISTS MNIST", null);
  }

  public static int getTrainTestSplit() {

    return 60000;
  }

  public static int createTable() {

    String CENSUS_INCOME_CREATE = "CREATE CACHED TABLE MNIST (id INTEGER NOT NULL, label INTEGER NOT NULL, imagedata VARCHAR(3333) NOT NULL, PRIMARY KEY (id))";
    return DBProxy.executeSQL("mnistconnectionpool", CENSUS_INCOME_CREATE, null);
  }

  public static int insert(Mnist mnist) {

    Object[] params = new Object[] {

    // @formatter:off
        mnist.getId(),
        mnist.getLabel(),
        mnist.getImagedata()
    // @formatter:on
        };
    String CENSUS_INCOME_INSERT = "INSERT INTO MNIST (id, label, imagedata) VALUES (?, ?, ?)";
    return DBProxy.executeSQL("mnistconnectionpool", CENSUS_INCOME_INSERT, params);

  }

  public static List<Mnist> selectAll() {

    String SELECT_ALL = "SELECT * FROM MNIST";

    return DBProxy.queryObjectListSQL("mnistconnectionpool", SELECT_ALL, Mnist.class, null);
  }

  public static Mnist selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM MNIST WHERE id = ?";

    return DBProxy.querySingleObjectSQL("mnistconnectionpool", SELECT_SINGLE, Mnist.class, params);
  }

  // public static List<Mnist> selectTrainData() {
  //
  // String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 1, 60000";
  //
  // return DBProxy.queryObjectListSQL("mnistconnectionpool", SELECT_TRAIN, Mnist.class, null);
  // }
  //
  // public static List<Mnist> selectTestData() {
  //
  // String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 60000, 70000";
  //
  // return DBProxy.queryObjectListSQL("mnistconnectionpool", SELECT_TRAIN, Mnist.class, null);
  // }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM MNIST";

    return DBProxy.querySingleScalarSQL("mnistconnectionpool", SELECT_COUNT, Long.class, null);
  }

}
