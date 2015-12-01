/**
 * (The MIT License)
 *
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
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
package org.knowm.datasets.common.nlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

/**
 * These OpenNLP utils are used for findings sentences, tokenizing sentences and tagging the tokens with POS (part of sentence) tags.
 * 
 * @author timmolter
 */
public class OpenNLPNameFinderUtils {

  private static NameFinderME personFinder = null;
  private static NameFinderME dateFinder = null;
  private static NameFinderME locationFinder = null;
  private static NameFinderME moneyFinder = null;
  private static NameFinderME organizationFinder = null;
  private static NameFinderME percentageFinder = null;
  private static NameFinderME timeFinder = null;

  static {
    InputStream personIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-person.bin");
    if (personIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-person.bin");
    }
    InputStream dateIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-date.bin");
    if (dateIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-date.bin");
    }
    InputStream locationIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-location.bin");
    if (locationIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-location.bin");
    }
    InputStream moneyIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-money.bin");
    if (moneyIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-money.bin");
    }
    InputStream organizationIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-organization.bin");
    if (organizationIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-organization.bin");
    }
    InputStream percentageIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-percentage.bin");
    if (percentageIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-percentage.bin");
    }
    InputStream timeIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-time.bin");
    if (timeIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-ner-time.bin");
    }
    try {
      TokenNameFinderModel personFinderModel = new TokenNameFinderModel(personIS);
      personFinder = new NameFinderME(personFinderModel);

      TokenNameFinderModel dateFinderModel = new TokenNameFinderModel(dateIS);
      dateFinder = new NameFinderME(dateFinderModel);

      TokenNameFinderModel locationFinderModel = new TokenNameFinderModel(locationIS);
      locationFinder = new NameFinderME(locationFinderModel);

      TokenNameFinderModel moneyFinderModel = new TokenNameFinderModel(moneyIS);
      moneyFinder = new NameFinderME(moneyFinderModel);

      TokenNameFinderModel organizationFinderModel = new TokenNameFinderModel(organizationIS);
      organizationFinder = new NameFinderME(organizationFinderModel);

      TokenNameFinderModel percentageFinderModel = new TokenNameFinderModel(percentageIS);
      percentageFinder = new NameFinderME(percentageFinderModel);

      TokenNameFinderModel timeFinderModel = new TokenNameFinderModel(timeIS);
      timeFinder = new NameFinderME(timeFinderModel);

    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      personIS.close();
      dateIS.close();
      locationIS.close();
      moneyIS.close();
      organizationIS.close();
      percentageIS.close();
      timeIS.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given a paragraph, returns the names
   * 
   * @param paragraph
   * @param nameFinderME
   * @return
   */
  public static String[] findNames(String paragraph, NameFinderME nameFinderME) {

    List<String> returnList = new ArrayList<String>();

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);

    for (String sentence : sentences) {

      String[] tokens = OpenNLPBasicUtils.tokenize(sentence);
      Span[] nameSpans = nameFinderME.find(tokens);
      String[] spans2 = Span.spansToStrings(nameSpans, tokens);
      for (int i = 0; i < spans2.length; i++) {
        returnList.add(spans2[i]);
      }
    }
    return returnList.toArray(new String[returnList.size()]);
  }

  public static String[] findPersons(String paragraph) {

    return findNames(paragraph, personFinder);
  }

  public static String[] findDates(String paragraph) {

    return findNames(paragraph, dateFinder);
  }

  public static String[] findLocations(String paragraph) {

    return findNames(paragraph, locationFinder);
  }

  public static String[] findMoney(String paragraph) {

    return findNames(paragraph, moneyFinder);
  }

  public static String[] findOrganization(String paragraph) {

    return findNames(paragraph, organizationFinder);
  }

  public static String[] findPercentage(String paragraph) {

    return findNames(paragraph, percentageFinder);
  }

  public static String[] findTime(String paragraph) {

    return findNames(paragraph, timeFinder);
  }
}
