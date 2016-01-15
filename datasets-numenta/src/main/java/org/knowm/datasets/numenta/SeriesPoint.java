package org.knowm.datasets.numenta;

import java.util.Date;

public class SeriesPoint {

  private long timestamp;
  private double value;
  private int label;

  public SeriesPoint() {

  }

  public SeriesPoint(long timestamp, double value, int label) {

    this.value = value;
    this.timestamp = timestamp;
    this.label = label;
  }

  public long getTimestamp() {

    return timestamp;
  }

  public double getValue() {

    return value;
  }

  public int getLabel() {

    return label;
  }

  public void setValue(double value) {

    this.value = value;
  }

  public void setTimestamp(long timestamp) {

    this.timestamp = timestamp;
  }

  public void setLabel(int label) {

    this.label = label;
  }

  public Date getTimestampAsDate() {

    return new Date(this.timestamp);
  }

  public void setTimestampAsDate(Date timestamp) {

    this.timestamp = timestamp.getTime();
  }

  @Override
  public String toString() {

    return "SeriesPoint [timestamp=" + timestamp + " ," + "value=" + value + " ," + "label=" + label + "]";
  }

}
