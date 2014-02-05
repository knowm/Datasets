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
package com.xeiam.datasets.breastcancerwisconsinorginal.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import com.xeiam.datasets.breastcancerwisconsinorginal.BreastCancer;
import com.xeiam.datasets.breastcancerwisconsinorginal.BreastCancerDAO;
import com.xeiam.datasets.common.utils.FileUtils;

/**
 * Parses Breast Cancer text files and put the data in a database
 * 
 * @author timmolter
 */
@Ignore
public class RawData2DB {

  int maxBodyLength = 0;

  public static void main(String[] args) {

    BreastCancerDAO.initTest();

    BreastCancerDAO.dropTable();
    BreastCancerDAO.createTable();

    RawData2DB dp = new RawData2DB();
    dp.go();

    BreastCancerDAO.testRelease();

  }

  private void go() {

    List<BreastCancer> breastCancerList = parseData("./raw/breast-cancer-wisconsin.data");

    for (BreastCancer breastCancer : breastCancerList) {
      BreastCancerDAO.insert(breastCancer);
    }
  }

  private List<BreastCancer> parseData(String file) {

    String data = FileUtils.readFileToString(file);
    System.out.println("loading data from " + file);
    String[] lines = data.split("\\r?\\n");
    ArrayList<BreastCancer> breastCancerData = new ArrayList<BreastCancer>();
    for (int i = 0; i < lines.length; i++) {
      try {
        if (!lines[i].contains("?")) { // remove unknowns to compare with published benchmarks

          String[] entry = lines[i].split(",");
          BreastCancer breastCancer = new BreastCancer();

          breastCancer.setSampleCodeNumber(Integer.parseInt(entry[0]));
          breastCancer.setClumpThickness(Integer.parseInt(entry[1]));
          breastCancer.setUniformityOfCellSize(Integer.parseInt(entry[2]));
          breastCancer.setUniformityOfCellShape(Integer.parseInt(entry[3]));
          breastCancer.setMarginalAdhesion(Integer.parseInt(entry[4]));
          breastCancer.setSingleEpithelialCellSize(Integer.parseInt(entry[5]));
          breastCancer.setBareNuclei(Integer.parseInt(entry[6]));
          breastCancer.setBlandChromatin(Integer.parseInt(entry[7]));
          breastCancer.setNormalNucleoli(Integer.parseInt(entry[8]));
          breastCancer.setMitoses(Integer.parseInt(entry[9]));
          breastCancer.setCellClass(Integer.parseInt(entry[10]));

          breastCancerData.add(breastCancer);
        }
        else {
          System.out.println("Skipping: " + lines[i]);
        }

      } catch (Exception e) {
        System.out.println("error parsing one line.");
        // eat it. Will throw exception on the first line of the test dataset.
      }
    }

    return breastCancerData;
  }

}
