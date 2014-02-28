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
package com.xeiam.datasets.hjabirdsong;

import com.xeiam.yank.DBProxy;

/**
 * @author timmolter
 */
public class HJABirdsongDAO extends HJABirdsongParentDAO {

  public static int dropTable() {

    return DBProxy.executeSQL("hjabirdsongconnectionpool", "DROP TABLE IF EXISTS BAG_LABELS", null);
  }

  public static int createTable() {

    String BagLabels_CREATE = "CREATE CACHED TABLE BAG_LABELS (bagid INTEGER NOT NULL, labels VARCHAR(256) NOT NULL, wavfilename VARCHAR(256) NOT NULL, PRIMARY KEY (bagid))";
    return DBProxy.executeSQL("hjabirdsongconnectionpool", BagLabels_CREATE, null);
  }

  public static int insert(HJABirdSong bagLabels) {

    Object[] params = new Object[] {

    // @formatter:off
        bagLabels.getBagid(),
        bagLabels.getLabels(),
        bagLabels.getWavfilename()
    // @formatter:on
        };
    String BAG_LABELS_INSERT = "INSERT INTO BAG_LABELS (bagid, labels, wavfilename) VALUES (?, ?, ?)";
    return DBProxy.executeSQL("hjabirdsongconnectionpool", BAG_LABELS_INSERT, params);
  }

  public static HJABirdSong selectSingle(int bagid) {

    Object[] params = new Object[] { bagid };

    String SELECT_SINGLE = "SELECT * FROM BAG_LABELS WHERE bagid = ?";

    return DBProxy.querySingleObjectSQL("hjabirdsongconnectionpool", SELECT_SINGLE, HJABirdSong.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM BAG_LABELS";

    return DBProxy.querySingleScalarSQL("hjabirdsongconnectionpool", SELECT_COUNT, Long.class, null);
  }
}
