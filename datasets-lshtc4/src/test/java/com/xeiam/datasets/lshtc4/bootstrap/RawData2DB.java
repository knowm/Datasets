package com.xeiam.datasets.lshtc4.bootstrap;

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

import com.xeiam.datasets.lshtc4.LSHTC4;
import com.xeiam.datasets.lshtc4.LSHTC4DAO;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 0;
  int longestLabelsStringLength = 0;
  int longestFeaturesStringLength = 0;
  int highestFeatureID = 0;
  int highestFeatureValue = 0;
  int highestLabelID = 0;

  public static void main(String[] args) throws IOException {

    LSHTC4DAO.initTest();

    LSHTC4DAO.dropTable();
    LSHTC4DAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing LSHTC4 test images...");
    dp.go("./raw/test-remapped.zip");

    System.out.println("processing LSHTC4 train images...");
    dp.go("./raw/train-remapped.zip");

    System.out.println("done.");

    LSHTC4DAO.testRelease();
  }

  private void go(String dataFile) throws IOException {

    ZipFile zipFile = new ZipFile(dataFile);

    Enumeration<? extends ZipEntry> entries = zipFile.entries();

    while (entries.hasMoreElements()) { // there's actually only one entry
      ZipEntry entry = entries.nextElement();
      System.out.println(entry.getName());
      boolean isTest = entry.getName().equals("test-remapped.csv");
      InputStream inputStream = zipFile.getInputStream(entry);

      String line = null;
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
      while ((line = br.readLine()) != null) {
        if (idx > 0) { // skip first line
          // System.out.println(line);
          // String whitespaceRemoved = line.replaceAll("\\s+", "");
          String[] pieces = line.split(" ");
          // System.out.println(Arrays.toString(pieces));}

          if (isTest) { // test parsing
            int id = Integer.parseInt(pieces[0].split(",")[0]);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < pieces.length; i++) {
              sb.append(pieces[i] + ",");
              getStats(pieces[i]);
            }
            insertLSHTC4(id, sb.toString(), "");
          }
          else { // train parsing

            int id = idx;

            int counter = 0;
            String label = "";
            StringBuilder labels = new StringBuilder();
            do {
              label = pieces[counter];
              labels.append(label);
              counter++;
            } while (label.contains(","));

            StringBuilder features = new StringBuilder();
            for (int i = counter; i < pieces.length; i++) {
              features.append(pieces[i] + ",");
              getStats(pieces[i]);

            }

            insertLSHTC4(id, features.toString(), labels.toString());
          }

        }
        idx++;
      }
    }

    System.out.println("longestLabelsStringLength: " + longestLabelsStringLength);
    System.out.println("longestFeaturesStringLength: " + longestFeaturesStringLength);
    System.out.println("highestFeatureID: " + highestFeatureID);
    System.out.println("highestFeatureValue: " + highestFeatureValue);
    System.out.println("highestLabelID: " + highestLabelID);
    System.out.println("Number parsed: " + (idx - 1));

  }

  private void insertLSHTC4(int id, String features, String labels) {

    LSHTC4 lSHTC4 = new LSHTC4();
    lSHTC4.setId(id);
    lSHTC4.setFeatures(features);
    lSHTC4.setLabels(labels);

    if (features.length() > longestFeaturesStringLength) {
      longestFeaturesStringLength = features.length();
    }
    if (labels.length() > longestLabelsStringLength) {
      longestLabelsStringLength = labels.length();
    }

    if (id > highestLabelID) {
      highestLabelID = id;
    }

    // System.out.println(lSHTC4.toString());
    LSHTC4DAO.insert(lSHTC4);
  }

  private void getStats(String featureAndValue) {

    String[] featureValuePair = featureAndValue.split(":");
    int feature = Integer.parseInt(featureValuePair[0]);
    int value = Integer.parseInt(featureValuePair[1]);

    if (feature > highestFeatureID) {
      highestFeatureID = feature;
    }

    if (value > highestFeatureValue) {
      highestFeatureValue = value;
    }
  }

}
