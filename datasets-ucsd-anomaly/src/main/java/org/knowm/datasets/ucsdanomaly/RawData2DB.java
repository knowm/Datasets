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
package org.knowm.datasets.ucsdanomaly;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.FileUtils;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 1; // one-based index

  public static void main(String[] args) throws IOException, SerialException, SQLException {

    UCSDAnomalyDAO.init(args);

    UCSDAnomalyDAO.dropTable();
    UCSDAnomalyDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing data...");
    dp.go("./raw/UCSDped1/Train/", null);
    dp.go("./raw/UCSDped1/Test/", "UCSDped1.m");
    // dp.go("./raw/UCSDped2/Train/");
    // dp.go("./raw/UCSDped2/Test/");

    System.out.println("done.");

    UCSDAnomalyDAO.release();
  }

  private void go(String dirpath, String anomalyFile) throws IOException, SerialException, SQLException {

    File root = new File(dirpath);
    String[] directories = root.list();

    for (String directory : directories) {
      if (new File(dirpath + directory).isDirectory() && !(dirpath + directory).endsWith("_gt")) {
        System.out.println(directory);

        File pngFileDir = new File(dirpath + directory);
        String[] pngFiles = pngFileDir.list();
        for (String pngFile : pngFiles) {

          String pngFileName = dirpath + directory + "/" + pngFile;
          if (new File(pngFileName).isFile() && (pngFileName).endsWith(".png")) {
            InputStream fis = null;
            try {
              // the png file Bytes
              File srcFile = new File(pngFileName);

              fis = new FileInputStream(srcFile);
              byte[] buffer = new byte[(int) srcFile.length()];
              // System.out.println(buffer.length);
              fis.read(buffer, 0, buffer.length);
              // System.out.println("   " + pngFile);
              UCSDAnomaly uCSDAnomaly = new UCSDAnomaly();
              uCSDAnomaly.setId(idx);
              int tifid = Integer.parseInt(pngFile.substring(0, pngFile.indexOf(".")));
              uCSDAnomaly.setTifid(tifid);
              uCSDAnomaly.setTifbytes((new SerialBlob(buffer)));

              uCSDAnomaly.setIsanomaly(isAnomaly(dirpath, anomalyFile, idx, tifid));
              UCSDAnomalyDAO.insert(uCSDAnomaly);
            } catch (Exception e) {
              e.printStackTrace();
            } finally {
              if (fis != null) {
                fis.close();
              }
            }
          }
        }
        idx++;
      }
    }

    System.out.println("Number parsed: " + (idx - 1));

  }

  private boolean isAnomaly(String dirpath, String anomalyFileName, int idx, int tifid) throws IOException {

    if (anomalyFileName == null) {
      return false;
    } else {
      File anomalyFile = new File(dirpath + anomalyFileName);
      List<String> labelsLines = FileUtils.readLines(anomalyFile, "UTF-8");
      String line = labelsLines.get(idx - UCSDAnomalyDAO.getTrainTestSplit());
      // System.out.println(line);
      String target = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
      String[] blocks = target.split(","); // [5:90, 140:200];
      for (int i = 0; i < blocks.length; i++) {
        String[] range = blocks[i].split(":");
        int lowerBound = Integer.parseInt(range[0].trim());
        int upperBound = Integer.parseInt(range[1].trim());
        if (tifid >= lowerBound && tifid <= upperBound) {
          return true;
        }
      }

      return false;
    }

  }
}
