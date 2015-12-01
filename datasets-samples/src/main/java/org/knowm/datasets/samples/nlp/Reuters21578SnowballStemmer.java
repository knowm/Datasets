package org.knowm.datasets.samples.nlp;

import java.util.Arrays;
import java.util.List;

import org.knowm.datasets.common.nlp.SnowballStemmerUtils;
import org.knowm.datasets.reuters21578.Reuters21578;
import org.knowm.datasets.reuters21578.Reuters21578DAO;

/**
 * @author timmolter
 */
public class Reuters21578SnowballStemmer {

  public static void main(String[] args) {

    try {
      Reuters21578DAO.init("/usr/local/Datasets"); // setup data
      List<Reuters21578> reuters21578List = Reuters21578DAO.selectRandomList(1);
      String[] chunks = SnowballStemmerUtils.stem(reuters21578List.get(0).getBody());
      System.out.println(Arrays.toString(chunks));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      Reuters21578DAO.release(); // release data resources
    }

  }
}
