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
package org.knowm.datasets.mnist.bootstrap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author alexnugent
 */
public class MnistFeatureFactory {

  public static Set<String> getThresholdedSet(int threshold, int[][] img) {

    Set<String> features = new HashSet<String>();

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        if (img[i][j] >= threshold) {
          String feature = i + "," + j;
          features.add(feature);
        }
      }
    }

    return features;
  }

  public static Set<String> getShiftedSet(Set<String> in, int dx, int dy) {

    Set<String> out = new HashSet<String>();
    for (String s : in) {

      String[] a = s.split(",");
      String s2 = (Integer.parseInt(a[0]) + dx) + "," + (Integer.parseInt(a[1]) + dy);
      out.add(s2);

    }

    return out;
  }

  public static Set<String> getShiftedSet(int threshold, int[][] img, int dx, int dy) {

    Set<String> features = new HashSet<String>();

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {

        int x = (i - dx) % img.length;
        int y = (j - dy) % img[0].length;
        if (x >= 0 && y >= 0 && x < img.length && y < img[0].length) {

          if (img[x][y] >= threshold) {
            String feature = x + "," + y;
            features.add(feature);
          }

        }

      }
    }

    return features;
  }

  public static List<String> getThresholded(int threshold, int[][] img) {

    List<String> features = new ArrayList<String>();

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        if (img[i][j] >= threshold) {
          String feature = i + "," + j;
          features.add(feature);
        }
      }
    }

    return features;
  }

  public static int[][] getThresholdedArray(int threshold, int[][] img) {

    int[][] out = new int[img.length][img[0].length];

    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[0].length; j++) {
        if (img[i][j] >= threshold) {
          out[i][j] = 1;
        }
      }
    }

    return out;
  }

}
