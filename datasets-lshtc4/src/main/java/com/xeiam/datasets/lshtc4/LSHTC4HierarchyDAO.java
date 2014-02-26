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
package com.xeiam.datasets.lshtc4;

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBProxy;

/**
 * @author timmolter
 */
public class LSHTC4HierarchyDAO extends DatasetsDAO {

  public static File init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17UUdES3N6YjJsS3M";
    String propsFileID = "0ByP7_A9vXm17bFE3VmxGTEVTeEU";
    String scriptFileID = "0ByP7_A9vXm17TDRUMEhvWi1fbUk";

    return init("lshtc4connectionpool", "DB_LSHTC4", dataFilesDir, dataFileID, propsFileID, scriptFileID, false);
  }

  public static int dropTable() {

    return DBProxy.executeSQL("lshtc4connectionpool", "DROP TABLE IF EXISTS LSHTC4Hierarchy", null);
  }

  public static int createTable() {

    String LSHTC4Hierarchy_CREATE = "CREATE CACHED TABLE LSHTC4Hierarchy (parentid INTEGER NOT NULL, nodeid INTEGER NOT NULL)";
    return DBProxy.executeSQL("lshtc4connectionpool", LSHTC4Hierarchy_CREATE, null);
  }

  public static int insert(LSHTC4Hierarchy lSHTC4Hierarchy) {

    Object[] params = new Object[] {

    // @formatter:off
        lSHTC4Hierarchy.getParentid(),
        lSHTC4Hierarchy.getNodeid()
    // @formatter:on
        };
    String LSHTC4Hierarchy_INSERT = "INSERT INTO LSHTC4Hierarchy (parentid, nodeid) VALUES (?, ?)";
    return DBProxy.executeSQL("lshtc4connectionpool", LSHTC4Hierarchy_INSERT, params);

  }

  public static List<LSHTC4Hierarchy> selectAll() {

    String SELECT_ALL = "SELECT * FROM LSHTC4Hierarchy";

    return DBProxy.queryObjectListSQL("lshtc4connectionpool", SELECT_ALL, LSHTC4Hierarchy.class, null);
  }

  public static LSHTC4Hierarchy selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM LSHTC4Hierarchy WHERE nodeid = ?";

    return DBProxy.querySingleObjectSQL("lshtc4connectionpool", SELECT_SINGLE, LSHTC4Hierarchy.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM LSHTC4Hierarchy";

    return DBProxy.querySingleScalarSQL("lshtc4connectionpool", SELECT_COUNT, Long.class, null);
  }
}
