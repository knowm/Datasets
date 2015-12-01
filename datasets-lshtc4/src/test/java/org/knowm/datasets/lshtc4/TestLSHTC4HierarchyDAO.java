package org.knowm.datasets.lshtc4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.lshtc4.LSHTC4Hierarchy;
import org.knowm.datasets.lshtc4.LSHTC4HierarchyDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestLSHTC4HierarchyDAO {

  @BeforeClass
  public static void setUpDB() {

    LSHTC4HierarchyDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    LSHTC4HierarchyDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = LSHTC4HierarchyDAO.selectCount();
    assertThat(count, equalTo(863261L));

  }

  @Test
  public void test() {

    List<LSHTC4Hierarchy> all = LSHTC4HierarchyDAO.selectAll();
    assertThat(all.size(), equalTo(863261));

    // System.out.println(all.get(0));
    LSHTC4Hierarchy lSHTC4Hierarchy = LSHTC4HierarchyDAO.selectSingle(2214730);
    int parentID = lSHTC4Hierarchy.getParentid();
    assertThat(parentID, equalTo(2244783));

  }

}
