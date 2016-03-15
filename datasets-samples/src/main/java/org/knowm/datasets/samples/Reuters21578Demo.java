/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
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
