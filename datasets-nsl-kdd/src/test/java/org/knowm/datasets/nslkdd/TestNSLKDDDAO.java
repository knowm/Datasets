package org.knowm.datasets.nslkdd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.nslkdd.NSLKDD;
import org.knowm.datasets.nslkdd.NSLKDDDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestNSLKDDDAO {

  @BeforeClass
  public static void setUpDB() {

    NSLKDDDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    NSLKDDDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = NSLKDDDAO.selectCount();
    assertThat(count, equalTo(148517L));
  }

  @Test
  public void testSelectSingle() {

    NSLKDD nSLKDD = NSLKDDDAO.selectSingle(4);
    System.out.println(nSLKDD);
    assertThat(nSLKDD.getSrc_bytes(), equalTo(199f));
  }

}
