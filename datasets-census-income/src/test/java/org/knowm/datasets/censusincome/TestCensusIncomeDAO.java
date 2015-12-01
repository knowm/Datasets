package org.knowm.datasets.censusincome;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.censusincome.CensusIncomeDAO;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
@Ignore
public class TestCensusIncomeDAO {

  @BeforeClass
  public static void setUpDB() {

    Yank.setupDataSource(PropertiesUtils.getPropertiesFromClasspath("DB_HSQLDB_FILE.properties"));
  }

  @AfterClass
  public static void tearDownDB() {

    CensusIncomeDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = CensusIncomeDAO.selectCount();
    assertThat(count, equalTo(48842L));
  }

}
