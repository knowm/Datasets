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
import java.io.RandomAccessFile;

/**
 * Cifar database file containing entries that can represent image or label data. Extends the standard random access file with methods for navigating
 * over the entries. The file format is basically idx with specific header information. This includes a magic number for determining the type of
 * stored entries, count of entries.
 */
public abstract class CifarDbFile extends RandomAccessFile {

  private final int count = 10000; // number of images in each Cifar data file

  /**
   * Creates new instance and reads the header information.
   * 
   * @param name the system-dependent filename
   * @param mode the access mode
   * @throws IOException
   * @throws FileNotFoundException
   * @see RandomAccessFile
   */
  public CifarDbFile(String name, String mode) throws IOException, FileNotFoundException {

    super(name, mode);
    // count = readInt();
  }

  public abstract int getHeaderSize();

  /**
   * Number of bytes for each entry.
   * 
   * @return int
   */
  public abstract int getEntryLength();

  /**
   * The current entry index.
   * 
   * @return long
   * @throws IOException
   */
  public long getCurrentIndex() throws IOException {

    return (getFilePointer() - getHeaderSize()) / getEntryLength() + 1;
  }

  /**
   * Set the required current entry index.
   * 
   * @param curr the entry index
   */
  public void setCurrentIndex(long curr) {

    try {
      if (curr < 0 || curr > count) {
        throw new RuntimeException(curr + " is not in the range 0 to " + count);
      }
      seek((curr - 1) * getEntryLength());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Move to the next entry.
   * 
   * @throws IOException
   */
  public void next() throws IOException {

    if (getCurrentIndex() < count) {
      skipBytes(getEntryLength());
    }
  }

  /**
   * Move to the previous entry.
   * 
   * @throws IOException
   */
  public void prev() throws IOException {

    if (getCurrentIndex() > 0) {
      seek(getFilePointer() - getEntryLength());
    }
  }

  public int getCount() {

    return count;
  }
}
