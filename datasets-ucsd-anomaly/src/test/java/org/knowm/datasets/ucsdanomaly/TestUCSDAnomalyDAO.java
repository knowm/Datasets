/**
 * (The MIT License)
 *
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package org.knowm.datasets.ucsdanomaly;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.knowm.datasets.ucsdanomaly.UCSDAnomaly;
import org.knowm.datasets.ucsdanomaly.UCSDAnomalyDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestUCSDAnomalyDAO {

  @BeforeClass
  public static void setUpDB() {

    UCSDAnomalyDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    UCSDAnomalyDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = UCSDAnomalyDAO.selectCount();
    assertThat(count, equalTo(14000L));
  }

  @Test
  public void testSelect() throws SQLException {

    UCSDAnomaly uCSDAnomaly = UCSDAnomalyDAO.selectSingle(3, 3);
    assertThat(uCSDAnomaly.getId(), equalTo(3));
    assertThat(uCSDAnomaly.getTifid(), equalTo(3));
    assertThat(uCSDAnomaly.isIsanomaly(), equalTo(true));
    // assertThat(new String(uCSDAnomaly.getTifbytes().getBytes(1, 4)), equalTo("test"));
  }

  // @Test
  public void test() {

    UCSDAnomaly uCSDAnomaly = UCSDAnomalyDAO.selectSingle(3, 3);
    InputStream bytes;
    try {
      bytes = uCSDAnomaly.getTifbytes().getBinaryStream();
      System.out.println();
      // got an image file
      // String filename = "./raw/001.png";
      // File f = new File(filename);
      // System.out.println(f.canRead());
      // System.out.println(Arrays.toString(ImageIO.getReaderFileSuffixes()));
      BufferedImage bufferedImage = ImageIO.read(bytes);
      // bufferedImage is the RenderedImage to be written

      // Graphics2D g2 = bufferedImage.createGraphics();
      // g2.drawImage(image, null, null);

      // tifPanel.add(new ImagePanel(bufferedImage), BorderLayout.PAGE_END);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
