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

import static org.fest.assertions.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author timmolter
 */
public class TestPCBDAO {

  @BeforeClass
  public static void setUpDB() {

    PCBDAO.init(new String[0]);
    PCBAnnotationDAO.init(new String[0]);
  }

  @AfterClass
  public static void tearDownDB() {

    PCBDAO.release();
    PCBAnnotationDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = PCBDAO.selectCount();
    assertThat(count).isEqualTo(165L);
  }

  @Test
  public void testImage() {

    PCB pcb = PCBDAO.selectSingle(1);

    try (InputStream bytes = pcb.getImgbytes().getBinaryStream();) {

      BufferedImage bufferedImage = ImageIO.read(bytes);
      assertThat(bufferedImage).isNotNull();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAnnotations() {

    long count = PCBAnnotationDAO.selectCount();
    System.out.println("" + count);

    List<PCBAnnotation> pcbAnnotations = PCBAnnotationDAO.selectList(1);

    assertThat(pcbAnnotations).hasSize(23);

    for (PCBAnnotation pcbAnnotation : pcbAnnotations) {

      System.out.println(pcbAnnotation.toString());
    }
  }
}
