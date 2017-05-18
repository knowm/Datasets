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
package org.knowm.datasets.lshtc4;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author timmolter
 */
public class RawData2DBHierarchy {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    LSHTC4HierarchyDAO.init(args);

    LSHTC4HierarchyDAO.dropTable();
    LSHTC4HierarchyDAO.createTable();

    RawData2DBHierarchy dp = new RawData2DBHierarchy();
    System.out.println("processing LSHTC4 hierarchy data...");
    dp.go("./raw/hierarchy.zip");

    System.out.println("done.");

    LSHTC4HierarchyDAO.release();
  }

  private void go(String dataFile) throws IOException {

    ZipFile zipFile = new ZipFile(dataFile);

    Enumeration<? extends ZipEntry> entries = zipFile.entries();

    while (entries.hasMoreElements()) { // there's actually only one entry
      ZipEntry entry = entries.nextElement();
      System.out.println(entry.getName());
      InputStream inputStream = zipFile.getInputStream(entry);

      String line = null;
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
      while ((line = br.readLine()) != null) {

        // System.out.println(line);
        // String whitespaceRemoved = line.replaceAll("\\s+", "");
        String[] pieces = line.split(" ");
        // System.out.println(Arrays.toString(pieces));

        LSHTC4Hierarchy lSHTC4Hierarchy = new LSHTC4Hierarchy();
        lSHTC4Hierarchy.setParentid(Integer.parseInt(pieces[0]));
        lSHTC4Hierarchy.setNodeid(Integer.parseInt(pieces[1]));
        LSHTC4HierarchyDAO.insert(lSHTC4Hierarchy);

        idx++;
      }
    }

    System.out.println("Number parsed: " + (idx - 1));

  }
}
