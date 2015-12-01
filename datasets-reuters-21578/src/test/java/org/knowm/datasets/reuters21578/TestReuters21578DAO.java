package org.knowm.datasets.reuters21578;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.datasets.reuters21578.Reuters21578;
import org.knowm.datasets.reuters21578.Reuters21578DAO;

/**
 * @author timmolter
 */
// @Ignore
public class TestReuters21578DAO {

  @BeforeClass
  public static void setUpDB() {

    Reuters21578DAO.init(new String[0]);
  }

  @AfterClass
  public static void tearDownDB() {

    Reuters21578DAO.release();
  }

  // @Test
  public void testSelectAll() {

    List<Reuters21578> allData = Reuters21578DAO.selectAll();
    assertThat(allData.size(), equalTo(21578));
  }

  // @Test
  public void testSelectRange() {

    List<Reuters21578> data = Reuters21578DAO.selectRange(10, 100);
    assertThat(data.size(), equalTo(100));
  }

  // @Test
  public void testSelectRandom() {

    List<Reuters21578> data1 = Reuters21578DAO.selectRandomList(10);
    assertThat(data1.size(), equalTo(10));

    List<Reuters21578> data2 = Reuters21578DAO.selectRandomList(10);
    assertThat(data2.size(), equalTo(10));

    assertThat(data1.get(0).getNewid(), not(equalTo(data2.get(0).getNewid())));

  }

  // @Test
  public void testSelectModApte() {

    List<Reuters21578> data = Reuters21578DAO.selectModApte("TRAIN", true);
    assertThat(data.size(), equalTo(9603));

    data = Reuters21578DAO.selectModApte("TEST", true);
    assertThat(data.size(), equalTo(3299));

    data = Reuters21578DAO.selectModApte("TRAIN", false);
    assertThat(data.size(), equalTo(5065));

    data = Reuters21578DAO.selectModApte("TEST", false);
    assertThat(data.size(), equalTo(2889));
  }

  @Test
  public void testSelectSingle() {

    Reuters21578 reuters21578 = Reuters21578DAO.selectSingle(1);
    assertThat(reuters21578.getNewid(), equalTo(1));
    assertThat(reuters21578.getTopics(), equalTo("cocoa"));
    assertThat(reuters21578.getPlaces(), equalTo("el-salvador,usa,uruguay"));

  }
}
