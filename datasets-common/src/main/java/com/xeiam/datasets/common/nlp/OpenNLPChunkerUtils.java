/**
 * Copyright (c) 2013 Knowmtech <http://knowmtech.com>
 * 
 * All rights reserved. No warranty, explicit or implicit, provided. In no event shall the author be liable for any claim or damages.
 * 
 * IMPORTANT: THIS CODE IS PROPRIETARY!!! ABSOLUTELY NO DUPLICATION OR DISTRIBUTION IS PERMITTED WITHOUT EXPRESS WRITTEN PERMISSION FROM:
 * M. ALEXANDER NUGENT CONSULTING 22B STACY RD, SANTA FE NM 87585 (505)-988-7016 i@alexnugent.name
 */
package com.xeiam.datasets.common.nlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

/**
 * These OpenNLP utils are used for chunking
 * 
 * @author timmolter
 */
public class OpenNLPChunkerUtils {

  private static ChunkerME chunkerME = null;
  static {
    InputStream chunkerIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-chunker.bin");
    try {

      ChunkerModel chunkerModel = new ChunkerModel(chunkerIS);
      chunkerME = new ChunkerME(chunkerModel);

    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      chunkerIS.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given a pargraph, returns the words annotated with the POS (part of sentence) tags
   * 
   * @param paragraph
   * @return
   */
  public static String[] chunk(String paragraph) {

    List<String> posTagsList = new ArrayList<String>();

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);

    for (String sentence : sentences) {

      String[] tokens = OpenNLPBasicUtils.tokenize(sentence);
      String[] tags = OpenNLPBasicUtils.getPOSTags(sentence, false);

      Span[] spans = chunkerME.chunkAsSpans(tokens, tags);
      for (int i = 0; i < spans.length; i++) {
        String chunkString = "";
        for (int j = spans[i].getStart(); j < spans[i].getEnd(); j++) {
          // System.out.println(j);
          chunkString += tokens[j] + " ";
        }
        posTagsList.add(chunkString);
      }
    }
    return posTagsList.toArray(new String[posTagsList.size()]);
  }
}
