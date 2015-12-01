package org.knowm.datasets.common.nlp;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import org.junit.Ignore;

/**
 * @author timmolter
 */
@Ignore
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
