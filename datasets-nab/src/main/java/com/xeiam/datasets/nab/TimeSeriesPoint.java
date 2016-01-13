package com.xeiam.datasets.nab;

import java.sql.Date;

public class TimeSeriesPoint {

  private double value;
  private Date timestamp;

  public TimeSeriesPoint(Date timestamp, double value) {

    this.value = value;
    this.timestamp = timestamp;
  }

  public double getValue() {

    return value;
  }

  public Date getDate() {

    return timestamp;
  }

  public void setValue(double value) {

    this.value = value;
  }

  public void setDate(Date date) {

    this.timestamp = date;
  }

  @Override
  public String toString() {

    return "TimeSeriesPoint [value=" + value + ", date=" + timestamp + "]";
  }

}
