package org.knowm.datasets.samples;

import javax.swing.JPanel;

import org.knowm.datasets.mnist.Mnist;
import org.knowm.datasets.mnist.MnistDAO;
import org.knowm.datasets.mnist.tools.MnistDigitViewer;
import org.knowm.datasets.mnist.tools.MnistImagePanel2;

/**
 * @author alexnugent
 */
public class MnistImageDisplayApp2 {

  /**
   * This app takes the following arguments:
   * <ul>
   * <li>int image index (0): Image index [0-69,999]
   * 
   * @param args
   */
  public static void main(String[] args) {

    try {
      MnistDAO.init("/usr/local/Datasets"); // setup data
      MnistImageDisplayApp2 mnistImageDisplayApp = new MnistImageDisplayApp2();
      mnistImageDisplayApp.go(args);
    } catch (Exception e) {
      // eat it.
    } finally {
      MnistDAO.release(); // release data resources
    }

  }

  public void go(String[] args) {

    int imageIndex = 0;

    try {
      imageIndex = Integer.parseInt(args[0]);

    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // just ignore
    }

    Mnist mnistData = MnistDAO.selectSingle(imageIndex);

    // paint the patches
    JPanel mnistImagePanel = new MnistImagePanel2(mnistData);
    new MnistDigitViewer(mnistImagePanel, "Index = " + mnistData.getId() + " label = " + mnistData.getLabel());

  }
}
