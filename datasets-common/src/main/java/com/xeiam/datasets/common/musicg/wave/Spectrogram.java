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
package com.xeiam.datasets.common.musicg.wave;

import com.xeiam.datasets.common.FastFourierTransform;
import com.xeiam.datasets.common.musicg.dsp.WindowFunction;
import com.xeiam.datasets.common.musicg.dsp.WindowFunction.WindowType;

/**
 * Handles the wave data in frequency-time domain.
 * 
 * @author Jacquet Wong
 */
public class Spectrogram {

  private static final int SPECTROGRAM_DEFAULT_FFT_SAMPLE_SIZE = 512;
  private static final int SPECTROGRAM_DEFAULT_OVERLAP_FACTOR = 0; // 0 for no overlapping

  private Wave wave;
  private double[][] spectrogram; // relative spectrogram
  private double[][] absoluteSpectrogram; // absolute spectrogram
  private int fftSampleSize; // number of sample in fft, the value needed to be a number to power of 2
  private int overlapFactor; // 1/overlapFactor overlapping, e.g. 1/4=25% overlapping
  private int numFrames; // number of frames of the spectrogram
  private int framesPerSecond; // frame per second of the spectrogram
  private int numFrequencyUnit; // number of y-axis unit
  private double frequencyBinSize; // frequency per y-axis unit
  private int frequencyRange; // frequency range of y-axis unit

  /**
   * Constructor
   * 
   * @param wave
   */
  public Spectrogram(Wave wave) {

    this(wave, SPECTROGRAM_DEFAULT_FFT_SAMPLE_SIZE, SPECTROGRAM_DEFAULT_OVERLAP_FACTOR);
  }

  /**
   * Constructor
   * 
   * @param wave
   * @param fftSampleSize number of sample in fft, the value needed to be a number to power of 2
   * @param overlapFactor 1/overlapFactor overlapping, e.g. 1/4=25% overlapping, 0 for no overlapping
   */
  public Spectrogram(Wave wave, int fftSampleSize, int overlapFactor) {

    this.wave = wave;

    if (Integer.bitCount(fftSampleSize) == 1) {
      this.fftSampleSize = fftSampleSize;
    }
    else {
      System.err.print("The input number must be a power of 2");
      this.fftSampleSize = SPECTROGRAM_DEFAULT_FFT_SAMPLE_SIZE;
    }

    this.overlapFactor = overlapFactor;

    buildSpectrogram();
  }

  /**
   * Build spectrogram
   */
  private void buildSpectrogram() {

    short[] amplitudes = wave.getSampleAmplitudes();
    int numSamples = amplitudes.length;

    int pointer = 0;

    // overlapping
    if (overlapFactor > 1) {

      int numOverlappedSamples = numSamples * overlapFactor;
      int backSamples = fftSampleSize * (overlapFactor - 1) / overlapFactor;
      int fftSampleSize_1 = fftSampleSize - 1;
      short[] overlapAmp = new short[numOverlappedSamples];
      pointer = 0;
      for (int i = 0; i < amplitudes.length; i++) {
        overlapAmp[pointer++] = amplitudes[i];
        if (pointer % fftSampleSize == fftSampleSize_1) {
          // overlap
          i -= backSamples;
        }
      }
      numSamples = numOverlappedSamples;
      amplitudes = overlapAmp;
    }

    numFrames = numSamples / fftSampleSize;
    framesPerSecond = (int) (numFrames / wave.getLengthInSeconds());

    // set signals for fft
    WindowFunction window = new WindowFunction();
    double[] win = window.generate(WindowType.HAMMING, fftSampleSize);

    double[][] signals = new double[numFrames][];
    for (int f = 0; f < numFrames; f++) {
      signals[f] = new double[fftSampleSize];
      int startSample = f * fftSampleSize;
      for (int n = 0; n < fftSampleSize; n++) {
        signals[f][n] = amplitudes[startSample + n] * win[n];
      }
    }

    absoluteSpectrogram = new double[numFrames][];
    // for each frame in signals, do fft on it
    FastFourierTransform fft = new FastFourierTransform();
    for (int i = 0; i < numFrames; i++) {
      absoluteSpectrogram[i] = fft.getMagnitudes(signals[i]);
    }

    if (absoluteSpectrogram.length > 0) {

      numFrequencyUnit = absoluteSpectrogram[0].length;
      frequencyBinSize = (double) wave.getWaveHeader().getSampleRate() / 2 / numFrequencyUnit; // frequency could be caught within the half of
                                                                                               // nSamples according to Nyquist theory
      frequencyRange = wave.getWaveHeader().getSampleRate() / 2;

      // normalization of absoultSpectrogram
      spectrogram = new double[numFrames][numFrequencyUnit];

      // set max and min amplitudes
      double maxAmp = Double.MIN_VALUE;
      double minAmp = Double.MAX_VALUE;
      for (int i = 0; i < numFrames; i++) {
        for (int j = 0; j < numFrequencyUnit; j++) {
          if (absoluteSpectrogram[i][j] > maxAmp) {
            maxAmp = absoluteSpectrogram[i][j];
          }
          else if (absoluteSpectrogram[i][j] < minAmp) {
            minAmp = absoluteSpectrogram[i][j];
          }
        }
      }

      // normalization
      // avoiding divided by zero
      double minValidAmp = 0.00000000001F;
      if (minAmp == 0) {
        minAmp = minValidAmp;
      }

      double diff = Math.log10(maxAmp / minAmp); // perceptual difference
      for (int i = 0; i < numFrames; i++) {
        for (int j = 0; j < numFrequencyUnit; j++) {
          if (absoluteSpectrogram[i][j] < minValidAmp) {
            spectrogram[i][j] = 0;
          }
          else {
            spectrogram[i][j] = (Math.log10(absoluteSpectrogram[i][j] / minAmp)) / diff;
            // System.out.println(spectrogram[i][j]);
          }
        }
      }
    }
  }

  /**
   * Get spectrogram: spectrogram[time][frequency]=intensity
   * 
   * @return logarithm normalized spectrogram
   */
  public double[][] getNormalizedSpectrogramData() {

    return spectrogram;
  }

  /**
   * Get spectrogram: spectrogram[time][frequency]=intensity
   * 
   * @return absolute spectrogram
   */
  public double[][] getAbsoluteSpectrogramData() {

    return absoluteSpectrogram;
  }

  public Wave getWave() {

    return wave;
  }

  public double[][] getSpectrogram() {

    return spectrogram;
  }

  public double[][] getAbsoluteSpectrogram() {

    return absoluteSpectrogram;
  }

  public int getFftSampleSize() {

    return fftSampleSize;
  }

  public int getOverlapFactor() {

    return overlapFactor;
  }

  public int getNumFrames() {

    return numFrames;
  }

  public int getFramesPerSecond() {

    return framesPerSecond;
  }

  public int getNumFrequencyUnit() {

    return numFrequencyUnit;
  }

  public double getFrequencyBinSize() {

    return frequencyBinSize;
  }

  public int getFrequencyRange() {

    return frequencyRange;
  }

}