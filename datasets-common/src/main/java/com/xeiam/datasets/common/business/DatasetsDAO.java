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
package com.xeiam.datasets.common.business;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xeiam.datasets.common.utils.FileUtils;
import com.xeiam.yank.DBConnectionManager;

/**
 * @author timmolter
 */
public class DatasetsDAO {

  private final static Logger logger = LoggerFactory.getLogger(DatasetsDAO.class);

  public static void release() {

    DBConnectionManager.INSTANCE.release();
  }

  public static void release(File file) {

    DBConnectionManager.INSTANCE.release();
    logger.info("Deleting temporary DB in: " + file.getPath());
    FileUtils.deleteDirectoryRecursively(file);
  }

  public static void testRelease() {

    DBConnectionManager.INSTANCE.release();
  }

  public static File init(String poolName, String dbName) {

    // 1. create temp dir
    File file = FileUtils.createTempDir();
    logger.info("Saving temporary DB in: " + file.getPath());

    // 2. move files from jar to temp dir
    FileUtils.copyFileFromClasspathToFile(file.getPath(), dbName + ".data");
    FileUtils.copyFileFromClasspathToFile(file.getPath(), dbName + ".properties");
    FileUtils.copyFileFromClasspathToFile(file.getPath(), dbName + ".script");

    // 3. setup HSQLDB
    Properties dbProps = new Properties();
    dbProps.setProperty("driverclassname", "org.hsqldb.jdbcDriver");
    dbProps.setProperty(poolName + ".url", "jdbc:hsqldb:file:" + file.getPath() + File.separatorChar + dbName + ";shutdown=true");
    dbProps.setProperty(poolName + ".user", "sa");
    dbProps.setProperty(poolName + ".password", "");
    dbProps.setProperty(poolName + ".maxconn", "10");

    DBConnectionManager.INSTANCE.init(dbProps);

    return file;
  }

  public static File init(String poolName, String dbName, String dataFilesDir) {

    // 1. create temp dir
    File file = FileUtils.mkDirIfNotExists(dataFilesDir);
    logger.info("Saving  DB in: " + file.getPath());

    // 2. move files from jar to given dir
    String dataPath = dbName + ".data";
    String propsPath = dbName + ".properties";
    String scriptPath = dbName + ".script";

    // if the files don't yet exist, then unpack them from the jar
    if (!FileUtils.fileExists(file.getPath() + "/" + dataPath) || !FileUtils.fileExists(file.getPath() + "/" + propsPath) || !FileUtils.fileExists(file.getPath() + "/" + scriptPath)) {
      FileUtils.copyFileFromClasspathToFile(file.getPath(), dataPath);
      FileUtils.copyFileFromClasspathToFile(file.getPath(), propsPath);
      FileUtils.copyFileFromClasspathToFile(file.getPath(), scriptPath);
    }

    // 3. setup HSQLDB
    Properties dbProps = new Properties();
    dbProps.setProperty("driverclassname", "org.hsqldb.jdbcDriver");
    dbProps.setProperty(poolName + ".url", "jdbc:hsqldb:file:" + file.getPath() + File.separatorChar + dbName + ";shutdown=true");
    dbProps.setProperty(poolName + ".user", "sa");
    dbProps.setProperty(poolName + ".password", "");
    dbProps.setProperty(poolName + ".maxconn", "10");

    DBConnectionManager.INSTANCE.init(dbProps);

    return file;
  }
}
