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
package com.musicg.wave;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WaveformRender {

  /**
   * Render a waveform of a wave file
   * 
   * @param wave Wave object
   * @param timeStep time interval in second, as known as 1/fps
   * @see RGB graphic rendered
   */
  public BufferedImage renderWaveform(Wave wave, int width) {

    // for signed signals, the middle is 0 (-1 ~ 1)
    double middleLine = 0;

    // usually 8bit is unsigned
    if (wave.getWaveHeader().getBitsPerSample() == 8) {
      // for unsigned signals, the middle is 0.5 (0~1)
      middleLine = 0.5;
    }

    double[] nAmplitudes = wave.getNormalizedAmplitudes();
    // int width = (int) (nAmplitudes.length / wave.getWaveHeader().getSampleRate() / timeStep);
    int height = width / 3;
    int middle = height / 2;
    int magnifier = 10000;

    int numSamples = nAmplitudes.length;

    int numSamplePerTimeFrame = numSamples / width;

    int[] scaledPosAmplitudes = new int[width];
    int[] scaledNegAmplitudes = new int[width];

    // width scaling
    for (int i = 0; i < width; i++) {
      double sumPosAmplitude = 0;
      double sumNegAmplitude = 0;
      int startSample = i * numSamplePerTimeFrame;
      for (int j = 0; j < numSamplePerTimeFrame; j++) {
        double a = nAmplitudes[startSample + j];
        if (a > middleLine) {
          sumPosAmplitude += (a - middleLine);
        } else {
          sumNegAmplitude += (a - middleLine);
        }
      }

      int scaledPosAmplitude = (int) (sumPosAmplitude / numSamplePerTimeFrame * magnifier + middle);
      int scaledNegAmplitude = (int) (sumNegAmplitude / numSamplePerTimeFrame * magnifier + middle);

      scaledPosAmplitudes[i] = scaledPosAmplitude;
      scaledNegAmplitudes[i] = scaledNegAmplitude;
    }

    // render wave form image
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // set default white background
    Graphics2D graphics = bufferedImage.createGraphics();
    graphics.setPaint(new Color(255, 255, 255));
    graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

    for (int i = 0; i < width; i++) {
      for (int j = scaledNegAmplitudes[i]; j < scaledPosAmplitudes[i]; j++) {
        int y = height - j; // j from -ve to +ve, i.e. draw from top to bottom
        if (y < 0) {
          y = 0;
        } else if (y >= height) {
          y = height - 1;
        }
        bufferedImage.setRGB(i, y, 0);
      }
    }

    return bufferedImage;
  }

  /**
   * Render a waveform of a wave file
   * 
   * @param filename output file
   * @throws IOException
   * @see RGB graphic rendered
   */
  public void saveWaveform(Wave wave, int width, String filename) throws IOException {

    BufferedImage bufferedImage = renderWaveform(wave, width);
    saveWaveform(bufferedImage, filename);
  }

  /**
   * Render a waveform of a wave file
   * 
   * @param filename output file
   * @throws IOException
   * @see RGB graphic rendered
   */
  public void saveWaveform(BufferedImage bufferedImage, String filename) throws IOException {

    // export image
    int dotPos = filename.lastIndexOf(".");
    String extension = filename.substring(dotPos + 1);
    ImageIO.write(bufferedImage, extension, new File(filename));
  }

}