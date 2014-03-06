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
 * MNIST database label file.
 */
public class MnistLabelFile extends MnistDbFile {

  /**
   * Creates new MNIST database label file ready for reading.
   * 
   * @param name the system-dependent filename
   * @param mode the access mode
   * @throws IOException
   * @throws FileNotFoundException
   */
  public MnistLabelFile(String name, String mode) throws FileNotFoundException, IOException {

    super(name, mode);
  }

  /**
   * Reads the integer at the current position.
   * 
   * @return integer representing the label
   * @throws IOException
   */
  public int readLabel() throws IOException {

    return readUnsignedByte();
  }

  @Override
  protected int getMagicNumber() {

    return 2049;
  }
}
