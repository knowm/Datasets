package org.knowm.datasets.samples;

import java.util.ArrayList;
import java.util.List;

import org.knowm.datasets.breastcancerwisconsinorginal.BreastCancer;
import org.knowm.datasets.breastcancerwisconsinorginal.BreastCancerDAO;
import org.knowm.datasets.common.data.HistogramDataInspector;

/**
 * @author timmolter
 */
public class BreastCancerDataInspector extends HistogramDataInspector<BreastCancer> {

  private List<BreastCancer> malignant = new ArrayList<BreastCancer>();
  private List<BreastCancer> benign = new ArrayList<BreastCancer>();

  public static void main(String[] args) {

    try {
      BreastCancerDAO.init("/usr/local/Datasets/"); // setup data
      // TRAIN_TEST_SPLIT = EventDAO.selectCount() * 4 / 5;

      BreastCancerDataInspector breastCancerDataInspector = new BreastCancerDataInspector();
      breastCancerDataInspector.generateTrainAndTestArraysTruncated();
      breastCancerDataInspector.go();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BreastCancerDAO.release(); // release data resources
    }
  }

  private void generateTrainAndTestArraysTruncated() {

    int numSamples = (int) BreastCancerDAO.selectCount();
    for (int i = 0; i < numSamples; i++) {

      BreastCancer breastCancer = BreastCancerDAO.selectSingle(i);
      if (breastCancer.getCellClass() == 4) {
        malignant.add(breastCancer);
      } else {
        benign.add(breastCancer);
      }
    }
  }

  @Override
  public List<BreastCancer> getFirstSamples() {

    return malignant;
  }

  @Override
  public List<BreastCancer> getSecondSamples() {

    return benign;
  }

  @Override
  public String getFirstLabel() {

    return "Malignant";
  }

  @Override
  public String getSecondLabel() {

    return "Benign";
  }

}
