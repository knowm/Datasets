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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.xeiam.datasets.censusincome.CensusIncome;
import com.xeiam.datasets.censusincome.CensusIncomeDAO;

/**
 * Parses Census Income text files and put the data in a database
 * 
 * @author timmolter
 */
public class RawData2DB {

  int maxBodyLength = 0;
  int idx = 0;

  public static void main(String[] args) throws IOException {

    CensusIncomeDAO.initTest();

    CensusIncomeDAO.dropTable();
    CensusIncomeDAO.createTable();

    RawData2DB dp = new RawData2DB();
    dp.go("./raw/adult.data");
    dp.go("./raw/adult.test");

    CensusIncomeDAO.release();
  }

  private void go(String file) throws IOException {

    String data = FileUtils.readFileToString(new File(file), "UTF-8");
    System.out.println("loading data from " + file);

    String[] lines = data.split("\\r?\\n");

    for (int i = 0; i < lines.length; i++) {
      try {

        String[] entry = lines[i].split(",");
        CensusIncome censusIncome = new CensusIncome();

        censusIncome.setId(idx);
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
        CensusIncomeDAO.insert(censusIncome);
        System.out.println(censusIncome.getId());
        idx++;

      } catch (Exception e) {
        // eat it. Will throw exception on the first line of the test dataset.
        System.out.println("error parsing one line.");
      }
    }

  }

}
