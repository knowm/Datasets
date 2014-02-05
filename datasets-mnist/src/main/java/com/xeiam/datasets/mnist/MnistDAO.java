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

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBConnectionManager;
import com.xeiam.yank.DBProxy;
import com.xeiam.yank.PropertiesUtils;

/**
 * @author timmolter
 */
public class MnistDAO extends DatasetsDAO {

  public static File init() {

    return init("mnistdatapool", "DB_MNIST");
  }

  public static void initTest() {

    DBConnectionManager.INSTANCE.init(PropertiesUtils.getPropertiesFromClasspath("DB_TEST.properties"));
  }

  public static int dropTable() {

    return DBProxy.executeSQL("mnistdatapool", "DROP TABLE IF EXISTS MNIST", null);
  }

  public static int createTable() {

    String CENSUS_INCOME_CREATE = "CREATE CACHED TABLE MNIST (id INTEGER NOT NULL, label INTEGER NOT NULL, imagedata VARCHAR(3333) NOT NULL, PRIMARY KEY (id))";
    return DBProxy.executeSQL("mnistdatapool", CENSUS_INCOME_CREATE, null);
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
    return DBProxy.executeSQL("mnistdatapool", CENSUS_INCOME_INSERT, params);

  }

  public static List<Mnist> selectAll() {

    String SELECT_ALL = "SELECT * FROM MNIST";

    return DBProxy.queryObjectListSQL("mnistdatapool", SELECT_ALL, Mnist.class, null);
  }

  public static Mnist selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM MNIST WHERE id = ?";

    return DBProxy.querySingleObjectSQL("mnistdatapool", SELECT_SINGLE, Mnist.class, params);
  }

  public static List<Mnist> selectTrainData() {

    String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 1, 60000";

    return DBProxy.queryObjectListSQL("mnistdatapool", SELECT_TRAIN, Mnist.class, null);
  }

  public static List<Mnist> selectTestData() {

    String SELECT_TRAIN = "SELECT * FROM MNIST LIMIT 60000, 70000";

    return DBProxy.queryObjectListSQL("mnistdatapool", SELECT_TRAIN, Mnist.class, null);
  }

}
