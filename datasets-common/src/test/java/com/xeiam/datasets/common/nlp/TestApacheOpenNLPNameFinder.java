/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
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
package com.xeiam.datasets.common.nlp;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;

/**
 * @author timmolter
 */
@Ignore
public class TestApacheOpenNLPNameFinder {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph =
        "How are you, Tim? This is Mike Smith from the United Nations. That car costs $19900? I wasn't born Dec 3, 2009 in San Franciso. The black and white cow jumped over the moon in 2013. I am Greg and I own 44% of Attolight. At 6:00 pm on Wednesday, the ship left the harbor towards Port Washington, WI";

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
