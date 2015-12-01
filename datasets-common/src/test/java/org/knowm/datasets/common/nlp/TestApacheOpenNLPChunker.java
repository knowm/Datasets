package org.knowm.datasets.common.nlp;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.knowm.datasets.common.nlp.OpenNLPChunkerUtils;

/**
 * POS = Part of Sentence
 * 
 * @author timmolter
 */
@Ignore
public class TestApacheOpenNLPChunker {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  private static void go() {

    String paragraph = "He reckons the current account deficit will narrow to only 1.8 billion in September.";
    String result[] = OpenNLPChunkerUtils.chunk(paragraph);

    System.out.println("___chunks___");
    for (String s : result) {
      System.out.println(s);
    }

  }

}
