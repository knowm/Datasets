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

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.knowm.datasets.common.business.Bean;

/**
 * @author timmolter
 */
public class Cifar extends Bean {

  private int label;
  private String imagedata;

  /** generated */
  int[][][] rgbImage = null;

  private static final Map<Integer, String> label2WordMap = new HashMap<Integer, String>();
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
  public BufferedImage getImageAsBufferedImage() {

	    BufferedImage bufferedImage = new BufferedImage(rgbImage.length, rgbImage[0].length, BufferedImage.TYPE_INT_RGB);

	    for (int x = 0; x < 32; x++) {
	      for (int y = 0; y < 32; y++) {
	        int value = rgbImage[x][y][0] << 16 | rgbImage[x][y][1] << 8 | rgbImage[x][y][2];
	        bufferedImage.setRGB(x, y, value);
	      }
	    }
	    return bufferedImage;
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

  public int[][][] getRGBImage() {

    if (rgbImage == null) {
      generateChannelData();
    }
    return rgbImage;
  }

  private void generateChannelData() {

	rgbImage = new int[32][32][3];

    String[] nonZeroPixels = imagedata.split(",");
    for (int i = 0; i < nonZeroPixels.length; i++) {
      String[] info = nonZeroPixels[i].split(":");
      int y = Integer.parseInt(info[0]);
      int x = Integer.parseInt(info[1]);
      String[] rgb = info[2].split("_");
      rgbImage[x][y][0] = Integer.parseInt(rgb[0]);
      rgbImage[x][y][1] = Integer.parseInt(rgb[1]);
      rgbImage[x][y][2] = Integer.parseInt(rgb[2]);
    }

  }

  @Override
  public String toString() {

    return "Cifar [id=" + getId() + ", label=" + label + "]";
  }

}
