/**
 * (The MIT License)
 *
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
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
