package org.knowm.datasets.samples;

import org.knowm.datasets.nslkdd.NSLKDD;
import org.knowm.datasets.nslkdd.NSLKDDDAO;

/**
 * @author timmolter
 */
public class NSLKDDDemo {

  public static void main(String[] args) {

    try {
      NSLKDDDAO.init("/usr/local/Datasets"); // setup data
      NSLKDDDemo demo = new NSLKDDDemo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      NSLKDDDAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = NSLKDDDAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    for (int i = 0; i < NSLKDDDAO.getTrainTestSplit(); i++) {
      NSLKDD nSLKDD = NSLKDDDAO.selectSingle(i);
      System.out.println(nSLKDD.toString());
    }

    // loop through test objects
    for (int i = NSLKDDDAO.getTrainTestSplit(); i < count; i++) {
      NSLKDD nSLKDD = NSLKDDDAO.selectSingle(i);
      System.out.println(nSLKDD.toString());
    }
  }

}
