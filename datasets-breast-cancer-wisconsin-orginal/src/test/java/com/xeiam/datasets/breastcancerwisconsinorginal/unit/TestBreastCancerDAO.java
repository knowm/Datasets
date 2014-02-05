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
package com.xeiam.datasets.breastcancerwisconsinorginal.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xeiam.datasets.breastcancerwisconsinorginal.BreastCancer;
import com.xeiam.datasets.breastcancerwisconsinorginal.BreastCancerDAO;

/**
 * @author timmolter
 */
// @Ignore
public class TestBreastCancerDAO {

  @BeforeClass
  public static void setUpDB() {

    BreastCancerDAO.initTest();

  }

  @AfterClass
  public static void tearDownDB() {

    BreastCancerDAO.testRelease();
  }

  @Test
  public void testSelectAll() {

    List<BreastCancer> allData = BreastCancerDAO.selectAll();
    assertThat(allData.size(), equalTo(683));
  }

  @Test
  public void testTrainData() {

    List<BreastCancer> data = BreastCancerDAO.selectTrainData();
    assertThat(data.size(), equalTo(500));
  }

  @Test
  public void testTestData() {

    List<BreastCancer> data = BreastCancerDAO.selectTestData();
    assertThat(data.size(), equalTo(183));
  }

}
