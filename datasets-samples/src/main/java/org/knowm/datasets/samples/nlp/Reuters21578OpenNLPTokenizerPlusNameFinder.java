package org.knowm.datasets.samples.nlp;

import java.util.Arrays;
import java.util.List;

import org.knowm.datasets.common.nlp.OpenNLPBasicUtils;
import org.knowm.datasets.common.nlp.OpenNLPNameFinderUtils;
import org.knowm.datasets.reuters21578.Reuters21578;
import org.knowm.datasets.reuters21578.Reuters21578DAO;

/**
 * @author timmolter
 */
public class Reuters21578OpenNLPTokenizerPlusNameFinder {

  public static void main(String[] args) {

    try {
      Reuters21578DAO.init("/usr/local/Datasets"); // setup data
      List<Reuters21578> reuters21578List = Reuters21578DAO.selectRandomList(1);

      Reuters21578 reuters21578 = reuters21578List.get(0);

      String[] words = OpenNLPBasicUtils.tokenize(reuters21578.getBody());
      System.out.println("words: " + Arrays.toString(words));

      String[] persons = OpenNLPNameFinderUtils.findPersons(reuters21578.getBody());
      System.out.println("persons: " + Arrays.toString(persons));

      String[] dates = OpenNLPNameFinderUtils.findDates(reuters21578.getBody());
      System.out.println("dates: " + Arrays.toString(dates));

      String[] locations = OpenNLPNameFinderUtils.findLocations(reuters21578.getBody());
      System.out.println("locations: " + Arrays.toString(locations));

      String[] money = OpenNLPNameFinderUtils.findMoney(reuters21578.getBody());
      System.out.println("money: " + Arrays.toString(money));

      String[] organizations = OpenNLPNameFinderUtils.findOrganization(reuters21578.getBody());
      System.out.println("organizations: " + Arrays.toString(organizations));

      String[] percentages = OpenNLPNameFinderUtils.findPercentage(reuters21578.getBody());
      System.out.println("percentages: " + Arrays.toString(percentages));

      String[] times = OpenNLPNameFinderUtils.findTime(reuters21578.getBody());
      System.out.println("times: " + Arrays.toString(times));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Reuters21578DAO.release(); // release data resources
    }

  }

}
