package org.knowm.datasets.breastcancerwisconsinorginal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.breastcancerwisconsinorginal.BreastCancerDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestBreastCancerDAO {

  @BeforeClass
  public static void setUpDB() {

    BreastCancerDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    BreastCancerDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = BreastCancerDAO.selectCount();
    assertThat(count, equalTo(683L));
  }

}
