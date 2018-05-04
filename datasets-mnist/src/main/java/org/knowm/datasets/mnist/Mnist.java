/**
 * (The MIT License)
 *
 * <p>Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors. Copyright 2013-2015 Xeiam
 * LLC (http://xeiam.com) and contributors.
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * This product currently only contains code developed by authors of specific components, as
 * identified by the source code files.
 *
 * <p>Since product implements StAX API, it has dependencies to StAX API classes.
 *
 * <p>For additional credits (generally to people who reported problems) see CREDITS file.
 */
package org.knowm.datasets.mnist;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.SQLException;

import org.knowm.datasets.common.business.Bean;

/** @author timmolter */
public class Mnist extends Bean {

  private int label;
  private Blob imgbytes;
  private byte[] imageAsByteArray;

  public int getLabel() {

    return label;
  }

  public void setLabel(int label) {

    this.label = label;
  }

  public Blob getImgbytes() {

    return imgbytes;
  }

  public void setImgbytes(Blob imgbytes) {

    this.imgbytes = imgbytes;
    try {
      this.imageAsByteArray = imgbytes.getBytes(1, (28 * 28));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public byte[][] getImageMatrix() {

    byte[][] imageMatrix = new byte[28][28];
    for (int y = 0; y < 28; y++) {
      for (int x = 0; x < 28; x++) {
        imageMatrix[x][y] = imageAsByteArray[28 * y + x];
      }
    }

    return imageMatrix;
  }

  public BufferedImage getImageAsBufferedImage() {

    byte[][] img = getImageMatrix();
    BufferedImage bufferedImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < img.length; x++) {
      for (int y = 0; y < img[0].length; y++) {
        int value = img[x][y] << 16 | img[x][y] << 8 | img[x][y];
        bufferedImage.setRGB(x, y, value);
      }
    }
    return bufferedImage;
  }

  public String toASCIIImageString() {

    byte[][] img = getImageMatrix();

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
