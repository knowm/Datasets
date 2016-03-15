/**
 * (The MIT License)
 *
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
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
package org.knowm.datasets.common.business;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author timmolter
 */
public abstract class DatasetsDAO {

  private final static Logger logger = LoggerFactory.getLogger(DatasetsDAO.class);

  private final static String GoogleDriveURLPart1 = "https://docs.google.com/uc?export=download&id=";

  public static void init(String[] args) {

    // allow for setting other SQL data sources
    if (args.length > 0 && args[0] != null) {
      Yank.setupDataSource(PropertiesUtils.getPropertiesFromClasspath(args[0]));
    } else {
      Yank.setupDataSource(PropertiesUtils.getPropertiesFromClasspath("DB_HSQLDB_FILE.properties"));
    }

    // allow for setting other SQL statements
    if (args.length > 1 && args[1] != null) {
      Yank.addSQLStatements(PropertiesUtils.getPropertiesFromClasspath(args[1]));
    } else {
      Yank.addSQLStatements(PropertiesUtils.getPropertiesFromClasspath("SQL_HSQLDB.properties"));
    }

  }

  public static void release() {

    Yank.releaseDataSource();
  }

  public static void init(String dbName, String dataFilesDir, String dataFileURL, String propsFileURL, String scriptFileURL, String lobsFileURL,
      boolean requiresManualDownload) {

    // 1. create data dir

    // 1b. Clean up any stragglers
    FileUtils.deleteQuietly(new File(dataFilesDir + "/" + dbName + ".tmp"));
    FileUtils.deleteQuietly(new File(dataFilesDir + "/" + dbName + ".lck"));
    FileUtils.deleteQuietly(new File(dataFilesDir + "/" + dbName + ".log"));

    // 2. check if files are already available
    File dataFile = new File(dataFilesDir + "/" + dbName + ".data");
    File propsFile = new File(dataFilesDir + "/" + dbName + ".properties");
    File scriptFile = new File(dataFilesDir + "/" + dbName + ".script");
    File lobsFile = new File(dataFilesDir + "/" + dbName + ".lobs");

    // if the files don't yet exist, then download them
    if (!dataFile.exists() || !propsFile.exists() || !scriptFile.exists()) {

      logger.info("Saving DB files in: " + dataFilesDir);
      URL url;
      try {

        // props
        logger.info("Downloading props file...");
        url = new URL(GoogleDriveURLPart1 + propsFileURL);
        org.apache.commons.io.FileUtils.copyURLToFile(url, propsFile, 5000, 10000);
      } catch (Exception e) {
        throw new RuntimeException(
            "Something went wrong while downloading the database files. Perhaps try again and if all else fails, download the files manually (https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing) and place in the directory you specified.",
            e);
      }
      try {
        // script
        logger.info("Downloading script file...");
        url = new URL(GoogleDriveURLPart1 + scriptFileURL);
        org.apache.commons.io.FileUtils.copyURLToFile(url, scriptFile, 5000, 10000);
      } catch (Exception e) {
        throw new RuntimeException(
            "Something went wrong while downloading the database files. Perhaps try again and if all else fails, download the files manually (https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing) and place in the directory you specified.",
            e);
      }
      // data
      if (!requiresManualDownload) {
        try {
          logger.info("Downloading data file... (this could take a while so be patient!)");
          url = new URL(GoogleDriveURLPart1 + dataFileURL);
          org.apache.commons.io.FileUtils.copyURLToFile(url, dataFile, 5000, 10000);
        } catch (Exception e) {
          throw new RuntimeException(
              "Something went wrong while downloading the database files. Perhaps try again and if all else fails, download the files manually (https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing) and place in the directory you specified.",
              e);
        }
      } else {
        throw new RuntimeException("The data file is too big to download automatically! Please manually download it from: "
            + (GoogleDriveURLPart1 + dataFileURL) + " and place it in: " + dataFilesDir);
      }

    } else {
      logger.info("Database files already exist in local directory. Skipping download.");
    }
    if (lobsFileURL != null && !lobsFile.exists()) {
      throw new RuntimeException("The data file is too big to download automatically! Please manually download it from: "
          + (GoogleDriveURLPart1 + lobsFileURL) + " and place it in: " + dataFilesDir);
    }

    // 3. setup HSQLDB
    Properties dbProps = new Properties();
    dbProps.setProperty("jdbcUrl", "jdbc:hsqldb:file:" + dataFilesDir + File.separatorChar + dbName + ";shutdown=true;readonly=true");
    dbProps.setProperty("username", "sa");
    dbProps.setProperty("password", "");
    dbProps.setProperty("readOnly", "true");

    Yank.setupDataSource(dbProps);

  }

}
