package org.knowm.datasets.samples;

import org.knowm.datasets.breastcancerwisconsinorginal.BreastCancer;
import org.knowm.datasets.breastcancerwisconsinorginal.BreastCancerDAO;

/**
 * @author timmolter
 */
public class BreastCancerDemo {

  public static void main(String[] args) {

    try {
      BreastCancerDAO.init("/usr/local/Datasets/"); // setup data
      BreastCancerDemo demo = new BreastCancerDemo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BreastCancerDAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = BreastCancerDAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    for (int i = 0; i < BreastCancerDAO.getTrainTestSplit(); i++) {
      BreastCancer breastCancer = BreastCancerDAO.selectSingle(i);
      System.out.println(breastCancer.toString());
    }

    // loop through test objects
    for (int i = BreastCancerDAO.getTrainTestSplit(); i < count; i++) {
      BreastCancer breastCancer = BreastCancerDAO.selectSingle(i);
      System.out.println(breastCancer.toString());
    }

  }
}
