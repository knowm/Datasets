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

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;

/**
 * These OpenNLP utils are used for chunking
 * 
 * @author timmolter
 */
public class OpenNLPTreeParserUtils {

  private static Parser parser = null;

  static {
    InputStream parseIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-parser-chunking.bin");
    if (parseIS == null) {
      throw new RuntimeException(
          "No model file found on classpath! You must manually download the following file from http://opennlp.sourceforge.net/models-1.5/ and place it on the classpath in a folder called \"apacheOpenNLP\": "
              + "en-parser-chunking.bin");
    }
    try {
      ParserModel parserModel = new ParserModel(parseIS);
      parser = ParserFactory.create(parserModel);

    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      parseIS.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given a paragraph, returns the phrases in the parsed tree, including the words (tokens).
   * 
   * @param paragraph
   * @return
   */
  public static String[] getTreePhrases(String paragraph) {

    List<String> phrases = new ArrayList<String>();

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);

    for (String sentence : sentences) {

      Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);

      for (Parse p : topParses) {
        // p.show();
        getPhrases(p, new int[0], phrases);
        // get("NP", p, phrases);
        // get("PP", p, phrases);
        // get("JJ", p, phrases);
        // get("NN", p, phrases);
        // get("IN", p, phrases);
        // get("DT", p, phrases);
        // get("RB", p, phrases);
      }
    }
    return phrases.toArray(new String[phrases.size()]);
  }

  private static List<String> getPhrasesForKey(String key, Parse parse, List<String> phrases) {

    if (parse.getType().equals(key)) {
      phrases.add(key + "_" + parse.getCoveredText());
      // System.out.println(key + ":" + parse.getCoveredText());
    }
    for (Parse child : parse.getChildren()) {
      getPhrasesForKey(key, child, phrases);
    }
    return phrases;
  }

  private static void getPhrases(Parse p, int[] levels, List<String> phrases) {

    Parse[] kids = p.getChildren();
    StringBuilder levelsBuff = new StringBuilder();
    levelsBuff.append("[");
    int[] nlevels = new int[levels.length + 1];
    for (int li = 0; li < levels.length; li++) {
      nlevels[li] = levels[li];
      levelsBuff.append(levels[li]).append(".");
    }
    for (int ki = 0; ki < kids.length; ki++) {
      phrases.add(kids[ki].getType() + "_" + kids[ki].getCoveredText());
      nlevels[levels.length] = ki;
      // System.out.println(levelsBuff.toString() + ki + "] " + kids[ki].getType() + " " + kids[ki].hashCode() + " -> "
      // + kids[ki].getParent().hashCode() + " " + kids[ki].getParent().getType() + " " + kids[ki].getCoveredText());
      getPhrases(kids[ki], nlevels, phrases);
    }

  }

}
