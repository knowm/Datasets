/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
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

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @author timmolter
 */
public class Mnist implements Serializable {

  private int id;
  private int label;
  private String imagedata;

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public int getLabel() {

    return label;
  }

  public void setLabel(int label) {

    this.label = label;
  }

  public String getImagedata() {

    return imagedata;
  }

  public void setImagedata(String imagedata) {

    this.imagedata = imagedata;
  }

  public int[][] getImageMatrix() {

    int[][] imageMatrix = new int[28][28];
    String[] nonZeroPixels = imagedata.split(",");
    for (int i = 0; i < nonZeroPixels.length; i++) {
      String[] info = nonZeroPixels[i].split(":");
      int x = Integer.parseInt(info[0]);
      int y = Integer.parseInt(info[1]);
      int magnitude = Integer.parseInt(info[2]);
      imageMatrix[x][y] = magnitude;
    }

    return imageMatrix;
  }

  public BufferedImage getImageAsBufferedImage() {

    int[][] img = getImageMatrix();
    BufferedImage bufferedImage = new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < img.length; y++) {
      for (int x = 0; x < img[0].length; x++) {
        int value = img[y][x] << 16 | img[y][x] << 8 | img[y][x];
        bufferedImage.setRGB(x, y, value);
      }
    }
    return bufferedImage;
  }

  public String toASCIIImageString() {

    int[][] img = getImageMatrix();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img.length; j++) {
        if (img[i][j] > 10) {
          sb.append("**");
        } else {
          sb.append("  ");
        }
      }
      sb.append(System.getProperty("line.separator"));
    }
    return sb.toString();

  }

}
