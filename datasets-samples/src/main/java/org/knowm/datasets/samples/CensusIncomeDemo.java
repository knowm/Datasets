package org.knowm.datasets.samples;

import org.knowm.datasets.censusincome.CensusIncome;
import org.knowm.datasets.censusincome.CensusIncomeDAO;

/**
 * @author timmolter
 */
public class CensusIncomeDemo {

  public static void main(String[] args) {

    try {
      CensusIncomeDAO.init("/usr/local/Datasets"); // setup data
      CensusIncomeDemo demo = new CensusIncomeDemo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      CensusIncomeDAO.release(); // release data resources
    }
  }

  private void go() {

    // print number of objects
    long count = CensusIncomeDAO.selectCount();
    System.out.println("count= " + count);

    // loop through train objects
    for (int i = 0; i < CensusIncomeDAO.getTrainTestSplit(); i++) {
      CensusIncome censusIncome = CensusIncomeDAO.selectSingle(i);
      System.out.println(censusIncome.toString());
    }

    // loop through test objects
    for (int i = CensusIncomeDAO.getTrainTestSplit(); i < count; i++) {
      CensusIncome censusIncome = CensusIncomeDAO.selectSingle(i);
      System.out.println(censusIncome.toString());
    }

  }

}
