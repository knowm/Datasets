package com.xeiam.datasets.lshtc4;

/**
 * @author timmolter
 */
public class LSHTC4 {

  private int id;
  private String labels;
  private String features;

  /** generated */

  public String getLabels() {

    return labels;
  }

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public void setLabels(String labels) {

    this.labels = labels;
  }

  public String getFeatures() {

    return features;
  }

  public void setFeatures(String features) {

    this.features = features;
  }

  @Override
  public String toString() {

    return "LSHTC4 [id=" + id + ", labels=" + labels + ", features=" + features + "]";
  }

}
