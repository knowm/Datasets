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
package org.knowm.datasets.cifar10;

import java.io.IOException;

/**
 * @author timmolter
 */
public class RawData2DB {

  private static final int NumEntriesPerFile = 10000;

  int idx = 0;

  public static void main(String[] args) throws IOException {

    CifarDAO.init(args);

    CifarDAO.dropTable();
    CifarDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing Cifar training images 1...");
    dp.go("./raw/data_batch_1.bin");
    System.out.println("processing Cifar training images 2...");
    dp.go("./raw/data_batch_2.bin");
    System.out.println("processing Cifar training images 3...");
    dp.go("./raw/data_batch_3.bin");
    System.out.println("processing Cifar training images 4...");
    dp.go("./raw/data_batch_4.bin");
    System.out.println("processing Cifar training images 5...");
    dp.go("./raw/data_batch_5.bin");
    // System.out.println("processing Cifar test images...");
    dp.go("./raw/test_batch.bin");
    System.out.println("done.");

    CifarDAO.release();
  }

  private void go(String imageDataFile) throws IOException {

    int longestStringLength = 0;

    CifarManager cifarManager = new CifarManager(imageDataFile);
    for (int n = 1; n <= NumEntriesPerFile; n++) {

      cifarManager.setCurrent(n); // index of the image that we are interested in
      CifarRaw cifarRaw = cifarManager.readImage();
      // System.out.println(cifarRaw.getLabel());

      Cifar cifar = new Cifar();
      cifar.setId(idx++);
      cifar.setLabel(cifarRaw.getLabel());

      String imagedata = getChannelData(cifarRaw);
      // System.out.println(imagedata);
      if (imagedata.length() > longestStringLength) {
        longestStringLength = imagedata.length();
      }
      cifar.setImagedata(imagedata);

      CifarDAO.insert(cifar);
    }
    System.out.println("longestStringLength: " + longestStringLength);
  }

  private String getChannelData(CifarRaw cifarRaw) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < cifarRaw.getRedChannel()[0].length; i++) {
      for (int j = 0; j < cifarRaw.getRedChannel().length; j++) {
        if (cifarRaw.getRedChannel()[i][j] > 0) {
          sb.append(i);
          sb.append(":");
          sb.append(j);
          sb.append(":");
          sb.append(cifarRaw.getRedChannel()[i][j]);
          sb.append("_");
          sb.append(cifarRaw.getGreenChannel()[i][j]);
          sb.append("_");
          sb.append(cifarRaw.getBlueChannel()[i][j]);
          sb.append(",");
        }
      }
    }
    return sb.toString();
  }
}
