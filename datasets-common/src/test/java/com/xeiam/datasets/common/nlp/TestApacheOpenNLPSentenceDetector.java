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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Test;

/**
 * @author timmolter
 */
public class TestApacheOpenNLPSentenceDetector {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph = "Hi. How are you? This isn't Mike. I wasn't born Dec. 3, 2009. The black and white cow jumped over the moon.";

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);
    for (int i = 0; i < sentences.length; i++) {
      System.out.println(i + ": " + sentences[i]);
    }
  }

  @Test
  public void test() {

    String paragraph = "The black and white cow jumped over the moon. Even though Bitcoin has been through two price spikes and dips, it seems yet again we have people flocking to this subreddit circlejerking over the price. ";

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);
    for (String a : sentences) {
      System.out.println(a);
    }
    assertThat(sentences.length, equalTo(2));
  }

}
