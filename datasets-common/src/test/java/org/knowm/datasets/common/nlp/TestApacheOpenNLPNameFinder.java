package org.knowm.datasets.common.nlp;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.knowm.datasets.common.nlp.OpenNLPNameFinderUtils;

/**
 * @author timmolter
 */
@Ignore
public class TestApacheOpenNLPNameFinder {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph = "How are you, Tim? This is Mike Smith from the United Nations. That car costs $19900? I wasn't born Dec 3, 2009 in San Franciso. The black and white cow jumped over the moon in 2013. I am Greg and I own 44% of Attolight. At 6:00 pm on Wednesday, the ship left the harbor towards Port Washington, WI";

    String[] persons = OpenNLPNameFinderUtils.findPersons(paragraph);
    printResults(persons, "persons");
    String[] dates = OpenNLPNameFinderUtils.findDates(paragraph);
    printResults(dates, "dates");
    String[] locations = OpenNLPNameFinderUtils.findLocations(paragraph);
    printResults(locations, "locations");
    String[] money = OpenNLPNameFinderUtils.findMoney(paragraph);
    printResults(money, "money");
    String[] organizations = OpenNLPNameFinderUtils.findOrganization(paragraph);
    printResults(organizations, "organization");
    String[] percentages = OpenNLPNameFinderUtils.findPercentage(paragraph);
    printResults(percentages, "percentage");
    String[] times = OpenNLPNameFinderUtils.findTime(paragraph);
    printResults(times, "time");
  }

  private static void printResults(String[] names, String nameType) {

    System.out.println(nameType + ":");
    for (int i = 0; i < names.length; i++) {
      System.out.println(i + ": " + names[i]);
    }
  }
}
