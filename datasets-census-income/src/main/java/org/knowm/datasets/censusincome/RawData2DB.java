package org.knowm.datasets.censusincome;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Parses Census Income text files and put the data in a database
 *
 * @author timmolter
 */
public class RawData2DB {

  int maxBodyLength = 0;
  int idx = 0;

  public static void main(String[] args) throws IOException {

    CensusIncomeDAO.init(args);

    CensusIncomeDAO.dropTable();
    CensusIncomeDAO.createTable();

    RawData2DB dp = new RawData2DB();
    dp.go("./raw/adult.data");
    dp.go("./raw/adult.test");

    CensusIncomeDAO.release();
  }

  private void go(String file) throws IOException {

    String data = FileUtils.readFileToString(new File(file), "UTF-8");
    System.out.println("loading data from " + file);

    String[] lines = data.split("\\r?\\n");

    for (int i = 0; i < lines.length; i++) {
      try {

        String[] entry = lines[i].split(",");
        CensusIncome censusIncome = new CensusIncome();

        censusIncome.setId(idx);
        censusIncome.setAge(Integer.parseInt(entry[0].trim()));
        censusIncome.setWorkclass(entry[1].trim());
        censusIncome.setFnlwgt(Integer.parseInt(entry[2].trim()));
        censusIncome.setEducation(entry[3].trim());
        censusIncome.setEducationNum(Integer.parseInt(entry[4].trim()));
        censusIncome.setMaritalStatus(entry[5].trim());
        censusIncome.setOccupation(entry[6].trim());
        censusIncome.setRelationship(entry[7].trim());
        censusIncome.setRace(entry[8].trim());
        censusIncome.setSex(entry[9].trim());
        censusIncome.setCapitalGain(Integer.parseInt(entry[10].trim()));
        censusIncome.setCapitalLoss(Integer.parseInt(entry[11].trim()));
        censusIncome.setHoursPerWeek(Integer.parseInt(entry[12].trim()));
        censusIncome.setNativeCountry(entry[13].trim());

        if (entry[14].trim().equalsIgnoreCase("<=50K") | entry[14].trim().equalsIgnoreCase("<=50K.")) {
          censusIncome.setIncomeLessThan50k(true);
        } else if (entry[14].trim().equalsIgnoreCase(">50K") | entry[14].trim().equalsIgnoreCase(">50K.")) {
          censusIncome.setIncomeLessThan50k(false);
        }

        // System.out.println(censusIncome.isIncomeLessThan50k());
        CensusIncomeDAO.insert(censusIncome);
        System.out.println(censusIncome.getId());
        idx++;

      } catch (Exception e) {
        // eat it. Will throw exception on the first line of the test dataset.
        System.out.println("error parsing one line.");
      }
    }

  }

}
