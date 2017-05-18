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

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;

public class RawData2DB {

  public static int idCount = 0;

  public static void main(String[] args) throws IOException, ParseException {

    NumentaDAO.init(args);

    NumentaDAO.dropTable();
    NumentaDAO.createTable();

    go();
    NumentaDAO.release();
  }

  public static void go() throws JsonParseException, JsonMappingException, IOException, ParseException {

    System.out.println("Building Anomalie Windows from label files...");
    Map<String, ArrayList<ArrayList<Long>>> windowMap = buildWindowMap("./raw/labels/combined_windows.json");

    System.out.println("Building DB tables from data files ....");
    Collection<File> files = FileUtils.listFiles(new File("./raw/data/"), FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY);
    for (File file : files) {
      if (file.getName().toLowerCase().endsWith(".csv")) {

        String path = file.getAbsolutePath();
        String seriesName = file.getName();
        seriesName = seriesName.substring(0, seriesName.length() - 4); // remove CSV
        String seriesGroup = file.getParentFile().getName();
        buildTable(path, seriesGroup, seriesName, windowMap.get(seriesName));
      }
    }

  }

  private static void buildTable(String file, String seriesGroup, String seriesName, ArrayList<ArrayList<Long>> windows) throws IOException, ParseException {

    String data = FileUtils.readFileToString(new File(file), "UTF-8");
    System.out.print("Loading " + seriesName + " data from " + file + "....");
    String[] lines = data.split("\\r?\\n");

    int tupleCount = 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (int i = 0; i < lines.length; i++) {
      // for (int i = 0; i < 1; i++) {

      try {

        String[] line = lines[i].split(",");
        Date dte = dateFormatter.parse(line[0]);

        // System.out.println(line[0] + " -> " + dateFormatter.format(dte) + " " + dte.getTime());
        long timestamp = dte.getTime();
        double value = Double.parseDouble(line[1]);

        boolean anomaly = false;
        for (ArrayList<Long> w : windows) {
          if (timestamp >= w.get(0) && timestamp <= w.get(1)) {
            anomaly = true;
            break;
          }
        }

        SeriesPoint point = new SeriesPoint();
        point.setId(idCount);
        point.setSeriesGroup(seriesGroup);
        point.setSeriesName(seriesName);
        point.setTimestamp(timestamp);
        point.setValue(value);
        point.setLabel(anomaly ? 1 : 0);

        // System.out.println(line[0] + " -> " + dte.toString() + " -> " + point.getTimestampAsDate().toString());

        idCount++;
        NumentaDAO.insertSeriesPoint(point);

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

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
      windowMap.put(key, windows);
      System.out.println(key + " " + windowMap.get(key).toString());
    }

    return windowMap;
  }
}
