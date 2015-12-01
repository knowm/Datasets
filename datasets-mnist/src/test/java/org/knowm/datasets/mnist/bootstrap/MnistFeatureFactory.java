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
