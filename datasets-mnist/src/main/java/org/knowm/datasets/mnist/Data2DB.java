package org.knowm.datasets.mnist;

import java.io.IOException;

import mnist.tools.MnistManager;

/**
 * @author timmolter
 */
public class Data2DB {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    MnistDAO.init(new String[0]);

    MnistDAO.dropTable();
    MnistDAO.createTable();

    Data2DB dp = new Data2DB();
    System.out.println("processing MNIST training images...");
    dp.go("./raw/train-images-idx3-ubyte", "./raw/train-labels-idx1-ubyte", 1, 60000);
    System.out.println("processing MNIST test images...");
    dp.go("./raw/t10k-images-idx3-ubyte", "./raw/t10k-labels-idx1-ubyte", 1, 10000);
    System.out.println("done.");

    MnistDAO.release();
  }

  private void go(String imageDataFile, String labelDataFile, int startIdx, int endIdx) throws IOException {

    int longestStringLength = 0;

    MnistManager mnistManager = new MnistManager(imageDataFile, labelDataFile);
    for (int n = startIdx; n <= endIdx; n++) {
      mnistManager.setCurrent(n); // index of the image that we are interested in
      StringBuilder sb = new StringBuilder();
      int[][] image = mnistManager.readImage();
      for (int i = 0; i < image[0].length; i++) {
        for (int j = 0; j < image.length; j++) {
          if (image[i][j] > 0) {
            sb.append(i);
            sb.append(":");
            sb.append(j);
            sb.append(":");
            sb.append(image[i][j]);
            sb.append(",");
          }
        }
      }
      String imagedata = sb.toString();
      // System.out.println(imagedata);
      if (imagedata.length() > longestStringLength) {
        longestStringLength = imagedata.length();
      }
      Mnist mnist = new Mnist();
      mnist.setId(idx++);
      mnist.setLabel(mnistManager.readLabel());
      mnist.setImagedata(imagedata);
      MnistDAO.insert(mnist);
    }
    // System.out.println("longestStringLength: " + longestStringLength);
  }
}
