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
