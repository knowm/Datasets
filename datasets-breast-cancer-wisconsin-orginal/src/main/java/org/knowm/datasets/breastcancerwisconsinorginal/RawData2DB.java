package org.knowm.datasets.breastcancerwisconsinorginal;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Parses Breast Cancer text files and put the data in a database
 *
 * @author timmolter
 */
public class RawData2DB {

  int maxBodyLength = 0;
  int idx = 0;

  public static void main(String[] args) throws IOException {

    BreastCancerDAO.init(args);

    BreastCancerDAO.dropTable();
    BreastCancerDAO.createTable();

    RawData2DB dp = new RawData2DB();
    dp.go("./raw/breast-cancer-wisconsin.data");

    BreastCancerDAO.release();

  }

  private void go(String file) throws IOException {

    String data = FileUtils.readFileToString(new File(file), "UTF-8");

    System.out.println("loading data from " + file);

    String[] lines = data.split("\\r?\\n");

    for (int i = 0; i < lines.length; i++) {
      try {
        if (!lines[i].contains("?")) { // remove unknowns to compare with published benchmarks

          String[] entry = lines[i].split(",");
          BreastCancer breastCancer = new BreastCancer();

          breastCancer.setId(idx++);
          breastCancer.setSampleCodeNumber(Integer.parseInt(entry[0]));
          breastCancer.setClumpThickness(Integer.parseInt(entry[1]));
          breastCancer.setUniformityOfCellSize(Integer.parseInt(entry[2]));
          breastCancer.setUniformityOfCellShape(Integer.parseInt(entry[3]));
          breastCancer.setMarginalAdhesion(Integer.parseInt(entry[4]));
          breastCancer.setSingleEpithelialCellSize(Integer.parseInt(entry[5]));
          breastCancer.setBareNuclei(Integer.parseInt(entry[6]));
          breastCancer.setBlandChromatin(Integer.parseInt(entry[7]));
          breastCancer.setNormalNucleoli(Integer.parseInt(entry[8]));
          breastCancer.setMitoses(Integer.parseInt(entry[9]));
          breastCancer.setCellClass(Integer.parseInt(entry[10]));

          BreastCancerDAO.insert(breastCancer);
        } else {
          System.out.println("Skipping: " + lines[i]);
        }

      } catch (Exception e) {
        System.out.println("error parsing one line.");
        // eat it. Will throw exception on the first line of the test dataset.
      }
    }

  }

}
