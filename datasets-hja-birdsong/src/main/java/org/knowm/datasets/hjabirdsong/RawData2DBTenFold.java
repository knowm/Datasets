/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package org.knowm.datasets.hjabirdsong;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.knowm.datasets.common.Splitter;

/**
 * @author timmolter
 */
public class RawData2DBTenFold {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    TenFoldDAO.init(args);

    TenFoldDAO.dropTable();
    TenFoldDAO.createTable();

    RawData2DBTenFold dp = new RawData2DBTenFold();
    System.out.println("processing TenFold data...");
    dp.go("./raw/hja_birdsong_10_fold.txt");

    System.out.println("done.");

    TenFoldDAO.release();
  }

  private void go(String dataFile) throws IOException {

    List<String> lines = FileUtils.readLines(new File(dataFile), "UTF-8");

    for (String line : lines) {

      System.out.println(line);
      Iterable<String> splitLine = Splitter.split(",", line);
      Iterator<String> itr = splitLine.iterator();
      String bagid = itr.next();
      String fold = itr.next();
      try {
        TenFold tenFold = new TenFold();
        tenFold.setBagid(Integer.parseInt(bagid));
        tenFold.setFold(Integer.parseInt(fold));
        TenFoldDAO.insert(tenFold);
        System.out.println(tenFold.toString());
        idx++;
      } catch (Exception e) {
        // e.printStackTrace();
        // eat it. skip first line in file.
      }

    }

    System.out.println("Number parsed: " + idx);

  }
}
