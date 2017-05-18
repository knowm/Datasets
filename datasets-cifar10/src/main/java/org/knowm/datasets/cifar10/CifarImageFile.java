/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package org.knowm.datasets.cifar10;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Cifar database image file. Contains additional header information for the number of rows and columns per each entry.
 */
public class CifarImageFile extends CifarDbFile {

  private final int rows = 32;
  private final int cols = 32;

  /**
   * Creates new 9 database image file ready for reading.
   * 
   * @param name the system-dependent filename
   * @param mode the access mode
   * @throws IOException
   * @throws FileNotFoundException
   */
  public CifarImageFile(String name, String mode) throws FileNotFoundException, IOException {

    super(name, mode);
  }

  /**
   * Reads the image at the current position.
   * 
   * @return matrix representing the image
   * @throws IOException
   */
  public CifarRaw readImage() throws IOException {

    int label = readUnsignedByte(); // peel off the first byte which is the image label

    int[][] red = new int[getRows()][getCols()];
    for (int i = 0; i < getCols(); i++) {
      for (int j = 0; j < getRows(); j++) {
        red[i][j] = readUnsignedByte();
      }
    }

    int[][] green = new int[getRows()][getCols()];
    for (int i = 0; i < getCols(); i++) {
      for (int j = 0; j < getRows(); j++) {
        green[i][j] = readUnsignedByte();
      }
    }

    int[][] blue = new int[getRows()][getCols()];
    for (int i = 0; i < getCols(); i++) {
      for (int j = 0; j < getRows(); j++) {
        blue[i][j] = readUnsignedByte();
      }
    }
    return new CifarRaw(label, red, green, blue);
  }

  /**
   * Move the cursor to the next image.
   * 
   * @throws IOException
   */
  public void nextImage() throws IOException {

    super.next();
  }

  /**
   * Move the cursor to the previous image.
   * 
   * @throws IOException
   */
  public void prevImage() throws IOException {

    super.prev();
  }

  /**
   * Number of rows per image.
   * 
   * @return int
   */
  public int getRows() {

    return rows;
  }

  /**
   * Number of columns per image.
   * 
   * @return int
   */
  public int getCols() {

    return cols;
  }

  @Override
  public int getEntryLength() {

    return 1 + 3 * cols * rows; // lable byte + rgb channels times 32 * 32
  }

  @Override
  public int getHeaderSize() {

    return 1; // just the label
  }
}
