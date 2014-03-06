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
