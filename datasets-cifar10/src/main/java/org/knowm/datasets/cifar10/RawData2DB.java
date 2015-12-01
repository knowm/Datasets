package org.knowm.datasets.cifar10;

import java.io.IOException;

/**
 * @author timmolter
 */
public class RawData2DB {

  private static final int NumEntriesPerFile = 10000;

  int idx = 0;

  public static void main(String[] args) throws IOException {

    CifarDAO.init(args);

    CifarDAO.dropTable();
    CifarDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing Cifar training images 1...");
    dp.go("./raw/data_batch_1.bin");
    System.out.println("processing Cifar training images 2...");
    dp.go("./raw/data_batch_2.bin");
    System.out.println("processing Cifar training images 3...");
    dp.go("./raw/data_batch_3.bin");
    System.out.println("processing Cifar training images 4...");
    dp.go("./raw/data_batch_4.bin");
    System.out.println("processing Cifar training images 5...");
    dp.go("./raw/data_batch_5.bin");
    // System.out.println("processing Cifar test images...");
    dp.go("./raw/test_batch.bin");
    System.out.println("done.");

    CifarDAO.release();
  }

  private void go(String imageDataFile) throws IOException {

    int longestStringLength = 0;

    CifarManager cifarManager = new CifarManager(imageDataFile);
    for (int n = 1; n <= NumEntriesPerFile; n++) {

      cifarManager.setCurrent(n); // index of the image that we are interested in
      CifarRaw cifarRaw = cifarManager.readImage();
      // System.out.println(cifarRaw.getLabel());

      Cifar cifar = new Cifar();
      cifar.setId(idx++);
      cifar.setLabel(cifarRaw.getLabel());

      String imagedata = getChannelData(cifarRaw);
      // System.out.println(imagedata);
      if (imagedata.length() > longestStringLength) {
        longestStringLength = imagedata.length();
      }
      cifar.setImagedata(imagedata);

      CifarDAO.insert(cifar);
    }
    System.out.println("longestStringLength: " + longestStringLength);
  }

  private String getChannelData(CifarRaw cifarRaw) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < cifarRaw.getRedChannel()[0].length; i++) {
      for (int j = 0; j < cifarRaw.getRedChannel().length; j++) {
        if (cifarRaw.getRedChannel()[i][j] > 0) {
          sb.append(i);
          sb.append(":");
          sb.append(j);
          sb.append(":");
          sb.append(cifarRaw.getRedChannel()[i][j]);
          sb.append("_");
          sb.append(cifarRaw.getGreenChannel()[i][j]);
          sb.append("_");
          sb.append(cifarRaw.getBlueChannel()[i][j]);
          sb.append(",");
        }
      }
    }
    return sb.toString();
  }
}
