package org.knowm.datasets.samples;

import org.knowm.datasets.lshtc4.LSHTC4;
import org.knowm.datasets.lshtc4.LSHTC4DAO;
import org.knowm.datasets.lshtc4.LSHTC4HierarchyDAO;

/**
 * @author timmolter
 */
public class LSHTC4Demo {

  public static void main(String[] args) {

    try {
      LSHTC4DAO.init("/usr/local/Datasets"); // setup data
      LSHTC4Demo demo = new LSHTC4Demo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      LSHTC4DAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = LSHTC4DAO.selectCount();
    System.out.println("count= " + count);

    // print number of LSHTC4Hierarchy objects
    long hierarchyCount = LSHTC4HierarchyDAO.selectCount();
    System.out.println("hierarchyCount= " + hierarchyCount);

    // loop through test objects
    for (int i = 0; i < LSHTC4DAO.getTrainTestSplit(); i++) {
      LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(i);
      System.out.println(lSHTC4.toString());
    }

    // loop through train objects
    for (int i = LSHTC4DAO.getTrainTestSplit(); i < count; i++) {
      LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(i);
      System.out.println(lSHTC4.toString());
    }

  }

}
