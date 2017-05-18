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
package org.knowm.datasets.reuters21578;

import java.util.Date;

import org.knowm.datasets.common.business.Bean;

/**
 * This is a POJO representing on Reuters21578 story
 *
 * @author timmolter
 */
public class Reuters21578 extends Bean {

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

  public int getId() {

    return newid;
  }

  public void setId(int newid) {

    this.newid = newid;
  }

  public int getNewid() {
    return newid;
  }

  public void setNewid(int newid) {
    this.newid = newid;
  }

  public int getOldid() {
    return oldid;
  }

  public void setOldid(int oldid) {
    this.oldid = oldid;
  }

  public boolean isTopicsbool() {
    return topicsbool;
  }

  public void setTopicsbool(boolean topicsbool) {
    this.topicsbool = topicsbool;
  }

  public String getLewissplit() {
    return lewissplit;
  }

  public void setLewissplit(String lewissplit) {
    this.lewissplit = lewissplit;
  }

  public String getCgisplit() {
    return cgisplit;
  }

  public void setCgisplit(String cgisplit) {
    this.cgisplit = cgisplit;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getTopics() {
    return topics;
  }

  public void setTopics(String topics) {
    this.topics = topics;
  }

  public String getPlaces() {
    return places;
  }

  public void setPlaces(String places) {
    this.places = places;
  }

  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  public String getOrgs() {
    return orgs;
  }

  public void setOrgs(String orgs) {
    this.orgs = orgs;
  }

  public String getExchanges() {
    return exchanges;
  }

  public void setExchanges(String exchanges) {
    this.exchanges = exchanges;
  }

  public String getCompanies() {
    return companies;
  }

  public void setCompanies(String companies) {
    this.companies = companies;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDateline() {
    return dateline;
  }

  public void setDateline(String dateline) {
    this.dateline = dateline;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {

    return "Reuters21578 [newid=" + newid + ", oldid=" + oldid + ", date=" + date + ", topics=" + topics + ", places=" + places + ", people=" + people
        + ", orgs=" + orgs + ", exchanges=" + exchanges + ", companies=" + companies + ", title=" + title + ", dateline=" + dateline + "]";
  }

}
