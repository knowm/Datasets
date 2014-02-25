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
package com.xeiam.datasets.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author timmolter
 */
public final class FileUtils {

  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

  /**
   * Constructor - Private constructor to prevent instantiation
   */
  private FileUtils() {

  }

  /**
   * Given a path to a File, return the content of the file as a String
   * 
   * @param file
   * @return
   */
  public static String readFileToString(String filePath) {

    String result = null;
    File file = new File(filePath);
    if (!file.exists()) {
      logger.error("SOURCE FILE (" + filePath + ") NOT FOUND!!!");
      return null;
    }
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      result = readerToString(reader);
    } catch (FileNotFoundException e) {
      logger.error("ERROR IN READFILETOSTRING!!!", e);
    }

    // show file contents here
    return result;
  }

  /**
   * Given a File from the classpath, return the content of the file as a String
   * 
   * @param fileName
   * @return
   */
  public static String readFileFromClasspathToString(String fileName) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(fileName)));
    String result = readerToString(reader);

    // show file contents here
    return result;
  }

  private static String readerToString(BufferedReader reader) {

    StringBuffer sb = new StringBuffer();

    try {
      String text = null;

      // repeat until all lines are read
      while ((text = reader.readLine()) != null) {
        sb.append(text).append(System.getProperty("line.separator"));
      }
    } catch (FileNotFoundException e) {
      logger.error("ERROR IN READFILETOSTRING!!!", e);
    } catch (IOException e) {
      logger.error("ERROR IN READFILETOSTRING!!!", e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        logger.error("ERROR IN READFILETOSTRING!!!", e);
      }
    }

    // show file contents here
    return sb.toString();

  }

  /**
   * This method returns the names of all the files found in the given directory
   * 
   * @param dirName - ex. "./images/colors/original/" *make sure you have the '/' on the end
   * @return String[] - an array of file names
   */
  public static String[] getAllFileNames(String dirName) {

    File dir = new File(dirName);

    File[] files = dir.listFiles(); // returns files and folders

    if (files != null) {
      List<String> fileNames = new ArrayList<String>();

      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          fileNames.add(files[i].getName());
        }
      }

      return fileNames.toArray(new String[fileNames.size()]);
    }
    else {
      logger.debug(dirName + " does not denote a valid directory!");
      return new String[0];
    }
  }

  /**
   * This method returns the Files found in the given directory
   * 
   * @param dirName - ex. "./images/colors/original/" *make sure you have the '/' on the end
   * @return File[] - an array of files
   */
  public static File[] getAllFiles(String dirName) {

    File dir = new File(dirName);

    File[] files = dir.listFiles(); // returns files and folders

    if (files != null) {
      List<File> filteredFiles = new ArrayList<File>();
      for (int i = 0; i < files.length; i++) {

        if (files[i].isFile()) {
          filteredFiles.add(files[i]);
        }
      }
      return filteredFiles.toArray(new File[filteredFiles.size()]);
    }
    else {
      logger.debug(dirName + " does not denote a valid directory!");
      return new File[0];
    }
  }

  /**
   * This method returns the names of all the files found in the given directory matching the given regular expression.
   * 
   * @param dirName - ex. "./images/colors/original/" *make sure you have the '/' on the end
   * @param regex - ex. ".*.png"
   * @return String[] - an array of file names
   */
  public static String[] getAllFileNames(String dirName, String regex) {

    String[] allFileNames = getAllFileNames(dirName);

    List<String> matchingFileNames = new ArrayList<String>();

    for (int i = 0; i < allFileNames.length; i++) {

      if (allFileNames[i].matches(regex)) {
        matchingFileNames.add(allFileNames[i]);
      }
    }

    return matchingFileNames.toArray(new String[matchingFileNames.size()]);

  }

  /**
   * This method returns the files found in the given directory matching the given regular expression.
   * 
   * @param dirName - ex. "./images/colors/original/" *make sure you have the '/' on the end
   * @param regex - ex. ".*.png"
   * @return File[] - an array of files
   */
  public static File[] getAllFiles(String dirName, String regex) {

    File[] allFiles = getAllFiles(dirName);

    List<File> matchingFiles = new ArrayList<File>();

    for (int i = 0; i < allFiles.length; i++) {

      if (allFiles[i].getName().matches(regex)) {
        matchingFiles.add(allFiles[i]);
      }
    }

    return matchingFiles.toArray(new File[matchingFiles.size()]);

  }

  /**
   * Copies src file to dst file. If the dst file does not exist, it is created.
   * 
   * @param srcPath
   * @param destPath
   * @throws IOException
   */
  public static boolean copy(String srcPath, String destPath) {

    boolean success = true;

    InputStream in = null;
    OutputStream out = null;

    try {

      File srcFile = new File(srcPath);

      if (!srcFile.exists()) {
        logger.error("SOURCE FILE NOT FOUND!!!");
        return false;
      }

      mkDirIfNotExists(destPath.substring(0, destPath.lastIndexOf(File.separatorChar)));

      File destFile = new File(destPath);

      in = new FileInputStream(srcFile);

      out = new FileOutputStream(destFile); // Transfer bytes from in to out

      byte[] buf = new byte[1024];

      int len;

      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }

    } catch (Exception e) {

      logger.error("ERROR COPYING FILE!!!", e);
      success = false;

    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          // eat it
        }
      }
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          // eat it
        }
      }
    }

    return success;

  }

  /**
   * @param fullyQualifiedFileName
   * @return
   */
  public static boolean deleteFile(String fullyQualifiedFileName) {

    File file = new File(fullyQualifiedFileName);

    boolean deleteSuccessful = false;
    if (file.exists() && file.canWrite() && !file.isDirectory()) {

      try {

        deleteSuccessful = file.delete();

      } catch (Exception e) {
        logger.error("ERROR DELETING FILE!!!", e);
      }
    }
    return deleteSuccessful;
  }

  /**
   * Checks if a file exists
   * 
   * @param pFilePath
   * @return
   */
  public static boolean fileExists(String pFilePath) {

    File file = new File(pFilePath);
    return file.exists();
  }

  /**
   * Makes a dir, if it doesn't already exist
   * 
   * @param filePath
   */
  public static File mkDirIfNotExists(String filePath) {

    File f = new File(filePath);
    if (!f.exists()) {
      f.mkdir();
    }
    return f;
  }

  /**
   * Given a File from the classpath, copy it to a directory
   * 
   * @param fileName
   * @return
   */
  public static boolean copyFileFromClasspathToFile(String destPath, String fileName) {

    boolean success = true;

    InputStream in = null;

    try {

      mkDirIfNotExists(destPath);
      File destFile = new File(destPath + File.separatorChar + fileName);

      in = FileUtils.class.getClassLoader().getResourceAsStream(fileName);

      org.apache.commons.io.FileUtils.copyInputStreamToFile(in, destFile);

    } catch (Exception e) {

      logger.error("ERROR COPYING FILE!!!", e);
      success = false;

    }
    return success;
  }

  private static final int TEMP_DIR_ATTEMPTS = 10000;

  public static File createTempDir() {

    // File baseDir = new File(System.getProperty("java.io.tmpdir"));
    File baseDir = org.apache.commons.io.FileUtils.getTempDirectory();
    String baseName = System.currentTimeMillis() + "-";

    for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
      File tempDir = new File(baseDir, baseName + counter);
      if (tempDir.mkdir()) {
        return tempDir;
      }
    }
    throw new IllegalStateException("Failed to create directory within " + TEMP_DIR_ATTEMPTS + " attempts (tried " + baseName + "0 to " + baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
  }

  public static boolean deleteDirectoryRecursively(File file) {

    boolean success = true;
    try {
      org.apache.commons.io.FileUtils.deleteDirectory(file);
    } catch (IOException e) {
      success = false;
      logger.error("ERROR DELETING DIRECTORY RECURESIVELY!!!", e);
    }
    return success;
  }

}