package org.knowm.datasets.common.nlp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.common.nlp.OpenNLPBasicUtils;

/**
 * @author timmolter
 */
@Ignore
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
