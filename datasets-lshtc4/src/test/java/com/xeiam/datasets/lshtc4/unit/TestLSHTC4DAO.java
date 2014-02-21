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
package com.xeiam.datasets.lshtc4.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xeiam.datasets.lshtc4.LSHTC4;
import com.xeiam.datasets.lshtc4.LSHTC4DAO;

/**
 * @author timmolter
 */
// @Ignore
public class TestLSHTC4DAO {

  @BeforeClass
  public static void setUpDB() {

    LSHTC4DAO.initTest();

  }

  @AfterClass
  public static void tearDownDB() {

    LSHTC4DAO.testRelease();
  }

  @Test
  public void testSelectCount() {

    long count = LSHTC4DAO.selectCount();
    assertThat(count, equalTo(2817604L));
  }

  @Test
  public void testTrainData() {

    List<LSHTC4> data = LSHTC4DAO.selectTrainData();
    assertThat(data.size(), equalTo(2365437));
  }

  @Test
  public void testTestData() {

    List<LSHTC4> data = LSHTC4DAO.selectTestData();
    assertThat(data.size(), equalTo(452167));
  }

}
