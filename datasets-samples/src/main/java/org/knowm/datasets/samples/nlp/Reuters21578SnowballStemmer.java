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
package org.knowm.datasets.samples.nlp;

import java.util.Arrays;
import java.util.List;

import org.knowm.datasets.common.nlp.SnowballStemmerUtils;
import org.knowm.datasets.reuters21578.Reuters21578;
import org.knowm.datasets.reuters21578.Reuters21578DAO;

/**
 * @author timmolter
 */
public class Reuters21578SnowballStemmer {

  public static void main(String[] args) {

    try {
      Reuters21578DAO.init("/usr/local/Datasets"); // setup data
      List<Reuters21578> reuters21578List = Reuters21578DAO.selectRandomList(1);
      String[] chunks = SnowballStemmerUtils.stem(reuters21578List.get(0).getBody());
      System.out.println(Arrays.toString(chunks));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Reuters21578DAO.release(); // release data resources
    }

  }
}
