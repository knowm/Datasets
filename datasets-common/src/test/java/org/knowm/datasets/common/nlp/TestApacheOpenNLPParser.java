package org.knowm.datasets.common.nlp;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Ignore;
import org.knowm.datasets.common.nlp.OpenNLPTreeParserUtils;

/**
 * @author timmolter
 */
@Ignore
public class TestApacheOpenNLPParser {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String sentence = "The quick brown fox jumps over the lazy dog.";

    String[] treePhrases = OpenNLPTreeParserUtils.getTreePhrases(sentence);
    for (int i = 0; i < treePhrases.length; i++) {
      System.out.println(treePhrases[i]);
    }

  }

}
