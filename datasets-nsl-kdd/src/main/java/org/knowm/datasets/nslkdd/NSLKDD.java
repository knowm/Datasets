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
package org.knowm.datasets.nslkdd;

import org.knowm.datasets.common.business.Bean;

/**
 * An explanation of the fields can be found here: https://archive.ics.uci.edu/ml/machine-learning-databases/kddcup99-mld/task.html
 *
 * @author timmolter
 */
public class NSLKDD extends Bean {

  private float duration;
  private String protocol_type; // {'tcp','udp', 'icmp'}
  private String service; // {'aol', 'auth', 'bgp', 'courier', 'csnet_ns', 'ctf', 'daytime', 'discard', 'domain', 'domain_u', 'echo', 'eco_i', 'ecr_i', 'efs', 'exec', 'finger', 'ftp', 'ftp_data',
  // 'gopher', 'harvest', 'hostnames', 'http', 'http_2784', 'http_443', 'http_8001', 'imap4', 'IRC', 'iso_tsap', 'klogin', 'kshell', 'ldap', 'link', 'login', 'mtp', 'name',
  // 'netbios_dgm', 'netbios_ns', 'netbios_ssn', 'netstat', 'nnsp', 'nntp', 'ntp_u', 'other', 'pm_dump', 'pop_2', 'pop_3', 'printer', 'private', 'red_i', 'remote_job', 'rje',
  // 'shell', 'smtp', 'sql_net', 'ssh', 'sunrpc', 'supdup', 'systat', 'telnet', 'tftp_u', 'tim_i', 'time', 'urh_i', 'urp_i', 'uucp', 'uucp_path', 'vmnet', 'whois', 'X11',
  // 'Z39_50'}
  private String flag; // { 'OTH', 'REJ', 'RSTO', 'RSTOS0', 'RSTR', 'S0', 'S1', 'S2', 'S3', 'SF', 'SH' }
  private float src_bytes;
  private float dst_bytes;
  private String land; // {'0', '1'}
  private float wrong_fragment;
  private float urgent;
  private float hot;
  private float num_failed_logins;
  private String logged_in; // {'0', '1'}
  private float num_compromised;
  private float root_shell;
  private float su_attempted;
  private float num_root;
  private float num_file_creations;
  private float num_shells;
  private float num_access_files;
  private float num_outbound_cmds;
  private String is_host_login; // {'0', '1'}
  private String is_guest_login; // {'0', '1'}
  private float concurrentcount;
  private float srv_count;
  private float serror_rate;
  private float srv_serror_rate;
  private float rerror_rate;
  private float srv_rerror_rate;
  private float same_srv_rate;
  private float diff_srv_rate;
  private float srv_diff_host_rate;
  private float dst_host_count;
  private float dst_host_srv_count;
  private float dst_host_same_srv_rate;
  private float dst_host_diff_srv_rate;
  private float dst_host_same_src_port_rate;
  private float dst_host_srv_diff_host_rate;
  private float dst_host_serror_rate;
  private float dst_host_srv_serror_rate;
  private float dst_host_rerror_rate;
  private float dst_host_srv_rerror_rate;
  private String truth; // {'normal', 'anomaly'}

  public float getDuration() {

    return duration;
  }

  public void setDuration(float duration) {

    this.duration = duration;
  }

  public String getProtocol_type() {

    return protocol_type;
  }

  public void setProtocol_type(String protocol_type) {

    this.protocol_type = protocol_type;
  }

  public String getService() {

    return service;
  }

  public void setService(String service) {

    this.service = service;
  }

  public String getFlag() {

    return flag;
  }

  public void setFlag(String flag) {

    this.flag = flag;
  }

  public float getSrc_bytes() {

    return src_bytes;
  }

  public void setSrc_bytes(float src_bytes) {

    this.src_bytes = src_bytes;
  }

  public float getDst_bytes() {

    return dst_bytes;
  }

  public void setDst_bytes(float dst_bytes) {

    this.dst_bytes = dst_bytes;
  }

  public String getLand() {

    return land;
  }

  public void setLand(String land) {

    this.land = land;
  }

  public float getWrong_fragment() {

    return wrong_fragment;
  }

  public void setWrong_fragment(float wrong_fragment) {

    this.wrong_fragment = wrong_fragment;
  }

  public float getUrgent() {

    return urgent;
  }

  public void setUrgent(float urgent) {

    this.urgent = urgent;
  }

  public float getHot() {

    return hot;
  }

  public void setHot(float hot) {

    this.hot = hot;
  }

  public float getNum_failed_logins() {

    return num_failed_logins;
  }

  public void setNum_failed_logins(float num_failed_logins) {

    this.num_failed_logins = num_failed_logins;
  }

  public String getLogged_in() {

    return logged_in;
  }

  public void setLogged_in(String logged_in) {

    this.logged_in = logged_in;
  }

  public float getNum_compromised() {

    return num_compromised;
  }

  public void setNum_compromised(float num_compromised) {

    this.num_compromised = num_compromised;
  }

  public float getRoot_shell() {

    return root_shell;
  }

  public void setRoot_shell(float root_shell) {

    this.root_shell = root_shell;
  }

  public float getSu_attempted() {

    return su_attempted;
  }

  public void setSu_attempted(float su_attempted) {

    this.su_attempted = su_attempted;
  }

  public float getNum_root() {

    return num_root;
  }

  public void setNum_root(float num_root) {

    this.num_root = num_root;
  }

  public float getNum_file_creations() {

    return num_file_creations;
  }

  public void setNum_file_creations(float num_file_creations) {

    this.num_file_creations = num_file_creations;
  }

  public float getNum_shells() {

    return num_shells;
  }

  public void setNum_shells(float num_shells) {

    this.num_shells = num_shells;
  }

  public float getNum_access_files() {

    return num_access_files;
  }

  public void setNum_access_files(float num_access_files) {

    this.num_access_files = num_access_files;
  }

  public float getNum_outbound_cmds() {

    return num_outbound_cmds;
  }

  public void setNum_outbound_cmds(float num_outbound_cmds) {

    this.num_outbound_cmds = num_outbound_cmds;
  }

  public String getIs_host_login() {

    return is_host_login;
  }

  public void setIs_host_login(String is_host_login) {

    this.is_host_login = is_host_login;
  }

  public String getIs_guest_login() {

    return is_guest_login;
  }

  public void setIs_guest_login(String is_guest_login) {

    this.is_guest_login = is_guest_login;
  }

  public float getConcurrentcount() {

    return concurrentcount;
  }

  public void setConcurrentcount(float concurrentcount) {

    this.concurrentcount = concurrentcount;
  }

  public float getSrv_count() {

    return srv_count;
  }

  public void setSrv_count(float srv_count) {

    this.srv_count = srv_count;
  }

  public float getSerror_rate() {

    return serror_rate;
  }

  public void setSerror_rate(float serror_rate) {

    this.serror_rate = serror_rate;
  }

  public float getSrv_serror_rate() {

    return srv_serror_rate;
  }

  public void setSrv_serror_rate(float srv_serror_rate) {

    this.srv_serror_rate = srv_serror_rate;
  }

  public float getRerror_rate() {

    return rerror_rate;
  }

  public void setRerror_rate(float rerror_rate) {

    this.rerror_rate = rerror_rate;
  }

  public float getSrv_rerror_rate() {

    return srv_rerror_rate;
  }

  public void setSrv_rerror_rate(float srv_rerror_rate) {

    this.srv_rerror_rate = srv_rerror_rate;
  }

  public float getSame_srv_rate() {

    return same_srv_rate;
  }

  public void setSame_srv_rate(float same_srv_rate) {

    this.same_srv_rate = same_srv_rate;
  }

  public float getDiff_srv_rate() {

    return diff_srv_rate;
  }

  public void setDiff_srv_rate(float diff_srv_rate) {

    this.diff_srv_rate = diff_srv_rate;
  }

  public float getSrv_diff_host_rate() {

    return srv_diff_host_rate;
  }

  public void setSrv_diff_host_rate(float srv_diff_host_rate) {

    this.srv_diff_host_rate = srv_diff_host_rate;
  }

  public float getDst_host_count() {

    return dst_host_count;
  }

  public void setDst_host_count(float dst_host_count) {

    this.dst_host_count = dst_host_count;
  }

  public float getDst_host_srv_count() {

    return dst_host_srv_count;
  }

  public void setDst_host_srv_count(float dst_host_srv_count) {

    this.dst_host_srv_count = dst_host_srv_count;
  }

  public float getDst_host_same_srv_rate() {

    return dst_host_same_srv_rate;
  }

  public void setDst_host_same_srv_rate(float dst_host_same_srv_rate) {

    this.dst_host_same_srv_rate = dst_host_same_srv_rate;
  }

  public float getDst_host_diff_srv_rate() {

    return dst_host_diff_srv_rate;
  }

  public void setDst_host_diff_srv_rate(float dst_host_diff_srv_rate) {

    this.dst_host_diff_srv_rate = dst_host_diff_srv_rate;
  }

  public float getDst_host_same_src_port_rate() {

    return dst_host_same_src_port_rate;
  }

  public void setDst_host_same_src_port_rate(float dst_host_same_src_port_rate) {

    this.dst_host_same_src_port_rate = dst_host_same_src_port_rate;
  }

  public float getDst_host_srv_diff_host_rate() {

    return dst_host_srv_diff_host_rate;
  }

  public void setDst_host_srv_diff_host_rate(float dst_host_srv_diff_host_rate) {

    this.dst_host_srv_diff_host_rate = dst_host_srv_diff_host_rate;
  }

  public float getDst_host_serror_rate() {

    return dst_host_serror_rate;
  }

  public void setDst_host_serror_rate(float dst_host_serror_rate) {

    this.dst_host_serror_rate = dst_host_serror_rate;
  }

  public float getDst_host_srv_serror_rate() {

    return dst_host_srv_serror_rate;
  }

  public void setDst_host_srv_serror_rate(float dst_host_srv_serror_rate) {

    this.dst_host_srv_serror_rate = dst_host_srv_serror_rate;
  }

  public float getDst_host_rerror_rate() {

    return dst_host_rerror_rate;
  }

  public void setDst_host_rerror_rate(float dst_host_rerror_rate) {

    this.dst_host_rerror_rate = dst_host_rerror_rate;
  }

  public float getDst_host_srv_rerror_rate() {

    return dst_host_srv_rerror_rate;
  }

  public void setDst_host_srv_rerror_rate(float dst_host_srv_rerror_rate) {

    this.dst_host_srv_rerror_rate = dst_host_srv_rerror_rate;
  }

  public String getTruth() {

    return truth;
  }

  public void setTruth(String truth) {

    this.truth = truth;
  }

  @Override
  public String toString() {

    return "NSLKDD [id=" + getId() + ", duration=" + duration + ", protocol_type=" + protocol_type + ", service=" + service + ", flag=" + flag
        + ", src_bytes=" + src_bytes + ", dst_bytes=" + dst_bytes + ", land=" + land + ", wrong_fragment=" + wrong_fragment + ", urgent=" + urgent
        + ", hot=" + hot + ", num_failed_logins=" + num_failed_logins + ", logged_in=" + logged_in + ", num_compromised=" + num_compromised
        + ", root_shell=" + root_shell + ", su_attempted=" + su_attempted + ", num_root=" + num_root + ", num_file_creations=" + num_file_creations
        + ", num_shells=" + num_shells + ", num_access_files=" + num_access_files + ", num_outbound_cmds=" + num_outbound_cmds + ", is_host_login="
        + is_host_login + ", is_guest_login=" + is_guest_login + ", concurrentcount=" + concurrentcount + ", srv_count=" + srv_count
        + ", serror_rate=" + serror_rate + ", srv_serror_rate=" + srv_serror_rate + ", rerror_rate=" + rerror_rate + ", srv_rerror_rate="
        + srv_rerror_rate + ", same_srv_rate=" + same_srv_rate + ", diff_srv_rate=" + diff_srv_rate + ", srv_diff_host_rate=" + srv_diff_host_rate
        + ", dst_host_count=" + dst_host_count + ", dst_host_srv_count=" + dst_host_srv_count + ", dst_host_same_srv_rate=" + dst_host_same_srv_rate
        + ", dst_host_diff_srv_rate=" + dst_host_diff_srv_rate + ", dst_host_same_src_port_rate=" + dst_host_same_src_port_rate
        + ", dst_host_srv_diff_host_rate=" + dst_host_srv_diff_host_rate + ", dst_host_serror_rate=" + dst_host_serror_rate
        + ", dst_host_srv_serror_rate=" + dst_host_srv_serror_rate + ", dst_host_rerror_rate=" + dst_host_rerror_rate + ", dst_host_srv_rerror_rate="
        + dst_host_srv_rerror_rate + ", truth=" + truth + "]";
  }

}
