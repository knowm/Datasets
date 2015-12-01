package org.knowm.datasets.common.nlp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.common.nlp.OpenNLPBasicUtils;

/**
 * POS = Part of Sentence
 * 
 * @author timmolter
 */
@Ignore
public class TestApacheOpenNLPPOSTagger {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph = "Hi. How are you? This is Mike.";
    String[] sentences = OpenNLPBasicUtils.getPOSTags(paragraph, true);
    for (int i = 0; i < sentences.length; i++) {
      System.out.println(i + ": " + sentences[i]);
    }
  }

  @Test
  public void test() {

    String paragraph = "The black and white cow jumped over the moon. Even though Bitcoin has been through two price spikes and dips, it seems yet again we have people flocking to this subreddit circlejerking over the price. ";

    String[] posTags = OpenNLPBasicUtils.getPOSTags(paragraph, true);
    for (String a : posTags) {
      System.out.println(a);
    }
    assertThat(posTags.length, equalTo(38));
    assertThat(posTags[0], equalTo("The_DT"));
  }

}
