/**
 * Copyright (c) 2013 Knowmtech <http://knowmtech.com>
 * 
 * All rights reserved. No warranty, explicit or implicit, provided. In no event shall the author be liable for any claim or damages.
 * 
 * IMPORTANT: THIS CODE IS PROPRIETARY!!! ABSOLUTELY NO DUPLICATION OR DISTRIBUTION IS PERMITTED WITHOUT EXPRESS WRITTEN PERMISSION FROM:
 * M. ALEXANDER NUGENT CONSULTING 22B STACY RD, SANTA FE NM 87585 (505)-988-7016 i@alexnugent.name
 */
package com.xeiam.datasets.common.nlp;

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
