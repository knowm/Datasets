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
package org.knowm.datasets.numenta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knowm.datasets.common.business.DatasetsDAO;
import org.knowm.yank.Yank;

public class NumentaDAO extends DatasetsDAO {

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static void dropTable() {

    String DROP_TABLE = "DROP TABLE IF EXISTS NUMENTA";
    Yank.execute(DROP_TABLE, null);
  }

  public static void insertSeriesPoint(SeriesPoint point) {

    Object[] params = new Object[] {

    // @formatter:off
        point.getId(),
        point.getSeriesGroup(),
        point.getSeriesName(),
        point.getTimestamp(),
        point.getValue(),
        point.getLabel()
        // @formatter:on
        };
    String INSERT = "INSERT INTO NUMENTA (id, seriesGroup, seriesName, timestamp, value, label) VALUES (?, ?, ?, ?, ?, ?)";
    Yank.execute(INSERT, params);
  }

  public static List<SeriesPoint> selectSeries(String series) {

    Object[] params = new Object[] { series };
    String SELECT_ALL_SQL = "SELECT * FROM NUMENTA WHERE seriesName = ? ORDER BY timestamp ASC";

    return Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, params);
  }

  public static List<String> selectSeriesNames() {

    String SELECT_SERIES_NAMES_SQL = "SELECT DISTINCT seriesName FROM NUMENTA";
    List<String> names = Yank.queryColumn(SELECT_SERIES_NAMES_SQL, "seriesName", String.class, null);
    return names;
  }

  public static String selectGroupForSeries(String series) {

    Object[] params = new Object[] { series };
    String SELECT_FIRST = "SELECT * FROM NUMENTA WHERE seriesName = ?";
    return Yank.queryBean(SELECT_FIRST, SeriesPoint.class, params).getSeriesGroup();
  }

  public static double selectMax(String series) {

    Object[] params = new Object[] { series };
    String SELECT_MAX = "SELECT MAX(value) FROM NUMENTA WHERE seriesName = ?";
    return Yank.queryScalar(SELECT_MAX, Double.class, params);
  }

  public static double selectMin(String series) {

    Object[] params = new Object[] { series };
    String SELECT_MIN = "SELECT MIN(value) FROM NUMENTA WHERE seriesName = ?";
    return Yank.queryScalar(SELECT_MIN, Double.class, params);
  }

  public static List<SeriesPoint> selectAll() {

    String SELECT_ALL_SQL = "SELECT * FROM NUMENTA";
    return Yank.queryBeanList(SELECT_ALL_SQL, SeriesPoint.class, null);
  }

  public static Long selectCount(String series) {

    Object[] params = new Object[] { series };
    String SELECT_COUNT = "SELECT COUNT(*) FROM NUMENTA WHERE seriesName = ?";
    return Yank.queryScalar(SELECT_COUNT, Long.class, params);
  }

  public static final Map<String, double[]> staticRange;
  static {
    Map<String, double[]> range = new HashMap<String, double[]>();
    range.put("art_daily_no_noise", new double[] { 20.0, 79.99997 });
    range.put("art_daily_perfect_square_wave", new double[] { 20.0, 80.0 });
    range.put("art_daily_small_noise", new double[] { 18.000963, 87.97613 });
    range.put("art_flatline", new double[] { 45.0, 45.0 });
    range.put("art_noisy ", new double[] { 8.000581, 18.996405 });
    range.put("art_daily_flatmiddle", new double[] { -21.998789, 87.95835 });
    range.put("art_daily_jumpsdown ", new double[] { 18.00204, 87.99838 });
    range.put("art_daily_jumpsup ", new double[] { 18.001009, 164.94748 });
    range.put("art_daily_nojump ", new double[] { 18.0005, 87.97331 });
    range.put("art_increase_spike_density ", new double[] { 0.0, 20.0 });
    range.put("art_load_balancer_spikes ", new double[] { 0.0, 3.224815 });
    range.put("exchange-2_cpc_results ", new double[] { 0.026843034, 0.22659793 });
    range.put("exchange-2_cpm_results ", new double[] { 3.8500494E-4, 1.0514425 });
    range.put("exchange-3_cpc_results ", new double[] { 0.038898848, 1.034 });
    range.put("exchange-3_cpm_results ", new double[] { 0.32065, 5.49754 });
    range.put("exchange-4_cpc_results ", new double[] { 0.023883704, 3.1268518 });
    range.put("exchange-4_cpm_results ", new double[] { 0.121520124, 16.4382 });
    range.put("ec2_cpu_utilization_24ae8d ", new double[] { 0.066, 2.344 });
    range.put("ec2_cpu_utilization_53ea38 ", new double[] { 1.604, 2.656 });
    range.put("ec2_cpu_utilization_5f5533 ", new double[] { 34.766, 68.092 });
    range.put("ec2_cpu_utilization_77c1ca ", new double[] { 0.064, 99.898 });
    range.put("ec2_cpu_utilization_825cc2 ", new double[] { 18.7225, 99.118 });
    range.put("ec2_cpu_utilization_ac20cd", new double[] { 2.464, 99.742 });
    range.put("ec2_cpu_utilization_c6585a", new double[] { 0.062, 1.602 });
    range.put("ec2_cpu_utilization_fe7f93", new double[] { 1.8, 99.668 });
    range.put("ec2_disk_write_bytes_1ef3de ", new double[] { 0.0, 5.4745702E8 });
    range.put("ec2_disk_write_bytes_c0d644", new double[] { 0.0, 8.6396403E8 });
    range.put("ec2_network_in_257a54", new double[] { 38516.6, 2.45126E8 });
    range.put("ec2_network_in_5abac7", new double[] { 42.0, 8285420.0 });
    range.put("elb_request_count_8c0756 ", new double[] { 1.0, 656.0 });
    range.put("grok_asg_anomaly", new double[] { 0.0, 45.6229 });
    range.put("iio_us-east-1_i-a2eb1cd9_NetworkIn", new double[] { 789781.0, 6.1519396E7 });
    range.put("rds_cpu_utilization_cc0c53 ", new double[] { 5.19, 25.1033 });
    range.put("rds_cpu_utilization_e47b3b ", new double[] { 12.628, 76.23 });
    range.put("ambient_temperature_system_failure ", new double[] { 57.458405, 86.22321 });
    range.put("cpu_utilization_asg_misconfiguration ", new double[] { 11.529, 100.0 });
    range.put("ec2_request_latency_system_failure", new double[] { 22.864, 99.248 });
    range.put("machine_temperature_system_failure", new double[] { 2.084721, 108.510544 });
    range.put("nyc_taxi", new double[] { 8.0, 39197.0 });
    range.put("rogue_agent_key_hold", new double[] { 0.0, 0.89501214 });
    range.put("rogue_agent_key_updown ", new double[] { 0.0, 288.20752 });
    range.put("occupancy_6005 ", new double[] { 0.0, 22.28 });
    range.put("occupancy_t4013 ", new double[] { 0.0, 43.06 });
    range.put("speed_6005 ", new double[] { 20.0, 109.0 });
    range.put("speed_7578 ", new double[] { 1.0, 90.0 });
    range.put("speed_t4013 ", new double[] { 11.0, 77.0 });
    range.put("TravelTime_387 ", new double[] { 9.0, 5059.0 });
    range.put("TravelTime_451 ", new double[] { 22.0, 5578.0 });
    range.put("Twitter_volume_AAPL ", new double[] { 0.0, 13479.0 });
    range.put("Twitter_volume_AMZN ", new double[] { 0.0, 1673.0 });
    range.put("Twitter_volume_CRM ", new double[] { 0.0, 209.0 });
    range.put("Twitter_volume_CVS ", new double[] { 0.0, 50.0 });
    range.put("Twitter_volume_FB ", new double[] { 0.0, 1258.0 });
    range.put("Twitter_volume_GOOG ", new double[] { 0.0, 465.0 });
    range.put("Twitter_volume_IBM ", new double[] { 0.0, 139.0 });
    range.put("Twitter_volume_KO ", new double[] { 0.0, 2241.0 });
    range.put("Twitter_volume_PFE", new double[] { 0.0, 36.0 });
    range.put("Twitter_volume_UPS ", new double[] { 0.0, 231.0 });
    staticRange = Collections.unmodifiableMap(range);

  }
}
