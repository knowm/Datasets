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
package com.xeiam.datasets.nslkdd;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Splitter;

/**
 * @author timmolter
 */
public class RawData2DB {

  int idx = 0;

  public static void main(String[] args) throws IOException {

    NSLKDDDAO.init(args);

    NSLKDDDAO.dropTable();
    NSLKDDDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing NSL-KDD data...");
    dp.go("./raw/KDDTrain+.txt");
    dp.go("./raw/KDDTest+.txt");

    System.out.println("done.");

    NSLKDDDAO.release();
  }

  private void go(String fileName) throws IOException {

    List<String> labelsLines = FileUtils.readLines(new File(fileName), "UTF-8");

    for (int i = 0; i < labelsLines.size(); i++) {

      String labelLine = labelsLines.get(i);

      System.out.println(labelLine);

      Iterable<String> labels = Splitter.on(",").split(labelLine);
      Iterator<String> itr = labels.iterator();

      NSLKDD nSLKDD = new NSLKDD();
      nSLKDD.setId(idx);
      nSLKDD.setDuration(Float.parseFloat(itr.next()));
      nSLKDD.setProtocol_type(itr.next());
      nSLKDD.setService(itr.next());
      nSLKDD.setFlag(itr.next());
      nSLKDD.setSrc_bytes(Float.parseFloat(itr.next()));

      nSLKDD.setDst_bytes(Float.parseFloat(itr.next()));
      nSLKDD.setLand(itr.next());
      nSLKDD.setWrong_fragment(Float.parseFloat(itr.next()));
      nSLKDD.setUrgent(Float.parseFloat(itr.next()));
      nSLKDD.setHot(Float.parseFloat(itr.next()));
      nSLKDD.setNum_failed_logins(Float.parseFloat(itr.next()));
      nSLKDD.setLogged_in(itr.next());
      nSLKDD.setNum_compromised(Float.parseFloat(itr.next()));
      nSLKDD.setRoot_shell(Float.parseFloat(itr.next()));
      nSLKDD.setSu_attempted(Float.parseFloat(itr.next()));
      nSLKDD.setNum_root(Float.parseFloat(itr.next()));
      nSLKDD.setNum_file_creations(Float.parseFloat(itr.next()));
      nSLKDD.setNum_shells(Float.parseFloat(itr.next()));
      nSLKDD.setNum_access_files(Float.parseFloat(itr.next()));
      nSLKDD.setNum_outbound_cmds(Float.parseFloat(itr.next()));
      nSLKDD.setIs_host_login(itr.next());
      nSLKDD.setIs_guest_login(itr.next());
      nSLKDD.setConcurrentcount(Float.parseFloat(itr.next()));
      nSLKDD.setSrv_count(Float.parseFloat(itr.next()));
      nSLKDD.setSerror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setSrv_serror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setRerror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setSrv_rerror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setSame_srv_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDiff_srv_rate(Float.parseFloat(itr.next()));
      nSLKDD.setSrv_diff_host_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_count(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_srv_count(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_same_srv_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_diff_srv_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_same_src_port_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_srv_diff_host_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_serror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_srv_serror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_rerror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setDst_host_srv_rerror_rate(Float.parseFloat(itr.next()));
      nSLKDD.setTruth(itr.next());

      NSLKDDDAO.insert(nSLKDD);
      idx++;

      System.out.println("Number parsed: " + idx);

    }
  }
}
