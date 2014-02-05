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
package com.xeiam.datasets.reuters21578;

import java.util.Date;

/**
 * This is a POJO representing on Reuters21578 story
 * 
 * @author timmolter
 */
public class Reuters21578 {

  private int newid;
  private int oldid;
  private boolean topicsbool;
  private String lewissplit;
  private String cgisplit;
  private Date date;
  private String topics;
  private String places;
  private String people;
  private String orgs;
  private String exchanges;
  private String companies;
  private String title;
  private String dateline;
  private String body;

  /**
   * @return the newid
   */
  public int getNewid() {

    return newid;
  }

  /**
   * @param newid the newid to set
   */
  public void setNewid(int newid) {

    this.newid = newid;
  }

  /**
   * @return the oldid
   */
  public int getOldid() {

    return oldid;
  }

  /**
   * @param oldid the oldid to set
   */
  public void setOldid(int oldid) {

    this.oldid = oldid;
  }

  /**
   * @return the topicsbool
   */
  public boolean isTopicsbool() {

    return topicsbool;
  }

  /**
   * @param topicsbool the topicsbool to set
   */
  public void setTopicsbool(boolean topicsbool) {

    this.topicsbool = topicsbool;
  }

  /**
   * @return the lewissplit
   */
  public String getLewissplit() {

    return lewissplit;
  }

  /**
   * @param lewissplit the lewissplit to set
   */
  public void setLewissplit(String lewissplit) {

    this.lewissplit = lewissplit;
  }

  /**
   * @return the cgisplit
   */
  public String getCgisplit() {

    return cgisplit;
  }

  /**
   * @param cgisplit the cgisplit to set
   */
  public void setCgisplit(String cgisplit) {

    this.cgisplit = cgisplit;
  }

  /**
   * @return the date
   */
  public Date getDate() {

    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {

    this.date = date;
  }

  /**
   * @return the topics
   */
  public String getTopics() {

    return topics;
  }

  /**
   * @param topics the topics to set
   */
  public void setTopics(String topics) {

    this.topics = topics;
  }

  /**
   * @return the places
   */
  public String getPlaces() {

    return places;
  }

  /**
   * @param places the places to set
   */
  public void setPlaces(String places) {

    this.places = places;
  }

  /**
   * @return the people
   */
  public String getPeople() {

    return people;
  }

  /**
   * @param people the people to set
   */
  public void setPeople(String people) {

    this.people = people;
  }

  /**
   * @return the orgs
   */
  public String getOrgs() {

    return orgs;
  }

  /**
   * @param orgs the orgs to set
   */
  public void setOrgs(String orgs) {

    this.orgs = orgs;
  }

  /**
   * @return the exchanges
   */
  public String getExchanges() {

    return exchanges;
  }

  /**
   * @param exchanges the exchanges to set
   */
  public void setExchanges(String exchanges) {

    this.exchanges = exchanges;
  }

  /**
   * @return the companies
   */
  public String getCompanies() {

    return companies;
  }

  /**
   * @param companies the companies to set
   */
  public void setCompanies(String companies) {

    this.companies = companies;
  }

  /**
   * @return the title
   */
  public String getTitle() {

    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {

    this.title = title;
  }

  /**
   * @return the dateline
   */
  public String getDateline() {

    return dateline;
  }

  /**
   * @param dateline the dateline to set
   */
  public void setDateline(String dateline) {

    this.dateline = dateline;
  }

  /**
   * @return the body
   */
  public String getBody() {

    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(String body) {

    this.body = body;
  }

  @Override
  public String toString() {

    return "Reuters21578 [newid=" + newid + ", oldid=" + oldid + ", date=" + date + ", topics=" + topics + ", places=" + places + ", people=" + people + ", orgs=" + orgs + ", exchanges=" + exchanges
        + ", companies=" + companies + ", title=" + title + ", dateline=" + dateline + ", body=" + body + "]";
  }

}
