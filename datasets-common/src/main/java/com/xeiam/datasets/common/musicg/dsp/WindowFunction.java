/**
 * Copyright (C) 2011 Jacquet Wong
 * Copyright (C) 2014 Xeiam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.datasets.common.musicg.dsp;

/**
 * Window functions generator
 * 
 * @author Jacquet Wong
 */
public class WindowFunction {

  public enum WindowType {
    RECTANGULAR, BARTLETT, HANNING, HAMMING, BLACKMAN
  };

  /**
   * Generate a window
   * 
   * @param windowType
   * @param nSamples size of the window
   * @return window in array
   */
  public double[] generate(WindowType windowType, int nSamples) {

    // generate nSamples window function values
    // for index values 0 .. nSamples - 1
    int m = nSamples / 2;
    double r;
    double pi = Math.PI;
    double[] w = new double[nSamples];
    switch (windowType) {
    case BARTLETT: // Bartlett (triangular) window
      for (int n = 0; n < nSamples; n++) {
        w[n] = 1.0f - Math.abs(n - m) / m;
      }
      break;
    case HANNING: // Hanning window
      r = pi / (m + 1);
      for (int n = -m; n < m; n++) {
        w[m + n] = 0.5f + 0.5f * Math.cos(n * r);
      }
      break;
    case HAMMING: // Hamming window
      r = pi / m;
      for (int n = -m; n < m; n++) {
        w[m + n] = 0.54f + 0.46f * Math.cos(n * r);
      }
      break;
    case BLACKMAN: // Blackman window
      r = pi / m;
      for (int n = -m; n < m; n++) {
        w[m + n] = 0.42f + 0.5f * Math.cos(n * r) + 0.08f * Math.cos(2 * n * r);
      }
      break;
    default: // Rectangular window function
      for (int n = 0; n < nSamples; n++) {
        w[n] = 1.0f;
      }
    }
    return w;
  }
}