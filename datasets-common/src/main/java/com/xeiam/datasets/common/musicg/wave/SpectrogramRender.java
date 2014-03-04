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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpectrogramRender {

  /**
   * Render a spectrogram of a wave file
   * 
   * @param spectrogram spectrogram object
   */
  public BufferedImage renderSpectrogram(Spectrogram spectrogram) {

    double[][] spectrogramData = spectrogram.getNormalizedSpectrogramData();

    int width = spectrogramData.length;
    int height = spectrogramData[0].length;

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int value;
        value = (int) (spectrogramData[i][j] * 255);
        bufferedImage.setRGB(i, j, value << 16 | value << 8 | value);
      }
    }

    return bufferedImage;

  }

  public void saveSpectrogram(BufferedImage bufferedImage, String filename) throws IOException {

    int dotPos = filename.lastIndexOf(".");
    String extension = filename.substring(dotPos + 1);
    ImageIO.write(bufferedImage, extension, new File(filename));
  }

  /**
   * Render a spectrogram of a wave file
   * 
   * @param spectrogram spectrogram object
   * @param filename output file
   * @throws IOException
   * @see RGB graphic rendered
   */
  public void saveSpectrogram(Spectrogram spectrogram, String filename) throws IOException {

    BufferedImage bufferedImage = renderSpectrogram(spectrogram);
    saveSpectrogram(bufferedImage, filename);
  }
}