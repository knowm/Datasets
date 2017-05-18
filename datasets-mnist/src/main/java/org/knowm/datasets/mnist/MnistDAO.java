/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package org.knowm.datasets.mnist;

import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class MnistDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17V1NvVzdOY2dURUU";
    String propsFileID = "0ByP7_A9vXm17cGRudjlMQXpTY1U";
    String scriptFileID = "0ByP7_A9vXm17Qm5rMzlEa2VrUm8";
    String lobsFileID = "0ByP7_A9vXm17Sml5dXdrSXozUjQ";

    init("DB_MNIST", dataFilesDir, dataFileID, propsFileID, scriptFileID, lobsFileID, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS MNIST", null);
  }

  public static int getTrainTestSplit() {

    return 60000;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(Mnist mnist) {

    Object[] params = new Object[]{

        // @formatter:off
        mnist.getId(), mnist.getLabel(), mnist.getImgbytes()
        // @formatter:on
    };
    String CENSUS_INCOME_INSERT = "INSERT INTO MNIST (id, label, imgbytes) VALUES (?, ?, ?)";
    return Yank.execute(CENSUS_INCOME_INSERT, params);

  }

  public static List<Mnist> selectAll() {

    String SELECT_ALL = "SELECT * FROM MNIST";

    return Yank.queryBeanList(SELECT_ALL, Mnist.class, null);
  }

  public static Mnist selectSingle(int id) {

    Object[] params = new Object[]{id};

    String SELECT_SINGLE = "SELECT * FROM MNIST WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, Mnist.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM MNIST";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }

}
