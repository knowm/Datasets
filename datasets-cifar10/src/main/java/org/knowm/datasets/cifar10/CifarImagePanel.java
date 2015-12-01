package org.knowm.datasets.cifar10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author alexnugent
 */
public class CifarImagePanel extends JPanel {

  private BufferedImage image;

  public CifarImagePanel(int[][] red, int[][] green, int[][] blue, int scale) {

    image = new BufferedImage(red.length * scale, red[0].length * scale, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < red.length; y++) {
      for (int x = 0; x < red[0].length; x++) {
        int value = red[y][x] << 16 | green[y][x] << 8 | blue[y][x];
        image.setRGB(x, y, value);
      }
    }
    scale(scale);

    setPreferredSize(new Dimension(red.length * scale, red[0].length * scale));
  }

  public CifarImagePanel(int[][] gray, int scale) {

    image = new BufferedImage(gray.length * scale, gray[0].length * scale, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < gray.length; y++) {
      for (int x = 0; x < gray[0].length; x++) {
        int value = gray[y][x] << 16 | gray[y][x] << 8 | gray[y][x];
        image.setRGB(x, y, value);
      }
    }
    scale(scale);

    setPreferredSize(new Dimension(gray.length * scale, gray[0].length * scale));
  }

  private void scale(int scale) {

    int w = image.getWidth();
    int h = image.getHeight();
    BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    at.scale(scale, scale);
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    after = scaleOp.filter(image, after);

    this.image = after;

  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
  }

}
