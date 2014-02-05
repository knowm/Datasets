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
package com.xeiam.datasets.cifar10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author timmolter
 */
public class Cifar {

  private int id;
  private int label;
  private String imagedata;
  private static final Map<Integer, String> label2WordMap = new HashMap<Integer, String>();

  /** generated */
  int[][] redChannel = null;
  int[][] greenChannel = null;
  int[][] blueChannel = null;

  static {
    label2WordMap.put(0, "airplane");
    label2WordMap.put(1, "automobile");
    label2WordMap.put(2, "bird");
    label2WordMap.put(3, "cat");
    label2WordMap.put(4, "deer");
    label2WordMap.put(5, "dog");
    label2WordMap.put(6, "frog");
    label2WordMap.put(7, "horse");
    label2WordMap.put(8, "ship");
    label2WordMap.put(9, "truck");
  }

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public int getLabel() {

    return label;
  }

  public String getLabelWord() {

    return label2WordMap.get(label);
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

  public int[][] getRedChannel() {

    if (redChannel == null) {
      generateChannelData();
    }
    return redChannel;
  }

  public int[][] getGreenChannel() {

    if (greenChannel == null) {
      generateChannelData();
    }
    return greenChannel;
  }

  public int[][] getBlueChannel() {

    if (blueChannel == null) {
      generateChannelData();
    }
    return blueChannel;
  }

  private void generateChannelData() {

    redChannel = new int[32][32];
    greenChannel = new int[32][32];
    blueChannel = new int[32][32];

    String[] nonZeroPixels = imagedata.split(",");
    for (int i = 0; i < nonZeroPixels.length; i++) {
      String[] info = nonZeroPixels[i].split(":");
      int x = Integer.parseInt(info[0]);
      int y = Integer.parseInt(info[1]);
      String[] rgb = info[2].split("_");
      redChannel[x][y] = Integer.parseInt(rgb[0]);
      greenChannel[x][y] = Integer.parseInt(rgb[1]);
      blueChannel[x][y] = Integer.parseInt(rgb[2]);
    }

    // /**
    // * Given the String encoded channel, generate and return a int[][]
    // *
    // * @param channel
    // * @return
    // */
    // public int[][] getImageMatrix(String channel) {
    //
    // int[][] imageMatrix = new int[32][32];
    // String[] nonZeroPixels = channel.split(",");
    // for (int i = 0; i < nonZeroPixels.length; i++) {
    // String[] info = nonZeroPixels[i].split(":");
    // int x = Integer.parseInt(info[0]);
    // int y = Integer.parseInt(info[1]);
    // int magnitude = Integer.parseInt(info[2]);
    // imageMatrix[x][y] = magnitude;
    // }
    //
    // return imageMatrix;
    // }

    // public void generateGrayChannel() {
    //
    // int[][] redChannelMatrix = getImageMatrix(redChannel);
    // int[][] greenChannelMatrix = getImageMatrix(greenChannel);
    // int[][] blueChannelMatrix = getImageMatrix(blueChannel);
    // int[][] grayChannelMatrix = new int[32][32];
    //
    // StringBuilder sb = new StringBuilder();
    //
    // for (int i = 0; i < grayChannelMatrix[0].length; i++) {
    // for (int j = 0; j < grayChannelMatrix.length; j++) {
    // sb.append(i);
    // sb.append(":");
    // sb.append(j);
    // sb.append(":");
    // sb.append((redChannelMatrix[i][j] + greenChannelMatrix[i][j] + blueChannelMatrix[i][j]) / 3);
    // sb.append(",");
    // }
    // }
    // grayChannel = sb.toString();
    // }
  }
}
