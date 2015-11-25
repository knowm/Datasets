/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
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
package com.xeiam.datasets.hjabirdsong;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author timmolter
 */
@Ignore
public class TestHJABirdsongDAO {

  @BeforeClass
  public static void setUpDB() {

    HJABirdsongDAO.init(new String[0]);

  }

  @AfterClass
  public static void tearDownDB() {

    HJABirdsongDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = HJABirdsongDAO.selectCount();
    assertThat(count, equalTo(548L));
  }

  @Test
  public void testSelect() throws SQLException {

    HJABirdSong hJABirdSong = HJABirdsongDAO.selectSingle(3);
    assertThat(hJABirdSong.getBagid(), equalTo(3));
    assertThat(hJABirdSong.getLabels(), equalTo("1,3"));
    assertThat(hJABirdSong.getLabelsAsArray().get(0), equalTo(1));
    assertThat(hJABirdSong.getLabelsAsArray().get(1), equalTo(3));
    assertThat(hJABirdSong.getWavfilename(), equalTo("PC13_20090531_050000_10.wav"));
    // System.out.println(hJABirdSong.getWavbytes().length());
    // System.out.println(new String(hJABirdSong.getWavbytes().getBytes(1, 4)));
    // assertThat(new String(hJABirdSong.getWavbytes().getBytes(1, 4)), equalTo("test"));
  }
}
