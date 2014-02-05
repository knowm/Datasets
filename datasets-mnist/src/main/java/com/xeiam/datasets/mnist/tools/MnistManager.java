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
package com.xeiam.datasets.mnist.tools;

import java.io.IOException;

/**
 * <p>
 * Utility class for working with the MNIST database.
 * <p>
 * Provides methods for traversing the images and labels data files separately, as well as simultaneously.
 * <p>
 * Provides also method for exporting an image by writing it as a PPM file.
 * <p>
 * Example usage:
 * 
 * <pre>
 * MnistManager m = new MnistManager(&quot;t10k-images.idx3-ubyte&quot;, &quot;t10k-labels.idx1-ubyte&quot;);
 * m.setCurrent(10); // index of the image that we are interested in
 * int[][] image = m.readImage();
 * System.out.println(&quot;Label:&quot; + m.readLabel());
 * MnistManager.writeImageToPpm(image, &quot;10.ppm&quot;);
 * </pre>
 */
public class MnistManager {

  private MnistImageFile images;
  private MnistLabelFile labels;

  // /**
  // * Writes the given image in the given file using the PPM data format.
  // *
  // * @param image
  // * @param ppmFileName
  // * @throws IOException
  // */
  // public static void writeImageToPpm(int[][] image, String ppmFileName) throws IOException {
  //
  // BufferedWriter ppmOut = null;
  // try {
  // ppmOut = new BufferedWriter(new FileWriter(ppmFileName));
  //
  // int rows = image.length;
  // int cols = image[0].length;
  // ppmOut.write("P3\n");
  // ppmOut.write("" + rows + " " + cols + " 255\n");
  // for (int i = 0; i < rows; i++) {
  // StringBuffer s = new StringBuffer();
  // for (int j = 0; j < cols; j++) {
  // s.append(image[i][j] + " " + image[i][j] + " " + image[i][j] + "  ");
  // }
  // ppmOut.write(s.toString());
  // }
  // } finally {
  // ppmOut.close();
  // }
  //
  // }

  /**
   * Constructs an instance managing the two given data files. Supports <code>NULL</code> value for one of the arguments in case reading only one of
   * the files (images and labels) is required.
   * 
   * @param imagesFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @param labelsFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @throws IOException
   */
  public MnistManager(String imagesFile, String labelsFile) throws IOException {

    if (imagesFile != null) {
      images = new MnistImageFile(imagesFile, "r");
    }
    if (labelsFile != null) {
      labels = new MnistLabelFile(labelsFile, "r");
    }
  }

  /**
   * Reads the current image.
   * 
   * @return matrix
   * @throws IOException
   */
  public int[][] readImage() throws IOException {

    if (images == null) {
      throw new IllegalStateException("Images file not initialized.");
    }
    return images.readImage();
  }

  /**
   * Set the position to be read.
   * 
   * @param index
   */
  public void setCurrent(int index) {

    images.setCurrentIndex(index);
    labels.setCurrentIndex(index);
  }

  /**
   * Reads the current label.
   * 
   * @return int
   * @throws IOException
   */
  public int readLabel() throws IOException {

    if (labels == null) {
      throw new IllegalStateException("labels file not initialized.");
    }
    return labels.readLabel();
  }

  /**
   * Get the underlying images file as {@link MnistImageFile}.
   * 
   * @return {@link MnistImageFile}.
   */
  public MnistImageFile getImages() {

    return images;
  }

  /**
   * Get the underlying labels file as {@link MnistLabelFile}.
   * 
   * @return {@link MnistLabelFile}.
   */
  public MnistLabelFile getLabels() {

    return labels;
  }
}
