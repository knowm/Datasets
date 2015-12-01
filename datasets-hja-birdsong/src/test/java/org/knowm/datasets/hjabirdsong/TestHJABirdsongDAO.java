package org.knowm.datasets.hjabirdsong;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.hjabirdsong.HJABirdSong;
import org.knowm.datasets.hjabirdsong.HJABirdsongDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestHJABirdsongDAO {

  @BeforeClass
  public static void setUpDB() {

    HJABirdsongDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    HJABirdsongDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = HJABirdsongDAO.selectCount();
    assertThat(count, equalTo(548L));
  }

  @Test
  public void testSelect() throws SQLException {

    HJABirdSong hJABirdSong = HJABirdsongDAO.selectSingle(3);
    assertThat(hJABirdSong.getBagid(), equalTo(3));
    assertThat(hJABirdSong.getLabels(), equalTo("1,3"));
    assertThat(hJABirdSong.getLabelsAsArray().get(0), equalTo(1));
    assertThat(hJABirdSong.getLabelsAsArray().get(1), equalTo(3));
    assertThat(hJABirdSong.getWavfilename(), equalTo("PC13_20090531_050000_10.wav"));
    // System.out.println(hJABirdSong.getWavbytes().length());
    // System.out.println(new String(hJABirdSong.getWavbytes().getBytes(1, 4)));
    // assertThat(new String(hJABirdSong.getWavbytes().getBytes(1, 4)), equalTo("test"));
  }
}
