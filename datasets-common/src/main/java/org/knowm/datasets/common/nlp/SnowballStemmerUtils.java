package org.knowm.datasets.common.nlp;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.SnowballStemmer;

/**
 * @author timmolter
 */
public class SnowballStemmerUtils {

  public static String[] stem(String paragraph) {

    SnowballStemmer stemmer = null;
    try {
      Class stemClass = Class.forName("org.tartarus.snowball.ext.englishStemmer");
      stemmer = (SnowballStemmer) stemClass.newInstance();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    List<String> stemmedWords = new ArrayList<String>();

    String[] words = OpenNLPBasicUtils.tokenize(paragraph);

    for (int i = 0; i < words.length; i++) {
      stemmer.setCurrent(words[i]);
      stemmer.stem();
      String stemmedWord = stemmer.getCurrent();
      // System.out.println(words[i] + " -> " + stemmedWord);
      stemmedWords.add(stemmedWord);
    }

    return stemmedWords.toArray(new String[stemmedWords.size()]);

  }

}
