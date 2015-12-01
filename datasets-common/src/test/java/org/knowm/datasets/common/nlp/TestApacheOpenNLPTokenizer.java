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
public class TestApacheOpenNLPTokenizer {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph = "Hi. How are you? This isn't Mike. I wasn't born Dec. 3, 2009. The black and white cow jumped over the moon.";

    String[] tokens = OpenNLPBasicUtils.tokenize(paragraph);

    for (String a : tokens) {
      System.out.println(a);
    }

  }

  @Test
  public void test() {

    String paragraph = "The black and white cow jumped over the moon.";

    String[] tokens = OpenNLPBasicUtils.tokenize(paragraph);
    for (String a : tokens) {
      System.out.println(a);
    }
    assertThat(tokens.length, equalTo(10));
  }

}
