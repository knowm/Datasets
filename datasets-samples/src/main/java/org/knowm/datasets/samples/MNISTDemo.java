package org.knowm.datasets.samples;

import org.knowm.datasets.mnist.Mnist;
import org.knowm.datasets.mnist.MnistDAO;

/**
 * @author timmolter
 */
public class MNISTDemo {

  public static void main(String[] args) {

    try {
      MnistDAO.init("/usr/local/Datasets"); // setup data
      MNISTDemo demo = new MNISTDemo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      MnistDAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = MnistDAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    for (int i = 0; i < MnistDAO.getTrainTestSplit(); i++) {
      Mnist mnist = MnistDAO.selectSingle(i);
      System.out.println(mnist.toString());
    }

    // loop through test objects
    for (int i = MnistDAO.getTrainTestSplit(); i < count; i++) {
      Mnist mnist = MnistDAO.selectSingle(i);
      System.out.println(mnist.toString());
    }
  }

}
