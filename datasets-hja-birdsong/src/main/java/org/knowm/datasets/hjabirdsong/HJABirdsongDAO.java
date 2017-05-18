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
package org.knowm.datasets.hjabirdsong;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class HJABirdsongDAO extends HJABirdsongParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS BIRD_SONGS", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(HJABirdSong hJABirdSong) {

    Object[] params = new Object[] {

        // @formatter:off
        hJABirdSong.getBagid(), hJABirdSong.getLabels(), hJABirdSong.getWavfilename(), hJABirdSong.getWavbytes()
        // @formatter:on
    };
    String BIRD_SONGS_INSERT = "INSERT INTO BIRD_SONGS (bagid, labels, wavfilename, wavbytes) VALUES (?, ?, ?, ?)";
    return Yank.execute(BIRD_SONGS_INSERT, params);
  }

  public static HJABirdSong selectSingle(int bagid) {

    Object[] params = new Object[] { bagid };

    String SELECT_SINGLE = "SELECT * FROM BIRD_SONGS WHERE bagid = ?";

    return Yank.queryBean(SELECT_SINGLE, HJABirdSong.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM BIRD_SONGS";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
