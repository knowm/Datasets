package org.knowm.datasets.cifar10;

import java.io.IOException;

public class CifarManager {

  private CifarImageFile images;

  /**
   * Constructs an instance managing the two given data files. Supports <code>NULL</code> value for one of the arguments in case reading only one of
   * the files (images and labels) is required.
   * 
   * @param imagesFile Can be <code>NULL</code>. In that case all future operations using that file will fail.
   * @throws IOException
   */
  public CifarManager(String imagesFile) throws IOException {

    if (imagesFile != null) {
      images = new CifarImageFile(imagesFile, "r");
    }
  }

  /**
   * Reads the current image.
   * 
   * @return matrix
   * @throws IOException
   */
  public CifarRaw readImage() throws IOException {

    if (images == null) {
      throw new IllegalStateException("Images file not initialized.");
    }
    return images.readImage();
  }

  /**
   * Set the position to be read.
   * 
   * @param index
   */
  public void setCurrent(int index) {

    images.setCurrentIndex(index);
  }

  /**
   * Get the underlying images file as {@link CifarImageFile}.
   * 
   * @return {@link CifarImageFile}.
   */
  public CifarImageFile getImages() {

    return images;
  }
}
