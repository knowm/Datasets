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

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.knowm.datasets.common.nlp.SnowballStemmerUtils;

/**
 * POS = Part of Sentence
 * 
 * @author timmolter
 */
@Ignore
public class TestSnowballStemmer {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  private static void go() {

    String paragraph = "He reckons the current account deficit will narrow to only 1.8 billion in September.";
    String result[] = SnowballStemmerUtils.stem(paragraph);

    System.out.println("___stemmed words___");
    for (String s : result) {
      System.out.println(s);
    }

  }

}
