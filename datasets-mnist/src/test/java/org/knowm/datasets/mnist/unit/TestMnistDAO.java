package org.knowm.datasets.mnist.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.mnist.Mnist;
import org.knowm.datasets.mnist.MnistDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestMnistDAO {

  @BeforeClass
  public static void setUpDB() {

    MnistDAO.init(new String[0]);
  }

  @AfterClass
  public static void tearDownDB() {

    MnistDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = MnistDAO.selectCount();
    assertThat(count, equalTo(70000L));
  }

  @Test
  public void testSelectSingle() {

    Mnist mnist = MnistDAO.selectSingle(2);
    assertThat(mnist, not(equalTo(null)));
  }

}
