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
