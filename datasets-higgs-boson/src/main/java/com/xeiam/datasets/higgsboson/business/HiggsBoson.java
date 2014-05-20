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
package com.xeiam.datasets.higgsboson.business;

/**
 * @author timmolter
 */
public class HiggsBoson {

  private int EventId;
  private float DER_mass_MMC;
  private float DER_mass_transverse_met_lep;
  private float DER_mass_vis;
  private float DER_pt_h;
  private float DER_deltaeta_jet_jet;
  private float DER_mass_jet_jet;
  private float DER_prodeta_jet_jet;
  private float DER_deltar_tau_lep;
  private float DER_pt_tot;
  private float DER_sum_pt;
  private float DER_pt_ratio_lep_tau;
  private float DER_met_phi_centrality;
  private float DER_lep_eta_centrality;
  private float PRI_tau_pt;
  private float PRI_tau_eta;
  private float PRI_tau_phi;
  private float PRI_lep_pt;
  private float PRI_lep_eta;
  private float PRI_lep_phi;
  private float PRI_met;
  private float PRI_met_phi;
  private float PRI_met_sumet;
  private float PRI_jet_num;
  private float PRI_jet_leading_pt;
  private float PRI_jet_leading_eta;
  private float PRI_jet_leading_phi;
  private float PRI_jet_subleading_pt;
  private float PRI_jet_subleading_eta;
  private float PRI_jet_subleading_phi;
  private float PRI_jet_all_pt;
  private float Weight;
  private String Label;

  public int getEventId() {

    return EventId;
  }

  public void setEventId(int eventId) {

    EventId = eventId;
  }

  public float getDER_mass_MMC() {

    return DER_mass_MMC;
  }

  public void setDER_mass_MMC(float dER_mass_MMC) {

    DER_mass_MMC = dER_mass_MMC;
  }

  public float getDER_mass_transverse_met_lep() {

    return DER_mass_transverse_met_lep;
  }

  public void setDER_mass_transverse_met_lep(float dER_mass_transverse_met_lep) {

    DER_mass_transverse_met_lep = dER_mass_transverse_met_lep;
  }

  public float getDER_mass_vis() {

    return DER_mass_vis;
  }

  public void setDER_mass_vis(float dER_mass_vis) {

    DER_mass_vis = dER_mass_vis;
  }

  public float getDER_pt_h() {

    return DER_pt_h;
  }

  public void setDER_pt_h(float dER_pt_h) {

    DER_pt_h = dER_pt_h;
  }

  public float getDER_deltaeta_jet_jet() {

    return DER_deltaeta_jet_jet;
  }

  public void setDER_deltaeta_jet_jet(float dER_deltaeta_jet_jet) {

    DER_deltaeta_jet_jet = dER_deltaeta_jet_jet;
  }

  public float getDER_mass_jet_jet() {

    return DER_mass_jet_jet;
  }

  public void setDER_mass_jet_jet(float dER_mass_jet_jet) {

    DER_mass_jet_jet = dER_mass_jet_jet;
  }

  public float getDER_prodeta_jet_jet() {

    return DER_prodeta_jet_jet;
  }

  public void setDER_prodeta_jet_jet(float dER_prodeta_jet_jet) {

    DER_prodeta_jet_jet = dER_prodeta_jet_jet;
  }

  public float getDER_deltar_tau_lep() {

    return DER_deltar_tau_lep;
  }

  public void setDER_deltar_tau_lep(float dER_deltar_tau_lep) {

    DER_deltar_tau_lep = dER_deltar_tau_lep;
  }

  public float getDER_pt_tot() {

    return DER_pt_tot;
  }

  public void setDER_pt_tot(float dER_pt_tot) {

    DER_pt_tot = dER_pt_tot;
  }

  public float getDER_sum_pt() {

    return DER_sum_pt;
  }

  public void setDER_sum_pt(float dER_sum_pt) {

    DER_sum_pt = dER_sum_pt;
  }

  public float getDER_pt_ratio_lep_tau() {

    return DER_pt_ratio_lep_tau;
  }

  public void setDER_pt_ratio_lep_tau(float dER_pt_ratio_lep_tau) {

    DER_pt_ratio_lep_tau = dER_pt_ratio_lep_tau;
  }

  public float getDER_met_phi_centrality() {

    return DER_met_phi_centrality;
  }

  public void setDER_met_phi_centrality(float dER_met_phi_centrality) {

    DER_met_phi_centrality = dER_met_phi_centrality;
  }

  public float getDER_lep_eta_centrality() {

    return DER_lep_eta_centrality;
  }

  public void setDER_lep_eta_centrality(float dER_lep_eta_centrality) {

    DER_lep_eta_centrality = dER_lep_eta_centrality;
  }

  public float getPRI_tau_pt() {

    return PRI_tau_pt;
  }

  public void setPRI_tau_pt(float pRI_tau_pt) {

    PRI_tau_pt = pRI_tau_pt;
  }

  public float getPRI_tau_eta() {

    return PRI_tau_eta;
  }

  public void setPRI_tau_eta(float pRI_tau_eta) {

    PRI_tau_eta = pRI_tau_eta;
  }

  public float getPRI_tau_phi() {

    return PRI_tau_phi;
  }

  public void setPRI_tau_phi(float pRI_tau_phi) {

    PRI_tau_phi = pRI_tau_phi;
  }

  public float getPRI_lep_pt() {

    return PRI_lep_pt;
  }

  public void setPRI_lep_pt(float pRI_lep_pt) {

    PRI_lep_pt = pRI_lep_pt;
  }

  public float getPRI_lep_eta() {

    return PRI_lep_eta;
  }

  public void setPRI_lep_eta(float pRI_lep_eta) {

    PRI_lep_eta = pRI_lep_eta;
  }

  public float getPRI_lep_phi() {

    return PRI_lep_phi;
  }

  public void setPRI_lep_phi(float pRI_lep_phi) {

    PRI_lep_phi = pRI_lep_phi;
  }

  public float getPRI_met() {

    return PRI_met;
  }

  public void setPRI_met(float pRI_met) {

    PRI_met = pRI_met;
  }

  public float getPRI_met_phi() {

    return PRI_met_phi;
  }

  public void setPRI_met_phi(float pRI_met_phi) {

    PRI_met_phi = pRI_met_phi;
  }

  public float getPRI_met_sumet() {

    return PRI_met_sumet;
  }

  public void setPRI_met_sumet(float pRI_met_sumet) {

    PRI_met_sumet = pRI_met_sumet;
  }

  public float getPRI_jet_num() {

    return PRI_jet_num;
  }

  public void setPRI_jet_num(float pRI_jet_num) {

    PRI_jet_num = pRI_jet_num;
  }

  public float getPRI_jet_leading_pt() {

    return PRI_jet_leading_pt;
  }

  public void setPRI_jet_leading_pt(float pRI_jet_leading_pt) {

    PRI_jet_leading_pt = pRI_jet_leading_pt;
  }

  public float getPRI_jet_leading_eta() {

    return PRI_jet_leading_eta;
  }

  public void setPRI_jet_leading_eta(float pRI_jet_leading_eta) {

    PRI_jet_leading_eta = pRI_jet_leading_eta;
  }

  public float getPRI_jet_leading_phi() {

    return PRI_jet_leading_phi;
  }

  public void setPRI_jet_leading_phi(float pRI_jet_leading_phi) {

    PRI_jet_leading_phi = pRI_jet_leading_phi;
  }

  public float getPRI_jet_subleading_pt() {

    return PRI_jet_subleading_pt;
  }

  public void setPRI_jet_subleading_pt(float pRI_jet_subleading_pt) {

    PRI_jet_subleading_pt = pRI_jet_subleading_pt;
  }

  public float getPRI_jet_subleading_eta() {

    return PRI_jet_subleading_eta;
  }

  public void setPRI_jet_subleading_eta(float pRI_jet_subleading_eta) {

    PRI_jet_subleading_eta = pRI_jet_subleading_eta;
  }

  public float getPRI_jet_subleading_phi() {

    return PRI_jet_subleading_phi;
  }

  public void setPRI_jet_subleading_phi(float pRI_jet_subleading_phi) {

    PRI_jet_subleading_phi = pRI_jet_subleading_phi;
  }

  public float getPRI_jet_all_pt() {

    return PRI_jet_all_pt;
  }

  public void setPRI_jet_all_pt(float pRI_jet_all_pt) {

    PRI_jet_all_pt = pRI_jet_all_pt;
  }

  public float getWeight() {

    return Weight;
  }

  public void setWeight(float weight) {

    Weight = weight;
  }

  public String getLabel() {

    return Label;
  }

  public void setLabel(String label) {

    Label = label;
  }

  @Override
  public String toString() {

    return "HiggsBoson [EventId=" + EventId + ", DER_mass_MMC=" + DER_mass_MMC + ", DER_mass_transverse_met_lep=" + DER_mass_transverse_met_lep + ", DER_mass_vis=" + DER_mass_vis + ", DER_pt_h="
        + DER_pt_h + ", DER_deltaeta_jet_jet=" + DER_deltaeta_jet_jet + ", DER_mass_jet_jet=" + DER_mass_jet_jet + ", DER_prodeta_jet_jet=" + DER_prodeta_jet_jet + ", DER_deltar_tau_lep="
        + DER_deltar_tau_lep + ", DER_pt_tot=" + DER_pt_tot + ", DER_sum_pt=" + DER_sum_pt + ", DER_pt_ratio_lep_tau=" + DER_pt_ratio_lep_tau + ", DER_met_phi_centrality=" + DER_met_phi_centrality
        + ", DER_lep_eta_centrality=" + DER_lep_eta_centrality + ", PRI_tau_pt=" + PRI_tau_pt + ", PRI_tau_eta=" + PRI_tau_eta + ", PRI_tau_phi=" + PRI_tau_phi + ", PRI_lep_pt=" + PRI_lep_pt
        + ", PRI_lep_eta=" + PRI_lep_eta + ", PRI_lep_phi=" + PRI_lep_phi + ", PRI_met=" + PRI_met + ", PRI_met_phi=" + PRI_met_phi + ", PRI_met_sumet=" + PRI_met_sumet + ", PRI_jet_num="
        + PRI_jet_num + ", PRI_jet_leading_pt=" + PRI_jet_leading_pt + ", PRI_jet_leading_eta=" + PRI_jet_leading_eta + ", PRI_jet_leading_phi=" + PRI_jet_leading_phi + ", PRI_jet_subleading_pt="
        + PRI_jet_subleading_pt + ", PRI_jet_subleading_eta=" + PRI_jet_subleading_eta + ", PRI_jet_subleading_phi=" + PRI_jet_subleading_phi + ", PRI_jet_all_pt=" + PRI_jet_all_pt + ", Weight="
        + Weight + ", Label=" + Label + "]";
  }

}
