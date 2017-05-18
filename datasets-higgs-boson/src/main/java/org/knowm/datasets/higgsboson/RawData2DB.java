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
package org.knowm.datasets.higgsboson;

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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author timmolter
 */
public class RawData2DB {

  public static void main(String[] args) throws IOException {

    HiggsBosonDAO.init(args);

    HiggsBosonDAO.dropTable();
    HiggsBosonDAO.createTable();

    RawData2DB dp = new RawData2DB();
    System.out.println("processing Higgs Boson training data...");
    dp.go("./raw/training.csv", true);
    System.out.println("processing Higgs Boson test data...");
    dp.go("./raw/test.csv", false);
    System.out.println("done.");

    HiggsBosonDAO.release();
  }

  private void go(String file, boolean isTrain) throws IOException {

    String data = FileUtils.readFileToString(new File(file), "UTF-8");

    System.out.println("loading data from " + file);
    String[] lines = data.split("\\r?\\n");
    for (int i = 0; i < lines.length; i++) {
      try {

        String[] entry = lines[i].split(",");
        HiggsBoson higgsBoson = new HiggsBoson();

        higgsBoson.setEventId(Integer.parseInt(entry[0]));
        higgsBoson.setDER_mass_MMC(Float.parseFloat(entry[1]));
        higgsBoson.setDER_mass_transverse_met_lep(Float.parseFloat(entry[2]));
        higgsBoson.setDER_mass_vis(Float.parseFloat(entry[3]));
        higgsBoson.setDER_pt_h(Float.parseFloat(entry[4]));
        higgsBoson.setDER_deltaeta_jet_jet(Float.parseFloat(entry[5]));
        higgsBoson.setDER_mass_jet_jet(Float.parseFloat(entry[6]));
        higgsBoson.setDER_prodeta_jet_jet(Float.parseFloat(entry[7]));
        higgsBoson.setDER_deltar_tau_lep(Float.parseFloat(entry[8]));
        higgsBoson.setDER_pt_tot(Float.parseFloat(entry[9]));
        higgsBoson.setDER_sum_pt(Float.parseFloat(entry[10]));
        higgsBoson.setDER_pt_ratio_lep_tau(Float.parseFloat(entry[11]));
        higgsBoson.setDER_met_phi_centrality(Float.parseFloat(entry[12]));
        higgsBoson.setDER_lep_eta_centrality(Float.parseFloat(entry[13]));
        higgsBoson.setPRI_tau_pt(Float.parseFloat(entry[14]));
        higgsBoson.setPRI_tau_eta(Float.parseFloat(entry[15]));
        higgsBoson.setPRI_tau_phi(Float.parseFloat(entry[16]));
        higgsBoson.setPRI_lep_pt(Float.parseFloat(entry[17]));
        higgsBoson.setPRI_lep_eta(Float.parseFloat(entry[18]));
        higgsBoson.setPRI_lep_phi(Float.parseFloat(entry[19]));
        higgsBoson.setPRI_met(Float.parseFloat(entry[20]));
        higgsBoson.setPRI_met_phi(Float.parseFloat(entry[21]));
        higgsBoson.setPRI_met_sumet(Float.parseFloat(entry[22]));
        higgsBoson.setPRI_jet_num(Float.parseFloat(entry[23]));
        higgsBoson.setPRI_jet_leading_pt(Float.parseFloat(entry[24]));
        higgsBoson.setPRI_jet_leading_eta(Float.parseFloat(entry[25]));
        higgsBoson.setPRI_jet_leading_phi(Float.parseFloat(entry[26]));
        higgsBoson.setPRI_jet_subleading_pt(Float.parseFloat(entry[27]));
        higgsBoson.setPRI_jet_subleading_eta(Float.parseFloat(entry[28]));
        higgsBoson.setPRI_jet_subleading_phi(Float.parseFloat(entry[29]));
        higgsBoson.setPRI_jet_all_pt(Float.parseFloat(entry[30]));

        if (isTrain) {
          higgsBoson.setWeight(Float.parseFloat(entry[31]));
          higgsBoson.setLabel(entry[32]);
        } else {
          higgsBoson.setWeight(-999.0f);
        }

        HiggsBosonDAO.insert(higgsBoson);

      } catch (Exception e) {
        System.out.println("error parsing one line.");
        System.out.println("error parsing one line.");
        e.printStackTrace();
        // eat it. Will throw exception on the first line of the test dataset.
      }
    }

  }
}
