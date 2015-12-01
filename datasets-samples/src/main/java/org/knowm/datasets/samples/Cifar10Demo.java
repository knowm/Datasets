package org.knowm.datasets.samples;

import org.knowm.datasets.cifar10.Cifar;
import org.knowm.datasets.cifar10.CifarDAO;

/**
 * @author timmolter
 */
public class Cifar10Demo {

  public static void main(String[] args) {

    try {
      CifarDAO.init("/usr/local/Datasets"); // setup data
      Cifar10Demo demo = new Cifar10Demo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      CifarDAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = CifarDAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    for (int i = 0; i < CifarDAO.getTrainTestSplit(); i++) {
      Cifar cifar = CifarDAO.selectSingle(i);
      System.out.println(cifar.toString());
    }

    // loop through test objects
    for (int i = CifarDAO.getTrainTestSplit(); i < count; i++) {
      Cifar cifar = CifarDAO.selectSingle(i);
      System.out.println(cifar.toString());
    }
  }

}
