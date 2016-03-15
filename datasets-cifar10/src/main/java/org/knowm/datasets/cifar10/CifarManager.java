/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
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

import java.io.IOException;

public class CifarManager {

  private CifarImageFile images;

  /**
   * Constructs an instance managing the two given data files. Supports <code>NULL</code> value for one of the arguments in case reading only one of
   * the files (images and labels) is required.
   * 
   * @param imagesFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @throws IOException
   */
  public CifarManager(String imagesFile) throws IOException {

    if (imagesFile != null) {
      images = new CifarImageFile(imagesFile, "r");
    }
  }

  /**
   * Reads the current image.
   * 
   * @return matrix
   * @throws IOException
   */
  public CifarRaw readImage() throws IOException {

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
  }

  /**
   * Get the underlying images file as {@link CifarImageFile}.
   * 
   * @return {@link CifarImageFile}.
   */
  public CifarImageFile getImages() {

    return images;
  }
}
