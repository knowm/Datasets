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
package com.xeiam.datasets.censusincome.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xeiam.datasets.censusincome.CensusIncome;
import com.xeiam.datasets.censusincome.CensusIncomeDAO;

/**
 * @author timmolter
 */
// @Ignore
public class TestCensusIncomeDAO {

  @BeforeClass
  public static void setUpDB() {

    CensusIncomeDAO.initTest();
  }

  @AfterClass
  public static void tearDownDB() {

    CensusIncomeDAO.testRelease();
  }

  @Test
  public void testSelectAll() {

    List<CensusIncome> allData = CensusIncomeDAO.selectAll();
    assertThat(allData.size(), equalTo(48842));
  }

  @Test
  public void testTrainData() {

    List<CensusIncome> data = CensusIncomeDAO.selectTrainData();
    assertThat(data.size(), equalTo(32561));

  }

  @Test
  public void testTestData() {

    List<CensusIncome> data = CensusIncomeDAO.selectTestData();
    assertThat(data.size(), equalTo(16281));

  }

}
