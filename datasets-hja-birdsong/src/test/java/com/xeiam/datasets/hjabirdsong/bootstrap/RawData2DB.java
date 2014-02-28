package com.xeiam.datasets.hjabirdsong.bootstrap;

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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.xeiam.datasets.hjabirdsong.HJABirdSong;
import com.xeiam.datasets.hjabirdsong.HJABirdsongDAO;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    HJABirdsongDAO.initTest();

    HJABirdsongDAO.dropTable();
    HJABirdsongDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing BagLabels data...");
    dp.go("./raw/hja_birdsong_bag_labels.txt", "./raw/id2filename.txt");

    System.out.println("done.");

    HJABirdsongDAO.release();
  }

  private void go(String labelsFile, String wavNameFile) throws IOException {

    List<String> labelsLines = FileUtils.readLines(new File(labelsFile), "UTF-8");
    List<String> wavNameLines = FileUtils.readLines(new File(wavNameFile), "UTF-8");

    for (int i = 0; i < labelsLines.size(); i++) {

      String labelLine = labelsLines.get(i);
      String wavNameLine = wavNameLines.get(i);

      System.out.println(labelLine);
      System.out.println(wavNameLine);

      String bagIDString = labelLine.substring(0, labelLine.indexOf(","));
      String labelsCSV = labelLine.substring(labelLine.indexOf(",") + 1, labelLine.length());

      String wavFileName = wavNameLine.substring(wavNameLine.indexOf(",") + 1, wavNameLine.length()) + ".wav";

      try {
        HJABirdSong bagLabels = new HJABirdSong();
        bagLabels.setBagid(Integer.parseInt(bagIDString));
        bagLabels.setLabels(labelsCSV);
        bagLabels.setWavfilename(wavFileName);
        HJABirdsongDAO.insert(bagLabels);
        System.out.println(bagLabels.toString());
        idx++;
      } catch (Exception e) {
        e.printStackTrace();
        // eat it. skip first line in file.
      }

    }

    System.out.println("Number parsed: " + idx);

  }
}
