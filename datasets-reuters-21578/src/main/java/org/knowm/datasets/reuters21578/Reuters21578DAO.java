/**
 * (The MIT License)
 *
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
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
package org.knowm.datasets.reuters21578;

import java.util.List;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class Reuters21578DAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17Smt5ZDdydk1JTWs";
    String propsFileID = "0ByP7_A9vXm17TnRPb1d5ak9Pc28";
    String scriptFileID = "0ByP7_A9vXm17ZEpQQ25oOGhWSHc";

    init("DB_REUTERS_21578", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS REUTERS_21578", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(Reuters21578 reuters21578) {

    Object[] params = new Object[] {

        // @formatter:off
        reuters21578.getNewid(), reuters21578.getOldid(), reuters21578.isTopicsbool(), reuters21578.getLewissplit(), reuters21578.getCgisplit(),
        reuters21578.getDate(), reuters21578.getTopics(), reuters21578.getPlaces(), reuters21578.getPeople(), reuters21578.getOrgs(),
        reuters21578.getExchanges(), reuters21578.getCompanies(), reuters21578.getTitle(), reuters21578.getDateline(), reuters21578.getBody()
        // @formatter:on
    };
    String REUTERS_21578_INSERT = "INSERT INTO REUTERS_21578 (NEWID, OLDID, TOPICSBOOL, LEWISSPLIT, CGISPLIT, DATE, TOPICS, PLACES, PEOPLE, ORGS, EXCHANGES, COMPANIES, TITLE, DATELINE, BODY ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return Yank.execute(REUTERS_21578_INSERT, params);
  }

  public static List<Reuters21578> selectAll() {

    String SELECT_ALL = "SELECT * FROM REUTERS_21578";

    return Yank.queryBeanList(SELECT_ALL, Reuters21578.class, null);
  }

  public static List<Reuters21578> selectRange(int startIdx, int size) {

    Object[] params = new Object[] { startIdx, size };
    String SELECT = "SELECT * FROM REUTERS_21578 LIMIT ?, ?";
    List<Reuters21578> reuters21578s = Yank.queryBeanList(SELECT, Reuters21578.class, params);

    return reuters21578s;
  }

  public static List<Reuters21578> selectRandomList(int size) {

    Object[] params = new Object[] { size };
    String SELECT = "SELECT * FROM REUTERS_21578 ORDER BY RAND() LIMIT 0, ?";
    List<Reuters21578> reuters21578s = Yank.queryBeanList(SELECT, Reuters21578.class, params);

    return reuters21578s;
  }

  /**
   * @param lewisSplit choices are TRAIN or TEST
   * @param topics does the story have topics (labels) or no?
   * @return a List<Reuters21578>
   */
  public static List<Reuters21578> selectModApte(String lewisSplit, boolean topics) {

    Object[] params = new Object[] { lewisSplit, topics };
    String SELECT = "SELECT * FROM REUTERS_21578 WHERE LEWISSPLIT = ? and TOPICSBOOL = ?";
    List<Reuters21578> reuters21578s = Yank.queryBeanList(SELECT, Reuters21578.class, params);

    return reuters21578s;
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM REUTERS_21578";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }

  public static Reuters21578 selectSingle(int newID) {

    String SELECT = "SELECT * FROM REUTERS_21578 WHERE newid = ?";

    Object[] params = new Object[] { newID };

    return Yank.queryBean(SELECT, Reuters21578.class, params);
  }
}
