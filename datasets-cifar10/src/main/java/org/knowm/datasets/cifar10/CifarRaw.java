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
package org.knowm.datasets.cifar10;

/**
 * @author timmolter
 */
public class CifarRaw {

  private final int label;
  private final int[][] redChannel;
  private final int[][] greenChannel;
  private final int[][] blueChannel;

  /**
   * Constructor
   * 
   * @param label
   * @param redChannel
   * @param greenChannel
   * @param blueChannel
   */
  public CifarRaw(int label, int[][] redChannel, int[][] greenChannel, int[][] blueChannel) {

    this.label = label;
    this.redChannel = redChannel;
    this.greenChannel = greenChannel;
    this.blueChannel = blueChannel;
  }

  public int getLabel() {

    return label;
  }

  public int[][] getRedChannel() {

    return redChannel;
  }

  public int[][] getGreenChannel() {

    return greenChannel;
  }

  public int[][] getBlueChannel() {

    return blueChannel;
  }

}
