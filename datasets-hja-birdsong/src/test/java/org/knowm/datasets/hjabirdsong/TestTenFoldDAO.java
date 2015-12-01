package org.knowm.datasets.hjabirdsong;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.hjabirdsong.TenFold;
import org.knowm.datasets.hjabirdsong.TenFoldDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestTenFoldDAO {

  @BeforeClass
  public static void setUpDB() {

    TenFoldDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    TenFoldDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = TenFoldDAO.selectCount();
    assertThat(count, equalTo(548L));
  }

  @Test
  public void testSelect() {

    TenFold tenFold = TenFoldDAO.selectSingle(3);
    assertThat(tenFold.getBagid(), equalTo(3));
    assertThat(tenFold.getFold(), equalTo(7));
  }

}
