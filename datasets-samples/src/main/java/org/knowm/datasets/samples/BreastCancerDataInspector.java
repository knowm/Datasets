/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
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
