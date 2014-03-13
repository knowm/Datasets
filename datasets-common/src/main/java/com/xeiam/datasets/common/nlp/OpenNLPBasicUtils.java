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

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

/**
 * These OpenNLP utils are used for findings sentences, tokenizing sentences and tagging the tokens with POS (part of sentence) tags.
 * 
 * @author timmolter
 */
public class OpenNLPBasicUtils {

  private static Tokenizer tokenizer = null;
  private static SentenceDetectorME sentenceDetector = null;
  private static POSTaggerME POSTaggerModel = null;
  static {
    InputStream tokenizerIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-token.bin");
    InputStream sentenceDetectorIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-sent.bin");
    InputStream posIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-pos-maxent.bin");
    if (tokenizerIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-token.bin");
    }
    if (sentenceDetectorIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-sent.bin");
    }
    if (posIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-pos-maxent.bin");
    }
    try {
      TokenizerModel tokenizerModel = new TokenizerModel(tokenizerIS);
      tokenizer = new TokenizerME(tokenizerModel);

      SentenceModel sentenceModel = new SentenceModel(sentenceDetectorIS);
      sentenceDetector = new SentenceDetectorME(sentenceModel);

      POSModel model = new POSModel(posIS);
      POSTaggerModel = new POSTaggerME(model);

    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      tokenizerIS.close();
      sentenceDetectorIS.close();
      posIS.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given a pargraph, returns it in tokenized form.
   * 
   * @param paragraph
   * @return
   */
  public static String[] tokenize(String paragraph) {

    String[] tokens = tokenizer.tokenize(paragraph);

    return tokens;
  }

  /**
   * Given a pargraph, returns it in tokenized form.
   * 
   * @param paragraph
   * @return
   */
  public static String[] getSentences(String paragraph) {

    String[] tokens = sentenceDetector.sentDetect(paragraph);

    return tokens;
  }

  /**
   * Given a pargraph, returns the words annotated with the POS (part of sentence) tags
   * 
   * @param paragraph
   * @return
   */
  public static String[] getPOSTags(String paragraph, boolean prefixWithToaken) {

    List<String> posTagsList = new ArrayList<String>();

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);

    for (String sentence : sentences) {

      String[] tokens = OpenNLPBasicUtils.tokenize(sentence);
      String[] tags = POSTaggerModel.tag(tokens);
      for (int i = 0; i < tags.length; i++) {
        if (prefixWithToaken) {
          posTagsList.add(tokens[i] + "_" + tags[i]);
        }
        else {
          posTagsList.add(tags[i]);
        }
      }
    }
    return posTagsList.toArray(new String[posTagsList.size()]);
  }

}
