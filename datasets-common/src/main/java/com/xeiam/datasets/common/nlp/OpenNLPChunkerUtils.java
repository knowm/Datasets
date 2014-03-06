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
