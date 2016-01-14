/**
 * (The MIT License)
 *
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
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
