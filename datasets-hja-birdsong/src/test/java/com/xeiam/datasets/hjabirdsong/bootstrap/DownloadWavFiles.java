/**
 * Copyright (C) 2013 Xeiam LLC http://xeiam.com
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
package com.xeiam.datasets.hjabirdsong.bootstrap;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author timmolter
 */
public class DownloadWavFiles {

  public static void main(String[] args) throws IOException {

    DownloadWavFiles downloadWavFiles = new DownloadWavFiles();
    downloadWavFiles.go();
  }

  private void go() throws IOException {

    String baseURL = "http://web.engr.oregonstate.edu/~briggsf/kdd2012datasets/hja_birdsong/src_wavs/";

    String baseWavFileName = "_20090531_050000_";

    String[] prefixes = new String[] { "PC1", "PC4", "PC7", "PC8", "PC13", "PC15", };
    int numFilePerPrefix = 120;
    for (int i = 0; i < prefixes.length; i++) {
      String prefix = prefixes[i];
      for (int j = 0; j < numFilePerPrefix; j++) {

        String wavFileName = prefix + baseWavFileName + j + ".wav";
        System.out.println("downloading: " + wavFileName);
        URL url = new URL(baseURL + wavFileName);
        File wavDir = new File("./raw/wav/" + wavFileName);
        org.apache.commons.io.FileUtils.copyURLToFile(url, wavDir, 5000, 10000);
      }

    }

  }

}
