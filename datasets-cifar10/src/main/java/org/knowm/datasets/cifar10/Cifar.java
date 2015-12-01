package org.knowm.datasets.cifar10;

import java.util.Arrays;
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

  }

  @Override
  public String toString() {

    return "Cifar [id=" + id + ", label=" + label + ", imagedata=" + imagedata + ", redChannel=" + Arrays.toString(redChannel) + ", greenChannel="
        + Arrays.toString(greenChannel) + ", blueChannel=" + Arrays.toString(blueChannel) + "]";
  }

}
