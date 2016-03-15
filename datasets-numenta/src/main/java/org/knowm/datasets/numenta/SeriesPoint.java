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
