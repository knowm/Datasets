package org.knowm.datasets.common;

import java.util.List;

/**
 * @author timmolter
 */
public class Joiner {

  /**
   * Joins a list of Strings
   *
   * @param separator
   * @param topicsArray
   * @return
   */
  public static String join(String separator, List<String> topicsArray) {

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < topicsArray.size(); i++) {

      if (sb.length() > 0) {
        sb.append(",");
      }
      sb.append(topicsArray.get(i));
    }
    return sb.toString();
  }

}
