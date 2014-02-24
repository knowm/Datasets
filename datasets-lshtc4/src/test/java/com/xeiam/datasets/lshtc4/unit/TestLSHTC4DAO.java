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
    assertThat(count, equalTo(2817603L));
  }

  @Test
  public void testTestData() {

    // first test object
    // 1 : 1,0 139:1 153:4 199:1 212:1 232:1 282:1 307:3 310:1 428:1 510:1 528:1 609:1 700:2 709:1 727:1 765:1 791:1 798:2 838:1 872:1 1007:1 1170:2 1374:1 1388:1 1409:1 1435:1 1892:1 2190:1 2197:1
    // 2253:1 2348:2 2570:1 2628:1 2713:1 3066:1 3406:1 3619:2 3628:2 3636:1 3649:2 5068:1 8385:1 9371:1 11248:1 11806:1
    LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(1);
    assertThat(lSHTC4.getId(), equalTo(1));
    System.out.println(lSHTC4.getFeatures());
    assertThat(lSHTC4.getFeaturesAsArray()[0], equalTo("139:1"));

    // last test object
    // 452167 : 452167,0 113:1 122:1 193:2 215:1 256:1 298:1 344:11 345:1 346:2 347:1 358:1 377:5 379:1 381:7 421:1 422:2 464:1 488:1 497:3 543:1 558:3 582:1 586:1 604:4 615:1 643:3 649:3 679:1 701:1
    // 715:1 756:2 828:1 862:1 889:1 924:1 932:2 1024:2 1037:1 1043:1 1056:1 1058:1 1187:1 1210:1 1264:1 1374:3 1377:1 1562:1 1720:1 1753:2 1997:2 2211:1 2245:1 2322:1 2337:1 2341:1 2371:1 2395:2
    // 2467:1 2469:1 2532:1 2557:2 2642:2 2676:1 2817:1 3213:3 3522:5 3693:1 3732:2 3851:7 3919:1 3959:2 3982:1 4139:2 4260:1 4310:2 4542:1 4587:1 4796:2 4831:1 5184:1 5431:1 5445:1 5671:1 5807:1
    // 7080:1 7301:1 7304:1 8176:1 9496:2 9735:1 9935:1 9988:1 10384:1 10637:2 10639:1 11796:1 15356:1 15598:1 15617:2 17404:2 17442:1 21758:1 23758:2 31274:1 31540:1 33480:1 33793:1 74533:1 91736:2
    // 184854:3 1611326:1
    lSHTC4 = LSHTC4DAO.selectSingle(452167);
    assertThat(lSHTC4.getId(), equalTo(452167));
    assertThat(lSHTC4.getFeaturesAsArray()[0], equalTo("113:1"));
  }

  @Test
  public void testTrainData() {

    // first train object
    // 452168 : 314523, 165538, 76255, 335416, 416827 1:1 2:1 3:1 4:1 5:1 6:1 7:1 8:1 9:1 10:1 11:1 12:2 13:1 14:2 15:1 16:1 17:1 18:1 19:1 20:1 21:1 22:1 23:1 24:5 25:1 26:1 27:1 28:1 29:1 30:3 31:1
    // 32:2 33:1 34:1 35:1 36:3 37:1 38:1 39:1 40:1 41:3 42:1 43:7 44:1 45:1 46:1 47:1 48:1 49:1 50:1 51:2 52:1 53:1 54:1 55:1 56:1 57:3 58:4 59:1 60:2 61:3 62:1 63:1 64:1 65:1 66:1 67:2 68:1 69:1
    // 70:1 71:1 72:1 73:1 74:1 75:2 76:2 77:1 78:1 79:1 80:1 81:1
    LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(452168);
    assertThat(lSHTC4.getId(), equalTo(452168));
    assertThat(lSHTC4.getFeaturesAsArray()[0], equalTo("1:1"));
    assertThat(lSHTC4.getLabelsAsArray()[0], equalTo("314523"));
    assertThat(lSHTC4.getLabelsAsArray().length, equalTo(5));

    // last train object
    // 2817603 : 364775, 179233 122:1 197:1 317:1 438:1 2001:1 2342:1 2636:1 3201:1 3824:1 7453:3 134640:1 1542929:1
    lSHTC4 = LSHTC4DAO.selectSingle(2817603);
    assertThat(lSHTC4.getId(), equalTo(2817603));
    assertThat(lSHTC4.getFeaturesAsArray()[0], equalTo("122:1"));
    assertThat(lSHTC4.getLabelsAsArray()[0], equalTo("364775"));
    assertThat(lSHTC4.getLabelsAsArray().length, equalTo(2));
  }
}
