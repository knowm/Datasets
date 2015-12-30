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
package org.knowm.datasets.mnist;

import java.io.IOException;

import mnist.tools.MnistManager;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    MnistDAO.init(new String[0]);

    MnistDAO.dropTable();
    MnistDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing MNIST training images...");
    dp.go("./raw/train-images-idx3-ubyte", "./raw/train-labels-idx1-ubyte", 1, 60000);
    System.out.println("processing MNIST test images...");
    dp.go("./raw/t10k-images-idx3-ubyte", "./raw/t10k-labels-idx1-ubyte", 1, 10000);
    System.out.println("done.");

    MnistDAO.release();
  }

  private void go(String imageDataFile, String labelDataFile, int startIdx, int endIdx) throws IOException {

    int longestStringLength = 0;

    MnistManager mnistManager = new MnistManager(imageDataFile, labelDataFile);
    for (int n = startIdx; n <= endIdx; n++) {
      mnistManager.setCurrent(n); // index of the image that we are interested in
      StringBuilder sb = new StringBuilder();
      int[][] image = mnistManager.readImage();
      for (int i = 0; i < image[0].length; i++) {
        for (int j = 0; j < image.length; j++) {
          if (image[i][j] > 0) {
            sb.append(i);
            sb.append(":");
            sb.append(j);
            sb.append(":");
            sb.append(image[i][j]);
            sb.append(",");
          }
        }
      }
      String imagedata = sb.toString();
      // System.out.println(imagedata);
      if (imagedata.length() > longestStringLength) {
        longestStringLength = imagedata.length();
      }
      Mnist mnist = new Mnist();
      mnist.setId(idx++);
      mnist.setLabel(mnistManager.readLabel());
      mnist.setImagedata(imagedata);
      MnistDAO.insert(mnist);
    }
    // System.out.println("longestStringLength: " + longestStringLength);
  }
}
