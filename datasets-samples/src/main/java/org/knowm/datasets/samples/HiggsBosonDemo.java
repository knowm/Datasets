package org.knowm.datasets.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.knowm.datasets.common.data.HistogramDataInspector;
import org.knowm.datasets.higgsboson.HiggsBoson;
import org.knowm.datasets.higgsboson.HiggsBosonDAO;

/**
 * @author timmolter
 */
public class HiggsBosonDemo extends HistogramDataInspector<HiggsBoson> {

  public static void main(String[] args) {

    try {
      HiggsBosonDAO.init("/usr/local/Datasets"); // setup data

      HiggsBosonDemo higgsBosonInspector = new HiggsBosonDemo();
      higgsBosonInspector.go();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      HiggsBosonDAO.release(); // release data resources
    }
  }

  @Override
  public List<HiggsBoson> getFirstSamples() {

    return HiggsBosonDAO.selectSignal();
  }

  @Override
  public List<HiggsBoson> getSecondSamples() {

    return HiggsBosonDAO.selectBackground();
  }

  @Override
  public String getFirstLabel() {

    return "Signal";
  }

  @Override
  public String getSecondLabel() {

    return "Background";
  }

  /**
   * Overriding this to weed out the -999.0 values in the dataset
   */
  @Override
  public void addToMap(Map<String, String> properties, Map<String, List<Float>> rawHistogramData) {

    // System.out.println(Arrays.toString(properties.entrySet().toArray()));0
    for (Entry<String, String> entrySet : properties.entrySet()) {

      String key = entrySet.getKey();
      String value = entrySet.getValue();

      try {

        Float floatValue = Float.parseFloat(value);
        if (floatValue > -900.0f) {
          List<Float> rawDataForSingleProperty = rawHistogramData.get(key);
          if (rawDataForSingleProperty == null) {
            rawDataForSingleProperty = new ArrayList<Float>();
          }
          rawDataForSingleProperty.add(floatValue);
          rawHistogramData.put(key, rawDataForSingleProperty);
        }
      } catch (NumberFormatException e) {
        // wasn't a float, skip.
      }
    }
  }
}
