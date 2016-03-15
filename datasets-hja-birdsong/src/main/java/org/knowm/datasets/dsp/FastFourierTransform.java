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
package org.knowm.datasets.dsp;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * FFT object, transform amplitudes to frequency intensities
 */
public class FastFourierTransform {

  /**
   * Get the frequency intensities
   *
   * @param amplitudes amplitudes of the signal
   */
  public double[] getMagnitudes(double[] amplitudes) {

    FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);

    Complex[] result = fft.transform(amplitudes, TransformType.FORWARD);

    // convert from complex to magnitude and only keep the positive frequencies as the FTT creates a symmetric result
    int positiveSize = result.length / 2;
    double[] magnitude = new double[positiveSize];
    for (int i = positiveSize; i < result.length; i++) {
      magnitude[i - positiveSize] = result[i].abs();
    }

    return magnitude;
  }
}
