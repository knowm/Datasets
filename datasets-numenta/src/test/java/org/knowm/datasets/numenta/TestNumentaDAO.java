package org.knowm.datasets.numenta;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.numenta.NumentaDAO;
import org.knowm.datasets.numenta.SeriesPoint;
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
