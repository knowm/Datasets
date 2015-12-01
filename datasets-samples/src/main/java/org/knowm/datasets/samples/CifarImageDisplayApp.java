package org.knowm.datasets.samples;

import javax.swing.JPanel;

import org.knowm.datasets.cifar10.Cifar;
import org.knowm.datasets.cifar10.CifarDAO;
import org.knowm.datasets.cifar10.CifarImagePanel;
import org.knowm.datasets.cifar10.CifarViewer;

/**
 * @author alexnugent
 */
public class CifarImageDisplayApp {

  /**
   * This app takes the following arguments:
   * <ul>
   * <li>int image index (0): Image index [0-69,999]
   * 
   * @param args
   */
  public static void main(String[] args) {

    CifarDAO.init("/usr/local/Datasets"); // setup data
    try {
      CifarImageDisplayApp cifarImageDisplayApp = new CifarImageDisplayApp();
      cifarImageDisplayApp.go(args);
    } catch (Exception e) {
      // eat it.
    } finally {
      CifarDAO.release();// release data resources
    }
  }

  public void go(String[] args) {

    int imageIndex = 836;

    try {
      imageIndex = Integer.parseInt(args[0]);

    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // just ignore
    }

    Cifar cifar = CifarDAO.selectSingle(imageIndex);

    int[][] red = cifar.getRedChannel();

    // paint the patches
    JPanel cifarImagePanel = new CifarImagePanel(red, 4);
    new CifarViewer(cifarImagePanel, "Index = " + cifar.getId() + " label = " + cifar.getLabelWord());

  }
}
