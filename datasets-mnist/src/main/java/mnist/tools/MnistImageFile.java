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

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * MNIST database image file. Contains additional header information for the number of rows and columns per each entry.
 */
public class MnistImageFile extends MnistDbFile {

  private final int rows;
  private final int cols;

  /**
   * Creates new MNIST database image file ready for reading.
   * 
   * @param name the system-dependent filename
   * @param mode the access mode
   * @throws IOException
   * @throws FileNotFoundException
   */
  public MnistImageFile(String name, String mode) throws FileNotFoundException, IOException {

    super(name, mode);

    // read header information
    rows = readInt();
    cols = readInt();
  }

  /**
   * Reads the image at the current position.
   * 
   * @return matrix representing the image
   * @throws IOException
   */
  public int[][] readImage() throws IOException {

    int[][] dat = new int[getRows()][getCols()];
    for (int i = 0; i < getCols(); i++) {
      for (int j = 0; j < getRows(); j++) {
        dat[i][j] = readUnsignedByte();
      }
    }
    return dat;
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

  @Override
  protected int getMagicNumber() {

    return 2051;
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

    return cols * rows;
  }

  @Override
  public int getHeaderSize() {

    return super.getHeaderSize() + 8; // to more integers - rows and columns
  }
}
