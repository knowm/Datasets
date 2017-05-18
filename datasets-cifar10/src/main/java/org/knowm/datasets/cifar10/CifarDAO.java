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
package org.knowm.datasets.cifar10;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class CifarDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17VERJam9EMm5sTkU";
    String propsFileID = "0ByP7_A9vXm17VHIzd1hSNW4zUXc";
    String scriptFileID = "0ByP7_A9vXm17eHlzcDJfalNoYkk";

    init("DB_CIFAR", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS CIFAR", null);
  }

  public static int getTrainTestSplit() {

    return 50000;
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(Cifar CIFAR) {

    Object[] params = new Object[] {

        // @formatter:off
        CIFAR.getId(), CIFAR.getLabel(), CIFAR.getImagedata()
        // @formatter:on
    };
    String CIFAR_INSERT = "INSERT INTO CIFAR (id, label, imagedata) VALUES (?, ?, ?)";
    return Yank.execute(CIFAR_INSERT, params);

  }

  public static Cifar selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM CIFAR WHERE id = ?";

    return Yank.queryBean(SELECT_SINGLE, Cifar.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM CIFAR";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
