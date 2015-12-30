package org.knowm.datasets.common;

import java.util.Arrays;

/**
 * @author timmolter
 */
public class Splitter {

  /**
   * Splits a String into an iterable
   *
   * @param separator
   * @param stringToSplit
   * @return
   */
  public static Iterable<String> split(String separator, String stringToSplit) {

    String[] StringArray = stringToSplit.split(separator);

    Iterable<String> iterable = Arrays.asList(StringArray);

    return iterable;
  }

}
