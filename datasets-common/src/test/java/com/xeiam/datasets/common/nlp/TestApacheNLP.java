/**
 * Copyright (c) 2013 M. Alexander Nugent Consulting <i@alexnugent.name>
 *
 * M. Alexander Nugent Consulting Research License Agreement
 * Non-Commercial Academic Use Only
 *
 * This Software is proprietary. By installing, copying, or otherwise using this
 * Software, you agree to be bound by the terms of this license. If you do not agree,
 * do not install, copy, or use the Software. The Software is protected by copyright
 * and other intellectual property laws.
 *
 * You may use the Software for non-commercial academic purpose, subject to the following
 * restrictions. You may copy and use the Software for peer-review and methods verification
 * only. You may not create derivative works of the Software. You may not use or distribute
 * the Software or any derivative works in any form for commercial or non-commercial purposes.
 *
 * Violators will be prosecuted to the full extent of the law.
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
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
