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
package com.xeiam.datasets.reuters21578.bootstrap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.base.Joiner;
import com.xeiam.datasets.common.utils.FileUtils;
import com.xeiam.datasets.reuters21578.Reuters21578;
import com.xeiam.datasets.reuters21578.Reuters21578DAO;

/**
 * Parses Reuters-21578 text files and put the data in a database
 * 
 * @author timmolter
 */
public class Reuters21578Data2DB {

  int maxBodyLength = 0;

  public static void main(String[] args) {

    Reuters21578DAO.initTest();

    Reuters21578DAO.dropTable();
    Reuters21578DAO.createTable();

    Reuters21578Data2DB dp = new Reuters21578Data2DB();
    dp.go();

    Reuters21578DAO.release();
  }

  private void go() {

    String[] fileNames = FileUtils.getAllFileNames("./raw/", ".*.sgm");
    // System.out.println("files.length: " + fileNames.length);

    int errors = 0;

    // 1. Parse each file
    for (int f = 0; f < fileNames.length; f++) {

      // System.out.println("file " + f + " of " + fileNames.length);
      // System.out.println("fileNames[f]: " + fileNames[f]);

      // 2. Get a list of Reuters21578Story Beans from a file
      List<Reuters21578> stories = getReuters21578StoriesFromFile("./raw/" + fileNames[f]);

      // 3. put the Reuters21578Story Beans in the DB
      for (Reuters21578 reuters21578Story : stories) {

        // stories.toString();
        if (Reuters21578DAO.insert(reuters21578Story) < 0) {
          errors++;
          System.out.println("errors=" + errors);
        }
      }
    }
    System.out.println("maxBodyLength = " + maxBodyLength);
  }

  /**
   * Get a List of the Reuters21578Story Beans
   * 
   * @param fileName
   * @return
   */
  private List<Reuters21578> getReuters21578StoriesFromFile(String fileName) {

    List<Reuters21578> stories = new ArrayList<Reuters21578>();

    // 1. Get String from the file
    String s = FileUtils.readFileToString(fileName);

    // 2. Get a list of stories as Strings, the contecnt between the REUTERS tags
    List<String> storiesAsString = extractElementAsLines(s, "REUTERS");

    for (String storyAsString : storiesAsString) {
      // System.out.println(storyAsString);
      Reuters21578 reuters21578Story = getReuters21578StoryFromText(storyAsString);
      // System.out.println(reuters21578Story.toString());
      stories.add(reuters21578Story);
    }

    return stories;
  }

  /**
   * Get a Reuters21578 object from a story String
   * 
   * @param storyText
   * @return
   */
  private Reuters21578 getReuters21578StoryFromText(String storyText) {

    // System.out.println(storyText);

    // ATTRIBUTES /////////////////////////
    String firstLine = storyText.split(System.getProperty("line.separator"))[0];
    // System.out.println(firstLine);
    String newIdString = extractAttribute(firstLine, "NEWID");
    int newId = Integer.parseInt(newIdString);
    String oldIdString = extractAttribute(firstLine, "OLDID");
    int oldId = Integer.parseInt(oldIdString);
    String topicsString = extractAttribute(firstLine, "TOPICS");
    boolean topicsBool = topicsString.equalsIgnoreCase("YES");
    String lewissplitString = extractAttribute(firstLine, "LEWISSPLIT");
    String cgisplitString = extractAttribute(firstLine, "CGISPLIT");

    // DATE /////////////////////////
    String dateString = extractTextBetweenTags(storyText, "DATE");
    // System.out.println("date=" + dateString);
    Date date = null;
    try {
      DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SS");
      date = sdf.parse(dateString);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    // TOPICS /////////////////////////
    String topicsText = extractTextBetweenTags(storyText, "TOPICS");
    List<String> topicsArray = extractElementAsLines(topicsText, "D");
    String topicsCSV = Joiner.on(", ").join(topicsArray);
    // PLACES /////////////////////////
    String placesText = extractTextBetweenTags(storyText, "PLACES");
    List<String> placesArray = extractElementAsLines(placesText, "D");
    String placesCSV = Joiner.on(", ").join(placesArray);
    // PEOPLE /////////////////////////
    String peopleText = extractTextBetweenTags(storyText, "PEOPLE");
    List<String> peopleArray = extractElementAsLines(peopleText, "D");
    String peopleCSV = Joiner.on(", ").join(peopleArray);
    // ORGS /////////////////////////
    String orgsText = extractTextBetweenTags(storyText, "ORGS");
    List<String> orgsArray = extractElementAsLines(orgsText, "D");
    String orgsCSV = Joiner.on(", ").join(orgsArray);
    // EXCHANGES /////////////////////////
    String exchangesText = extractTextBetweenTags(storyText, "EXCHANGES");
    List<String> exchangesArray = extractElementAsLines(exchangesText, "D");
    String exchangesCSV = Joiner.on(", ").join(exchangesArray);
    // COMPANIES /////////////////////////
    String companiesText = extractTextBetweenTags(storyText, "COMPANIES");
    List<String> companiesArray = extractElementAsLines(companiesText, "D");
    String companiesCSV = Joiner.on(", ").join(companiesArray);

    // TITLE /////////////////////////
    String titleText = extractTextBetweenTags(storyText, "TITLE");
    // DATELINE /////////////////////////
    String datelineText = extractTextBetweenTags(storyText, "DATELINE");
    // BODY /////////////////////////
    String body = extractTextBetweenTags(storyText, "BODY").replaceAll("\\s+", " ");
    // System.out.println(body);
    if (body.length() > maxBodyLength) {
      maxBodyLength = body.length();
    }

    Reuters21578 reuters21578 = new Reuters21578();
    reuters21578.setNewid(newId);
    reuters21578.setOldid(oldId);
    reuters21578.setTopicsbool(topicsBool);
    reuters21578.setLewissplit(lewissplitString);
    reuters21578.setCgisplit(cgisplitString);
    reuters21578.setDate(date);
    reuters21578.setTopics(topicsCSV);
    reuters21578.setPlaces(placesCSV);
    reuters21578.setPeople(peopleCSV);
    reuters21578.setOrgs(orgsCSV);
    reuters21578.setExchanges(exchangesCSV);
    reuters21578.setCompanies(companiesCSV);
    reuters21578.setTitle(titleText);
    reuters21578.setDateline(datelineText);
    reuters21578.setBody(body);

    return reuters21578;

  }

  protected List<String> extractElementAsLines(String stringContainingText, String tagname) {

    String openTag = "<" + tagname.toUpperCase();
    String closeTag = "</" + tagname.toUpperCase();
    List<String> cuts = new ArrayList<String>();
    StringBuilder buf = new StringBuilder();
    boolean record = false;
    for (int i = 0; i < stringContainingText.length() - openTag.length(); i++) {

      if (stringContainingText.substring(i, i + closeTag.length()).equalsIgnoreCase(closeTag)) {
        cuts.add(buf.toString());
        record = false;
      }

      if (record) {
        buf.append(stringContainingText.charAt(i));
      }
      if (stringContainingText.substring(i, i + openTag.length()).equalsIgnoreCase(openTag)) {
        buf = new StringBuilder();
        i += openTag.length();
        record = true;
      }

    }
    // System.out.println(cuts.toString());
    return cuts;

  }

  /**
   * Given some stringContainingText, extracts the String between the tags
   * 
   * @param stringContainingText
   * @param tagname
   * @return
   */
  protected String extractTextBetweenTags(String stringContainingText, String tagname) {

    String openTag = "<" + tagname.toUpperCase() + ">";
    String closeTag = "</" + tagname.toUpperCase() + ">";
    StringBuilder buf = new StringBuilder();
    boolean record = false;
    for (int i = 0; i < stringContainingText.length() - openTag.length(); i++) {
      if (stringContainingText.substring(i, i + closeTag.length()).equalsIgnoreCase(closeTag)) {
        record = false;
        break;
      }

      if (record) {
        buf.append(stringContainingText.charAt(i));
      }
      if (stringContainingText.substring(i, i + openTag.length()).equalsIgnoreCase(openTag)) {
        buf = new StringBuilder();
        i += openTag.length() - 1;
        record = true;
      }

    }

    return buf.toString();
  }

  /**
   * Given a String containing XML-like attributes and a key, it extracts a value
   * 
   * @param stringContainingAttributes
   * @param attributeName
   * @return
   */
  protected String extractAttribute(String stringContainingAttributes, String attributeName) {

    String attributeValue = "";

    stringContainingAttributes = stringContainingAttributes.replaceAll("<", "").replaceAll(">", "");

    String[] keyValues = stringContainingAttributes.split(" ");
    for (int i = 0; i < keyValues.length; i++) {
      String keyValue = keyValues[i].trim();
      String[] keyAndValue = keyValue.split("=");
      if (keyAndValue[0].equalsIgnoreCase(attributeName)) {
        return keyAndValue[1].substring(1, keyAndValue[1].length() - 1);
      }
    }

    return attributeValue;
  }

}
