/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
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
package org.knowm.datasets.pcb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.FileUtils;

/**
 * @author timmolter
 */
public class RawData2DB {

  public static void main(String[] args) throws IOException {

    PCBDAO.init(args);
    PCBDAO.dropTable();
    PCBDAO.createTable();
    PCBAnnotationDAO.init(args);
    PCBAnnotationDAO.dropTable();
    PCBAnnotationDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing PCB images 1...");
    dp.go("./raw/cvl_pcb_dslr_1/");
    System.out.println("processing PCB images 2...");
    dp.go("./raw/cvl_pcb_dslr_2/");
    System.out.println("processing PCB images 3...");
    dp.go("./raw/cvl_pcb_dslr_3/");
    System.out.println("processing PCB images 4...");
    dp.go("./raw/cvl_pcb_dslr_4/");
    System.out.println("processing PCB images 5...");
    dp.go("./raw/cvl_pcb_dslr_5/");
    System.out.println("processing PCB images 6...");
    dp.go("./raw/cvl_pcb_dslr_6/");
    System.out.println("processing PCB images 7...");
    dp.go("./raw/cvl_pcb_dslr_7/");
    System.out.println("processing PCB images 8...");
    dp.go("./raw/cvl_pcb_dslr_8/");
    System.out.println("done.");

    PCBDAO.release();
    PCBAnnotationDAO.release();
  }

  private void go(String imageDataFolder) throws IOException {

    File root = new File(imageDataFolder);
    String[] directories = root.list();

    for (String directory : directories) {

      if (new File(imageDataFolder + directory).isDirectory()) {

        System.out.println(directory);

        String pcbID = directory.replace("pcb", "");
        Integer pcbIDAsInt = Integer.parseInt(pcbID);

        int counter = 1; // Each folder has multiple images of same PCB at different orientations

        File pcbDir = new File(imageDataFolder + directory);

        //        try { // keep processing images until no more are found by causing an IOException
        //          while (true) {
        String jpgFileName = imageDataFolder + directory + "/rec" + counter + ".jpg";
        //                  BufferedImage pcbimage = ImageIO.read(new File(imageDataFolder + directory + "/rec" + counter + ".jpg"));
        //          BufferedImage pcbMaskimage = ImageIO.read(new File(imageDataFolder + directory + "/rec" + counter + "-mask.png"));
        String annotationData = FileUtils.readFileToString(new File(imageDataFolder + directory + "/rec" + counter + "-annot.txt"), "UTF-8");

        // the png file Bytes
        File srcFile = new File(jpgFileName);
        try (InputStream fis = new FileInputStream(srcFile)) {

          byte[] buffer = new byte[(int) srcFile.length()];
          // System.out.println(buffer.length);
          fis.read(buffer, 0, buffer.length);

          PCB pcb = new PCB();
          pcb.setId(pcbIDAsInt);

          pcb.setImgbytes(new SerialBlob(buffer));
          PCBDAO.insert(pcb);

        } catch (Exception e) {
          e.printStackTrace();
        }

        String[] lines = annotationData.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {

          if (lines[i].length() > 1) {

            //          System.out.println(lines[i]);

            String[] entries = lines[i].split(" ");

            PCBAnnotation pcbAnnotation = new PCBAnnotation();
            pcbAnnotation.setPcbid(pcbIDAsInt);
            pcbAnnotation.setId(i);
            pcbAnnotation.setX(Float.parseFloat(entries[0].trim()));
            pcbAnnotation.setY(Float.parseFloat(entries[1].trim()));
            pcbAnnotation.setWidth(Float.parseFloat(entries[2].trim()));
            pcbAnnotation.setHeight(Float.parseFloat(entries[3].trim()));
            pcbAnnotation.setRotation(Float.parseFloat(entries[4].trim()));
            if (entries.length > 6) {
              pcbAnnotation.setName(entries[5].trim());
            }
            PCBAnnotationDAO.insert(pcbAnnotation);
          }
        }

      }

    }

  }
}
