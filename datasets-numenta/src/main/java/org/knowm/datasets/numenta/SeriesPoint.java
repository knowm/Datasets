package org.knowm.datasets.numenta;

import java.util.Date;

public class SeriesPoint {

  private long id;
  private String seriesGroup;
  private String seriesName;
  private long timestamp;
  private double value;
  private int label;

  public SeriesPoint() {

  }

  public SeriesPoint(long id, String seriesGroup, String seriesName, long timestamp, double value, int label) {

    this.id = id;
    this.seriesGroup = seriesGroup;
    this.seriesName = seriesName;
    this.value = value;
    this.timestamp = timestamp;
    this.label = label;
  }

  public long getId() {

    return id;
  }

  public String getSeriesGroup() {

    return seriesGroup;
  }

  public String getSeriesName() {

    return seriesName;
  }

  public long getTimestamp() {

    return timestamp;
  }

  public Date getDate() {

    return new Date(timestamp);
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

  public void setSeriesGroup(String seriesGroup) {

    this.seriesGroup = seriesGroup;
  }

  public void setSeriesName(String seriesName) {

    this.seriesName = seriesName;
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

    return "SeriesPoint [id=" + id + " ," + "seriesGroup=" + seriesGroup + " ," + "seriesName=" + seriesName + " ," + "timestamp=" + timestamp + " ," + "value=" + value + " ," + "label=" + label
        + "]";
  }

}
