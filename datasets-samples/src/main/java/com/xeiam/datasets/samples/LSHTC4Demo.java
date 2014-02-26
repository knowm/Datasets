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
package com.xeiam.datasets.samples;

import com.xeiam.datasets.lshtc4.LSHTC4;
import com.xeiam.datasets.lshtc4.LSHTC4DAO;
import com.xeiam.datasets.lshtc4.LSHTC4HierarchyDAO;

/**
 * @author timmolter
 */
public class LSHTC4Demo {

  public static void main(String[] args) {

    try {
      LSHTC4DAO.init("/Users/timmolter/Documents/Datasets"); // setup data
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
      System.out.println(lSHTC4);
    }

    // loop through train objects
    for (int i = LSHTC4DAO.getTrainTestSplit(); i < count; i++) {
      LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(i);
      System.out.println(lSHTC4);
    }

  }

}
