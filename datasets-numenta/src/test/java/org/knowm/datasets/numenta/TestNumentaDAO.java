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

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;

@Ignore
public class TestNumentaDAO {

  @BeforeClass
  public static void setUpDB() {

    Yank.setupDataSource(PropertiesUtils.getPropertiesFromClasspath("DB_HSQLDB_FILE.properties"));
  }

  @AfterClass
  public static void tearDownDB() {

    NumentaDAO.release();
  }

  @Test
  public void testSelectCount() {

    List<SeriesPoint> points = NumentaDAO.selectAll();
    assert (!points.isEmpty());
  }

  @Test
  public void testOrdering() {

    List<String> names = NumentaDAO.selectSeriesNames();
    for (String name : names) {
      long last = Long.MIN_VALUE;
      List<SeriesPoint> points = NumentaDAO.selectSeries(name);
      for (SeriesPoint p : points) {
        assert (p.getTimestamp() > last);
      }
    }

  }

  @Test
  public void testSeriesCount() {

    assert (NumentaDAO.selectSeries("TravelTime_387").size() == 2500);
    assert (NumentaDAO.selectSeries("TravelTime_451").size() == 2162);
    assert (NumentaDAO.selectSeries("Twitter_volume_AAPL").size() == 15902);
    assert (NumentaDAO.selectSeries("art_daily_jumpsup").size() == 4032);
    assert (NumentaDAO.selectSeries("ec2_cpu_utilization_825cc2").size() == 4032);
    assert (NumentaDAO.selectSeries("exchange_2_cpm_results").size() == 1624);
    assert (NumentaDAO.selectSeries("speed_6005").size() == 2500);
  }

  @Test
  public void testSeriesNames() {

    List<String> names = NumentaDAO.selectSeriesNames();
    for (String name : names) {
      System.out.println(name);
    }
    assert (!names.isEmpty());
  }

}
