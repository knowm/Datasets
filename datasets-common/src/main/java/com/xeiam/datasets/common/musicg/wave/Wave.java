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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Read WAVE headers and data from wave input stream
 * <p>
 * WAV File Specification:
 * <p>
 * https://ccrma.stanford.edu/courses/422/projects/WaveFormat/
 * 
 * @author Jacquet Wong
 */
public class Wave implements Serializable {

  // A wav file is split up into a header and the actual data
  private WaveHeader waveHeader;
  private byte[] data; // little endian

  /**
   * Constructor
   * 
   * @param filename Wave file
   * @throws FileNotFoundException
   */
  public Wave(String filename) throws FileNotFoundException {

    this(new FileInputStream(filename));
  }

  /**
   * Constructor
   * 
   * @param inputStream Wave file input stream
   */
  public Wave(InputStream inputStream) {

    try {
      // reads the first 44 bytes for header
      waveHeader = new WaveHeader(inputStream);

      // load data
      data = new byte[inputStream.available()];
      inputStream.read(data);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Get the wave spectrogram
   * 
   * @return spectrogram
   */
  public Spectrogram getSpectrogram() {

    return new Spectrogram(this);
  }

  /**
   * Get the wave spectrogram
   * 
   * @param fftSampleSize number of samples in fft, the value needs to be a number to power of 2
   * @param overlapFactor 1/overlapFactor overlapping, e.g. 1/4=25% overlapping, 0 for no overlapping
   * @return spectrogram
   */
  public Spectrogram getSpectrogram(int fftSampleSize, int overlapFactor) {

    return new Spectrogram(this, fftSampleSize, overlapFactor);
  }

  /**
   * Get the amplitudes of the wave samples (depends on the header)
   * 
   * @return amplitudes array (signed 16-bit)
   */
  public short[] getSampleAmplitudes() {

    int bytePerSample = waveHeader.getBitsPerSample() / 8;
    int numSamples = data.length / bytePerSample;
    short[] amplitudes = new short[numSamples];

    int pointer = 0;
    for (int i = 0; i < numSamples; i++) {
      short amplitude = 0;
      for (int byteNumber = 0; byteNumber < bytePerSample; byteNumber++) {
        // little endian
        amplitude |= (short) ((data[pointer++] & 0xFF) << (byteNumber * 8));
      }
      amplitudes[i] = amplitude;
    }

    return amplitudes;
  }

  public double[] getNormalizedAmplitudes() {

    double[] normalizedAmplitudes;

    boolean signed = true;

    // usually 8bit is unsigned
    if (getWaveHeader().getBitsPerSample() == 8) {
      signed = false;
    }

    short[] amplitudes = getSampleAmplitudes();
    int numSamples = amplitudes.length;
    int maxAmplitude = 1 << (getWaveHeader().getBitsPerSample() - 1);

    if (!signed) { // one more bit for unsigned value
      maxAmplitude <<= 1;
    }

    normalizedAmplitudes = new double[numSamples];
    for (int i = 0; i < numSamples; i++) {
      normalizedAmplitudes[i] = (double) amplitudes[i] / maxAmplitude;
    }
    return normalizedAmplitudes;
  }

  /**
   * Get the wave header
   * 
   * @return waveHeader
   */
  public WaveHeader getWaveHeader() {

    return waveHeader;
  }

  /**
   * Get the wave data in bytes
   * 
   * @return wave data
   */
  public byte[] getData() {

    return data;
  }

  /**
   * Length of the wave in second
   * 
   * @return length in second
   */
  public float getLengthInSeconds() {

    float second = (float) waveHeader.getSubChunk2Size() / waveHeader.getByteRate();
    return second;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer(waveHeader.toString());
    sb.append("\n");
    sb.append("length: " + getLengthInSeconds() + " s");
    return sb.toString();
  }

}