package com.xeiam.datasets.common.musicg.dsp;

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
