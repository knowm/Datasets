package org.knowm.datasets.cifar10;

/**
 * @author timmolter
 */
public class CifarRaw {

  private final int label;
  private final int[][] redChannel;
  private final int[][] greenChannel;
  private final int[][] blueChannel;

  /**
   * Constructor
   * 
   * @param label
   * @param redChannel
   * @param greenChannel
   * @param blueChannel
   */
  public CifarRaw(int label, int[][] redChannel, int[][] greenChannel, int[][] blueChannel) {

    this.label = label;
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  public int getLabel() {

    return label;
  }

  public int[][] getRedChannel() {

    return redChannel;
  }

  public int[][] getGreenChannel() {

    return greenChannel;
  }

  public int[][] getBlueChannel() {

    return blueChannel;
  }

}
