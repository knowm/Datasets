package com.xeiam.datasets.numenta.unit;

import java.util.ArrayList;
import java.util.Properties;

import com.xeiam.datasets.numenta.NumentaDAO;
import com.xeiam.datasets.numenta.SeriesPoint;
import com.xeiam.yank.Yank;

public class TimeSeriesDAOTest {

  public static void main(String[] args) {

    Properties dbProps = new Properties();
    dbProps.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/yank");
    dbProps.setProperty("username", "root");
    dbProps.setProperty("password", "");
    Yank.setupDataSource(dbProps);

    NumentaDAO.dropTable("test");
    NumentaDAO.createTable("test");

    SeriesPoint point1 = new SeriesPoint(10001, 11, 0);
    SeriesPoint point2 = new SeriesPoint(100002, 12, 1);
    SeriesPoint point3 = new SeriesPoint(1000003, 13, 1);
    SeriesPoint point4 = new SeriesPoint(10000004, 14, 0);
    SeriesPoint point5 = new SeriesPoint(100000005, 15, 1);

    NumentaDAO.insertSeriesPoint("test", point1);
    NumentaDAO.insertSeriesPoint("test", point2);
    NumentaDAO.insertSeriesPoint("test", point3);
    NumentaDAO.insertSeriesPoint("test", point4);
    NumentaDAO.insertSeriesPoint("test", point5);

    ArrayList<SeriesPoint> points = (ArrayList<SeriesPoint>) NumentaDAO.selectAll("test");

    assert (points.get(0).getValue() == point1.getValue() && points.get(0).getTimestamp() == point1.getTimestamp());
    assert (points.get(1).getValue() == point2.getValue() && points.get(1).getTimestamp() == point2.getTimestamp());
    assert (points.get(2).getValue() == point3.getValue() && points.get(2).getTimestamp() == point3.getTimestamp());
    assert (points.get(3).getValue() == point4.getValue() && points.get(3).getTimestamp() == point4.getTimestamp());
    assert (points.get(4).getValue() == point5.getValue() && points.get(4).getTimestamp() == point5.getTimestamp());

  }

}
