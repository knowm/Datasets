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
package mnist.tools;

import java.io.IOException;

/**
 * <p>
 * Utility class for working with the MNIST database.
 * <p>
 * Provides methods for traversing the mnistImageFile and mnistLabelFile data files separately, as well as simultaneously.
 * <p>
 * Provides also method for exporting an image by writing it as a PPM file.
 * <p>
 * Example usage:
 * 
 * <pre>
 * MnistManager m = new MnistManager(&quot;t10k-mnistImageFile.idx3-ubyte&quot;, &quot;t10k-mnistLabelFile.idx1-ubyte&quot;);
 * m.setCurrent(10); // index of the image that we are interested in
 * int[][] image = m.readImage();
 * System.out.println(&quot;Label:&quot; + m.readLabel());
 * MnistManager.writeImageToPpm(image, &quot;10.ppm&quot;);
 * </pre>
 */
public class MnistManager {

  private MnistImageFile mnistImageFile;
  private MnistLabelFile mnistLabelFile;

  /**
   * Constructs an instance managing the two given data files. Supports <code>NULL</code> value for one of the arguments in case reading only one of
   * the files (mnistImageFile and mnistLabelFile) is required.
   * 
   * @param imagesFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @param labelsFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @throws IOException
   */
  public MnistManager(String imagesFile, String labelsFile) throws IOException {

    if (imagesFile != null) {
      mnistImageFile = new MnistImageFile(imagesFile, "r");
    }
    if (labelsFile != null) {
      mnistLabelFile = new MnistLabelFile(labelsFile, "r");
    }
  }

  /**
   * Reads the current image.
   * 
   * @return matrix
   * @throws IOException
   */
  public int[][] readImage() throws IOException {

    if (mnistImageFile == null) {
      throw new IllegalStateException("Images file not initialized.");
    }
    return mnistImageFile.readImage();
  }

  public byte[] readImageAsSignedByteArray() throws IOException {

    if (mnistImageFile == null) {
      throw new IllegalStateException("Images file not initialized.");
    }
    return mnistImageFile.readImageAsSignedByteArray();
  }

  /**
   * Set the position to be read.
   * 
   * @param index
   */
  public void setCurrent(int index) {

    mnistImageFile.setCurrentIndex(index);
    mnistLabelFile.setCurrentIndex(index);
  }

  /**
   * Reads the current label.
   * 
   * @return int
   * @throws IOException
   */
  public int readLabel() throws IOException {

    if (mnistLabelFile == null) {
      throw new IllegalStateException("mnistLabelFile file not initialized.");
    }
    return mnistLabelFile.readLabel();
  }

  /**
   * Get the underlying mnistImageFile file as {@link MnistImageFile}.
   * 
   * @return {@link MnistImageFile}.
   */
  public MnistImageFile getMnistImageFile() {

    return mnistImageFile;
  }

  /**
   * Get the underlying mnistLabelFile file as {@link MnistLabelFile}.
   * 
   * @return {@link MnistLabelFile}.
   */
  public MnistLabelFile getMnistLabelFile() {

    return mnistLabelFile;
  }
}
