package org.knowm.datasets.samples;

import java.util.List;

import org.knowm.datasets.reuters21578.Reuters21578;
import org.knowm.datasets.reuters21578.Reuters21578DAO;

/**
 * @author timmolter
 */
public class Reuters21578Demo {

  public static void main(String[] args) {

    try {
      Reuters21578DAO.init("/usr/local/Datasets"); // setup data
      Reuters21578Demo demo = new Reuters21578Demo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Reuters21578DAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = Reuters21578DAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    List<Reuters21578> trainSet = Reuters21578DAO.selectModApte("TRAIN", true);
    for (Reuters21578 reuters21578 : trainSet) {
      System.out.println(reuters21578.toString());
    }

    // loop through test objects
    List<Reuters21578> testSet = Reuters21578DAO.selectModApte("TEST", true);
    for (Reuters21578 reuters21578 : testSet) {
      System.out.println(reuters21578.toString());
    }
  }

}
