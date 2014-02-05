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
package com.xeiam.datasets.cifar10;

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBConnectionManager;
import com.xeiam.yank.DBProxy;
import com.xeiam.yank.PropertiesUtils;

/**
 * @author timmolter
 */
public class CifarDAO extends DatasetsDAO {

  public static File init() {

    return init("cifardatapool", "DB_CIFAR");
  }

  public static void initTest() {

    DBConnectionManager.INSTANCE.init(PropertiesUtils.getPropertiesFromClasspath("DB_TEST.properties"));
  }

  public static int dropTable() {

    return DBProxy.executeSQL("cifardatapool", "DROP TABLE IF EXISTS CIFAR", null);
  }

  public static int createTable() {

    String CENSUS_INCOME_CREATE = "CREATE CACHED TABLE CIFAR (id INTEGER NOT NULL, label INTEGER NOT NULL, imagedata VARCHAR(30000) NOT NULL, PRIMARY KEY (id))";
    return DBProxy.executeSQL("cifardatapool", CENSUS_INCOME_CREATE, null);
  }

  public static int insert(Cifar CIFAR) {

    Object[] params = new Object[] {

    // @formatter:off
        CIFAR.getId(),
        CIFAR.getLabel(),
        CIFAR.getImagedata()
    // @formatter:on
        };
    String CENSUS_INCOME_INSERT = "INSERT INTO CIFAR (id, label, imagedata) VALUES (?, ?, ?)";
    return DBProxy.executeSQL("cifardatapool", CENSUS_INCOME_INSERT, params);

  }

  public static List<Cifar> selectAll() {

    String SELECT_ALL = "SELECT * FROM CIFAR";

    return DBProxy.queryObjectListSQL("cifardatapool", SELECT_ALL, Cifar.class, null);
  }

  public static Cifar selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM CIFAR WHERE id = ?";

    return DBProxy.querySingleObjectSQL("cifardatapool", SELECT_SINGLE, Cifar.class, params);
  }

  public static List<Cifar> selectTrainData() {

    String SELECT_TRAIN = "SELECT * FROM CIFAR LIMIT 1, 50000";

    return DBProxy.queryObjectListSQL("cifardatapool", SELECT_TRAIN, Cifar.class, null);
  }

  public static List<Cifar> selectTestData() {

    String SELECT_TRAIN = "SELECT * FROM CIFAR LIMIT 50001, 60000";

    return DBProxy.queryObjectListSQL("cifardatapool", SELECT_TRAIN, Cifar.class, null);
  }

}
