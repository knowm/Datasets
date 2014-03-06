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

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * @author timmolter
 */
public class TestApacheNLP {

  private POSModel model;

  /**
   * Constructor
   * 
   * @param data
   * @throws IOException
   */
  public TestApacheNLP(InputStream data) throws IOException {

    setModel(new POSModel(data));
  }

  public static void main(String args[]) throws IOException {

    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("apacheOpenNLP/en-pos-maxent.bin");
    TestApacheNLP hw = new TestApacheNLP(is);
    is.close();

    hw.run("The black and white cow jumped over the moon.");

  }

  public void run(String sentence) {

    POSTaggerME tagger = new POSTaggerME(getModel());
    String[] words = sentence.split("\\s+");
    String[] tags = tagger.tag(words);
    double[] probs = tagger.probs();

    for (int i = 0; i < tags.length; i++) {
      System.out.println(words[i] + " => " + tags[i] + " @ " + probs[i]);
    }
  }

  private void setModel(POSModel model) {

    this.model = model;
  }

  private POSModel getModel() {

    return this.model;
  }

}
