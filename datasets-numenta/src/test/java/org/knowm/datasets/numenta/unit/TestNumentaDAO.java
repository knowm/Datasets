package org.knowm.datasets.numenta.unit;

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

}
