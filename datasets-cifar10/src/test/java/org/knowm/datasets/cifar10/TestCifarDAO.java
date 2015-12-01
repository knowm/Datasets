package org.knowm.datasets.cifar10;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.cifar10.CifarDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestCifarDAO {

  @BeforeClass
  public static void setUpDB() {

    CifarDAO.init(new String[0]);
  }

  @AfterClass
  public static void tearDownDB() {

    CifarDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = CifarDAO.selectCount();
    assertThat(count, equalTo(60000L));
  }

}
