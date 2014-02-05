/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.datasets.censusincome.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.xeiam.datasets.censusincome.CensusIncome;
import com.xeiam.datasets.censusincome.CensusIncomeDAO;
import com.xeiam.datasets.common.utils.FileUtils;

/**
 * Parses Census Income text files and put the data in a database
 * 
 * @author timmolter
 */
public class RawData2DB {

  int maxBodyLength = 0;

  public static void main(String[] args) {

    CensusIncomeDAO.initTest();

    CensusIncomeDAO.dropTable();
    CensusIncomeDAO.createTable();

    RawData2DB dp = new RawData2DB();
    dp.go();

    CensusIncomeDAO.testRelease();
  }

  private void go() {

    List<CensusIncome> trainData = parseData("./raw/adult.data");
    System.out.println("trainData.size()= " + trainData.size());
    for (CensusIncome censusIncome : trainData) {
      CensusIncomeDAO.insert(censusIncome);
    }
    List<CensusIncome> testData = parseData("./raw/adult.test");
    System.out.println("testData.size()= " + testData.size());
    for (CensusIncome censusIncome : testData) {
      CensusIncomeDAO.insert(censusIncome);
    }
  }

  private static List<CensusIncome> parseData(String file) {

    String data = FileUtils.readFileToString(file);
    System.out.println("loading data from " + file);
    String[] lines = data.split("\\r?\\n");
    ArrayList<CensusIncome> censusIncomeData = new ArrayList<CensusIncome>();
    for (int i = 0; i < lines.length; i++) {
      try {

        // if (!lines[i].contains("?")) { // remove unknowns to compare with published benchmarks

        // if (lines[i].contains("?")) {
        // // System.out.println(lines[i]);
        // lines[i] = lines[i].replaceAll("\\?", "");
        // // System.out.println(lines[i]);
        // }

        String[] entry = lines[i].split(",");
        CensusIncome censusIncome = new CensusIncome();

        censusIncome.setAge(Integer.parseInt(entry[0].trim()));
        censusIncome.setWorkclass(entry[1].trim());
        censusIncome.setFnlwgt(Integer.parseInt(entry[2].trim()));
        censusIncome.setEducation(entry[3].trim());
        censusIncome.setEducationNum(Integer.parseInt(entry[4].trim()));
        censusIncome.setMaritalStatus(entry[5].trim());
        censusIncome.setOccupation(entry[6].trim());
        censusIncome.setRelationship(entry[7].trim());
        censusIncome.setRace(entry[8].trim());
        censusIncome.setSex(entry[9].trim());
        censusIncome.setCapitalGain(Integer.parseInt(entry[10].trim()));
        censusIncome.setCapitalLoss(Integer.parseInt(entry[11].trim()));
        censusIncome.setHoursPerWeek(Integer.parseInt(entry[12].trim()));
        censusIncome.setNativeCountry(entry[13].trim());

        if (entry[14].trim().equalsIgnoreCase("<=50K") | entry[14].trim().equalsIgnoreCase("<=50K.")) {
          censusIncome.setIncomeLessThan50k(true);
        }
        else if (entry[14].trim().equalsIgnoreCase(">50K") | entry[14].trim().equalsIgnoreCase(">50K.")) {
          censusIncome.setIncomeLessThan50k(false);
        }

        // System.out.println(censusIncome.isIncomeLessThan50k());
        censusIncomeData.add(censusIncome);
        // } else {
        // // System.out.println("Skipping: " + lines[i]);
        // }

      } catch (Exception e) {
        // eat it. Will throw exception on the first line of the test dataset.
        System.out.println("error parsing one line.");
      }
    }

    return censusIncomeData;
  }

}
