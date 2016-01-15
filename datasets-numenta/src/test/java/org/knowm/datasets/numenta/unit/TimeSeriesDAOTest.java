package org.knowm.datasets.numenta.unit;

import java.util.ArrayList;

import org.knowm.datasets.numenta.NumentaDAO;
import org.knowm.datasets.numenta.SeriesPoint;

public class TimeSeriesDAOTest {

  public static void main(String[] args) {

    NumentaDAO.init("/usr/local/Datasets");
    SeriesPoint point1 = new SeriesPoint(10001, 11, 0);
    SeriesPoint point2 = new SeriesPoint(100002, 12, 1);
    SeriesPoint point3 = new SeriesPoint(1000003, 13, 1);
    SeriesPoint point4 = new SeriesPoint(10000004, 14, 0);
    SeriesPoint point5 = new SeriesPoint(100000005, 15, 1);

    NumentaDAO.insertSeriesPoint("Twitter_volume_AAPL", point1);
    NumentaDAO.insertSeriesPoint("Twitter_volume_AAPL", point2);
    NumentaDAO.insertSeriesPoint("Twitter_volume_AAPL", point3);
    NumentaDAO.insertSeriesPoint("Twitter_volume_AAPL", point4);
    NumentaDAO.insertSeriesPoint("Twitter_volume_AAPL", point5);

    ArrayList<SeriesPoint> points = (ArrayList<SeriesPoint>) NumentaDAO.selectAll("test");

    assert (points.get(0).getValue() == point1.getValue() && points.get(0).getTimestamp() == point1.getTimestamp());
    assert (points.get(1).getValue() == point2.getValue() && points.get(1).getTimestamp() == point2.getTimestamp());
    assert (points.get(2).getValue() == point3.getValue() && points.get(2).getTimestamp() == point3.getTimestamp());
    assert (points.get(3).getValue() == point4.getValue() && points.get(3).getTimestamp() == point4.getTimestamp());
    assert (points.get(4).getValue() == point5.getValue() && points.get(4).getTimestamp() == point5.getTimestamp());

  }

}
