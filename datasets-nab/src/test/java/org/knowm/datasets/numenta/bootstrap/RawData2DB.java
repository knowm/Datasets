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
package org.knowm.datasets.numenta.bootstrap;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.knowm.datasets.numenta.NumentaDAO;
import org.knowm.datasets.numenta.SeriesPoint;

import com.xeiam.yank.Yank;

/**
 * Parses Numenta csv files and put the data in a database
 *
 * @author Jacob Robert Steeves
 */
public class RawData2DB {

  /**
   * This method builds tables for each anomaly timeseries from the NAB benchmark dataset
   */
  public static void main(String[] args) throws IOException, ParseException {

    Properties dbProps = new Properties();
    if (args.length < 3) {
      dbProps.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/DB_CLASSIFIER_APPS");
      dbProps.setProperty("username", "root");
      dbProps.setProperty("password", "");
    }
    else {
      dbProps.setProperty("jdbcUrl", args[0]);
      dbProps.setProperty("username", args[1]);
      dbProps.setProperty("password", args[2]);
    }
    Yank.setupDataSource(dbProps);

    System.out.println("Building Anomalie Windows from label files...");
    Map<String, ArrayList<ArrayList<Long>>> windowMap = buildWindowMap("./raw/labels/combined_windows.json");

    System.out.println("Building DB tables from data files ....");
    Collection<File> files = FileUtils.listFiles(new File("./raw/data/"), FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY);
    for (File file : files) {
      if (file.getName().toLowerCase().endsWith(".csv")) {

        String path = file.getAbsolutePath();
        String name = file.getName();
        name = name.substring(0, name.length() - 4);
        name = name.replaceAll("-", "_");
        System.out.println(name);
        buildTable(path, name, windowMap.get(name));
      }
    }

  }

  private static void buildTable(String file, String tableName, ArrayList<ArrayList<Long>> windows) throws IOException, ParseException {

    NumentaDAO.dropTable(tableName);
    NumentaDAO.createTable(tableName);
    String data = FileUtils.readFileToString(new File(file), "UTF-8");
    System.out.print("Creating " + tableName + " from " + file + "....");
    String[] lines = data.split("\\r?\\n");

    int tupleCount = 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    for (int i = 0; i < lines.length; i++) {
      try {
        String[] line = lines[i].split(",");
        long timestamp = dateFormatter.parse(line[0]).getTime();
        double value = Double.parseDouble(line[1]);

        boolean anomaly = false;
        for (ArrayList<Long> w : windows) {
          if (timestamp >= w.get(0) && timestamp <= w.get(1)) {
            anomaly = true;
            break;
          }
        }

        SeriesPoint point = new SeriesPoint(timestamp, value, anomaly ? 1 : 0);
        NumentaDAO.insertSeriesPoint(tableName, point);

      } catch (Exception e) {
        // eat it. Will throw exception on the first line of the test dataset.
        // System.out.println("error parsing one line.");
      }
      tupleCount++;

    }
    System.out.println(" Done. Loaded " + tupleCount + " tuples");

  }

  private static Map<String, ArrayList<ArrayList<Long>>> buildWindowMap(String file) throws JsonParseException, JsonMappingException, IOException, ParseException {

    Map<String, ArrayList<ArrayList<Long>>> windowMap = new HashMap<String, ArrayList<ArrayList<Long>>>();

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    ObjectMapper mapper = new ObjectMapper();
    Map<String, ArrayList<ArrayList<String>>> jWindows = mapper.readValue(new File(file), Map.class);

    for (String key : jWindows.keySet()) {

      ArrayList<ArrayList<Long>> windows = new ArrayList<ArrayList<Long>>();
      for (ArrayList<String> jwindow : jWindows.get(key)) {
        ArrayList<Long> window = new ArrayList<Long>();
        window.add(dateFormatter.parse(jwindow.get(0)).getTime());
        window.add(dateFormatter.parse(jwindow.get(1)).getTime());
        windows.add(window);
      }

      key = key.substring(0, key.length() - 4);
      key = key.replaceFirst(".*/", "");
      key = key.replaceAll("-", "_");
      windowMap.put(key, windows);
      System.out.println(key + " " + windowMap.get(key).toString());
    }

    return windowMap;
  }
}
