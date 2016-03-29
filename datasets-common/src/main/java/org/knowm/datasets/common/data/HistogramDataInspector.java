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
package org.knowm.datasets.common.data;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.SwingWrapper;

/**
 * This class uses Java reflection to build histograms of float-type fields given two lists of DTOs. Extend the class, implement the methods, and you
 * will get a grid of histograms - one for each DTO field.
 *
 * @author timmolter
 */
public abstract class HistogramDataInspector<T> {

  Map<String, List<Float>> rawHistogramData1 = new HashMap<String, List<Float>>();
  Map<String, List<Float>> rawHistogramData2 = new HashMap<String, List<Float>>();

  public void go() throws IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

    // loop through all first samples, collecting all float valued properties
    List<T> firstElements = getFirstSamples();
    System.out.println("parsing first samples...");
    for (T iterable_element : firstElements) {
      Map<String, String> properties = BeanUtils.describe(iterable_element);
      addToMap(properties, rawHistogramData1);
    }
    // loop through all second samples, collecting all float valued properties
    List<T> secondElements = getSecondSamples();
    System.out.println("parsing second samples...");
    for (T iterable_element : secondElements) {
      Map<String, String> properties = BeanUtils.describe(iterable_element);
      addToMap(properties, rawHistogramData2);
    }
    // plot histograms
    plot();
  }

  public void addToMap(Map<String, String> properties, Map<String, List<Float>> rawHistogramData) {

    // System.out.println(Arrays.toString(properties.entrySet().toArray()));0
    for (Entry<String, String> entrySet : properties.entrySet()) {

      String key = entrySet.getKey();
      String value = entrySet.getValue();

      try {

        Float floatValue = Float.parseFloat(value);
        List<Float> rawDataForSingleProperty = rawHistogramData.get(key);
        if (rawDataForSingleProperty == null) {
          rawDataForSingleProperty = new ArrayList<Float>();
        }
        rawDataForSingleProperty.add(floatValue);
        rawHistogramData.put(key, rawDataForSingleProperty);

      } catch (NumberFormatException e) {
        e.printStackTrace();
        // wasn't a float, skip.
      }
    }
  }

  private void plot() {

    List<CategoryChart> charts = new ArrayList<CategoryChart>();

    for (Entry<String, List<Float>> entrySet : rawHistogramData1.entrySet()) {

      try {

        String key = entrySet.getKey();

        List<Float> value1 = rawHistogramData1.get(key);
        List<Float> value2 = rawHistogramData2.get(key);

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(300).height(200).title(key).xAxisTitle("Value").yAxisTitle("Count").build();
        chart.getStyler().setLegendVisible(false);

        float[] minMax = getMinMax(value1, value2);

        Histogram histogram1 = new Histogram(value1, 20, minMax[0], minMax[1]);
        Histogram histogram2 = new Histogram(value2, 20, minMax[0], minMax[1]);
        chart.addSeries(getFirstLabel(), histogram1.getxAxisData(), histogram1.getyAxisData());
        chart.addSeries(getSecondLabel(), histogram2.getxAxisData(), histogram2.getyAxisData());

        // Customize Chart
        chart.getStyler().setAvailableSpaceFill(.96);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setXAxisTitleVisible(false);
        chart.getStyler().setYAxisTitleVisible(false);

        charts.add(chart);

      } catch (Exception e) {
        // nop
        continue;
      }

    }
    new SwingWrapper(charts).displayChartMatrix();

  }

  private float[] getMinMax(List<Float> value1, List<Float> value2) {

    float[] minMax = new float[] { Float.MAX_VALUE, Float.MIN_VALUE };

    for (float f : value1) {
      if (f < minMax[0]) {
        minMax[0] = f;
      } else if (f > minMax[1]) {
        minMax[1] = f;
      }
    }

    for (float f : value2) {
      if (f < minMax[0]) {
        minMax[0] = f;
      } else if (f > minMax[1]) {
        minMax[1] = f;
      }
    }

    return minMax;
  }

  public abstract List<T> getFirstSamples();

  public abstract List<T> getSecondSamples();

  public abstract String getFirstLabel();

  public abstract String getSecondLabel();
}
