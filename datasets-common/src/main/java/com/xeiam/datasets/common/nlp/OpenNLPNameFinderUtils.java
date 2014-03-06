/**
 * Copyright (c) 2013 Knowmtech <http://knowmtech.com>
 * 
 * All rights reserved. No warranty, explicit or implicit, provided. In no event shall the author be liable for any claim or damages.
 * 
 * IMPORTANT: THIS CODE IS PROPRIETARY!!! ABSOLUTELY NO DUPLICATION OR DISTRIBUTION IS PERMITTED WITHOUT EXPRESS WRITTEN PERMISSION FROM:
 * M. ALEXANDER NUGENT CONSULTING 22B STACY RD, SANTA FE NM 87585 (505)-988-7016 i@alexnugent.name
 */
package com.xeiam.datasets.common.nlp;

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
    InputStream dateIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-date.bin");
    InputStream locationIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-location.bin");
    InputStream moneyIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-money.bin");
    InputStream organizationIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-organization.bin");
    InputStream percentageIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-percentage.bin");
    InputStream timeIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-ner-time.bin");

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
