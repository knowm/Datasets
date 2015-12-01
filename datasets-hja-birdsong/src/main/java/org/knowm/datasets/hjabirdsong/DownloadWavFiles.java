package org.knowm.datasets.hjabirdsong;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;

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

    List<String> wavNameLines = FileUtils.readLines(new File("./raw/id2filename.txt"), "UTF-8");

    for (int i = 0; i < wavNameLines.size(); i++) {

      String wavNameLine = wavNameLines.get(i);
      String wavFileName = wavNameLine.substring(wavNameLine.indexOf(",") + 1, wavNameLine.length()) + ".wav";
      System.out.println("downloading: " + wavFileName);
      URL url = new URL(baseURL + wavFileName);
      File wavDir = new File("./raw/wav/" + wavFileName);
      org.apache.commons.io.FileUtils.copyURLToFile(url, wavDir, 5000, 10000);
    }

  }

}
