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
package com.xeiam.datasets.nslkdd.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.xeiam.datasets.nslkdd.NSLKDD;
import com.xeiam.datasets.nslkdd.NSLKDDDAO;

/**
 * @author timmolter
 */
@Ignore
public class TestNSLKDDDAO {

  @BeforeClass
  public static void setUpDB() {

    NSLKDDDAO.initTest();

  }

  @AfterClass
  public static void tearDownDB() {

    NSLKDDDAO.release();
  }

  @Test
  public void testSelectCount() {

    long count = NSLKDDDAO.selectCount();
    assertThat(count, equalTo(148517L));
  }

  @Test
  public void testSelectSingle() {

    NSLKDD nSLKDD = NSLKDDDAO.selectSingle(4);
    System.out.println(nSLKDD);
    assertThat(nSLKDD.getSrc_bytes(), equalTo(199f));
  }

}
