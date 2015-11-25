package com.xeiam.datasets.hjabirdsong;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.FileUtils;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    HJABirdsongDAO.init(args);

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

      // System.out.println(labelLine);
      // System.out.println(wavNameLine);

      String bagIDString = labelLine.substring(0, labelLine.indexOf(","));
      String labelsCSV = labelLine.substring(labelLine.indexOf(",") + 1, labelLine.length());

      String wavFileName = wavNameLine.substring(wavNameLine.indexOf(",") + 1, wavNameLine.length()) + ".wav";

      InputStream fis = null;
      try {
        // the Wav file Bytes
        File srcFile = new File("./raw/wav/" + wavFileName);
        fis = new FileInputStream(srcFile);
        byte[] buffer = new byte[(int) srcFile.length()];
        fis.read(buffer, 0, buffer.length);

        HJABirdSong hJABirdSong = new HJABirdSong();
        hJABirdSong.setBagid(Integer.parseInt(bagIDString));
        hJABirdSong.setLabels(labelsCSV);
        hJABirdSong.setWavfilename(wavFileName);
        hJABirdSong.setWavbytes(new SerialBlob(buffer));
        HJABirdsongDAO.insert(hJABirdSong);
        // System.out.println(hJABirdSong.toString());
        idx++;
      } catch (Exception e) {
        e.printStackTrace();
        // eat it. skip first line in file.
      } finally {
        if (fis != null) {
          fis.close();
        }
      }

    }
    System.out.println("Number parsed: " + idx);

  }
}
