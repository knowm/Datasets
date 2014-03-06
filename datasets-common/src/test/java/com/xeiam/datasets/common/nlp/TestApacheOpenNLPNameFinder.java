/**
 * Copyright (c) 2013 M. Alexander Nugent Consulting <i@alexnugent.name>
 *
 * M. Alexander Nugent Consulting Research License Agreement
 * Non-Commercial Academic Use Only
 *
 * This Software is proprietary. By installing, copying, or otherwise using this
 * Software, you agree to be bound by the terms of this license. If you do not agree,
 * do not install, copy, or use the Software. The Software is protected by copyright
 * and other intellectual property laws.
 *
 * You may use the Software for non-commercial academic purpose, subject to the following
 * restrictions. You may copy and use the Software for peer-review and methods verification
 * only. You may not create derivative works of the Software. You may not use or distribute
 * the Software or any derivative works in any form for commercial or non-commercial purposes.
 *
 * Violators will be prosecuted to the full extent of the law.
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
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

/**
 * @author timmolter
 */
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
