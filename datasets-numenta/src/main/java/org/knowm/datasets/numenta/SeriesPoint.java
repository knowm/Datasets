package org.knowm.datasets.numenta;

import java.util.Date;

public class SeriesPoint {

  private long id;
  private String series;
  private long timestamp;
  private double value;
  private int label;

  public SeriesPoint() {

  }

  public SeriesPoint(long id, String series, long timestamp, double value, int label) {

    this.id = id;
    this.series = series;
    this.value = value;
    this.timestamp = timestamp;
    this.label = label;
  }

  public long getId() {

    return id;
  }

  public String getSeries() {

    return series;
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

  public void setId(long id) {

    this.id = id;
  }

  public void setSeries(String series) {

    this.series = series;
  }

  public void setTimestamp(long timestamp) {

    this.timestamp = timestamp;
  }

  public void setValue(double value) {

    this.value = value;
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

    return "SeriesPoint [id=" + id + " ," + "series=" + series + " ," + "timestamp=" + timestamp + " ," + "value=" + value + " ," + "label=" + label + "]";
  }

}
