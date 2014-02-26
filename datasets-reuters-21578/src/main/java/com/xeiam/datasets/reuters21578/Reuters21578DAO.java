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
package com.xeiam.datasets.reuters21578;

import java.io.File;
import java.util.List;

import com.xeiam.datasets.common.business.DatasetsDAO;
import com.xeiam.yank.DBProxy;

/**
 * @author timmolter
 */
public class Reuters21578DAO extends DatasetsDAO {

  public static File init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17aTU1VzdCNFhRVEk";
    String propsFileID = "0ByP7_A9vXm17c1hwbTNFdVBwX0k";
    String scriptFileID = "0ByP7_A9vXm17Q3Q5ejFrNFlGOTA";

    return init("reuters21578connectionpool", "DB_REUTERS_21578", dataFilesDir, dataFileID, propsFileID, scriptFileID, true);
  }

  public static int dropTable() {

    return DBProxy.executeSQL("reuters21578connectionpool", "DROP TABLE IF EXISTS REUTERS_21578", null);
  }

  public static int createTable() {

    String REUTERS_21578_CREATE =
        "CREATE CACHED TABLE REUTERS_21578 (NEWID INTEGER NOT NULL, OLDID INTEGER NOT NULL, TOPICSBOOL TINYINT NULL, LEWISSPLIT VARCHAR(256) NULL, CGISPLIT VARCHAR(256) NULL, DATE TIME NULL, TOPICS VARCHAR(256) NULL, PLACES VARCHAR(256) NULL, PEOPLE VARCHAR(256) NULL, ORGS VARCHAR(256) NULL, EXCHANGES VARCHAR(256) NULL, COMPANIES VARCHAR(256) NULL, TITLE VARCHAR(256) NULL, DATELINE VARCHAR(256) NULL, BODY VARCHAR(13500) NULL, PRIMARY KEY (NEWID))";
    return DBProxy.executeSQL("reuters21578connectionpool", REUTERS_21578_CREATE, null);
  }

  public static int insert(Reuters21578 reuters21578) {

    Object[] params = new Object[] { 
       
        // @formatter:off
        reuters21578.getNewid(),
        reuters21578.getOldid(),
        reuters21578.isTopicsbool(),
        reuters21578.getLewissplit(),
        reuters21578.getCgisplit(),
        reuters21578.getDate(),
        reuters21578.getTopics(),
        reuters21578.getPlaces(),
        reuters21578.getPeople(),
        reuters21578.getOrgs(),
        reuters21578.getExchanges(),
        reuters21578.getCompanies(),
        reuters21578.getTitle(),
        reuters21578.getDateline(),
        reuters21578.getBody()
        // @formatter:on
        };
    String REUTERS_21578_INSERT =
        "INSERT INTO REUTERS_21578 (NEWID, OLDID, TOPICSBOOL, LEWISSPLIT, CGISPLIT, DATE, TOPICS, PLACES, PEOPLE, ORGS, EXCHANGES, COMPANIES, TITLE, DATELINE, BODY ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    return DBProxy.executeSQL("reuters21578connectionpool", REUTERS_21578_INSERT, params);
  }

  public static List<Reuters21578> selectAll() {

    String SELECT_ALL = "SELECT * FROM REUTERS_21578";

    return DBProxy.queryObjectListSQL("reuters21578connectionpool", SELECT_ALL, Reuters21578.class, null);
  }

  public static List<Reuters21578> selectRange(int startIdx, int size) {

    Object[] params = new Object[] { startIdx, size };
    String SELECT = "SELECT * FROM REUTERS_21578 LIMIT ?, ?";
    List<Reuters21578> reuters21578s = DBProxy.queryObjectListSQL("reuters21578connectionpool", SELECT, Reuters21578.class, params);

    return reuters21578s;
  }

  public static List<Reuters21578> selectRandomList(int size) {

    Object[] params = new Object[] { size };
    String SELECT = "SELECT * FROM REUTERS_21578 ORDER BY RAND() LIMIT 0, ?";
    List<Reuters21578> reuters21578s = DBProxy.queryObjectListSQL("reuters21578connectionpool", SELECT, Reuters21578.class, params);

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
    List<Reuters21578> reuters21578s = DBProxy.queryObjectListSQL("reuters21578connectionpool", SELECT, Reuters21578.class, params);

    return reuters21578s;
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM REUTERS_21578";

    return DBProxy.querySingleScalarSQL("reuters21578connectionpool", SELECT_COUNT, Long.class, null);
  }
}
