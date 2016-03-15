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
package org.knowm.datasets.censusincome;

import java.io.Serializable;

/**
 * @author alexnugent
 */
public class CensusIncome implements Serializable {

  private int id;
  private int age;
  private String workclass;
  private int fnlwgt;
  private String education;
  private int educationNum;
  private String maritalStatus;
  private String occupation;
  private String relationship;
  private String race;
  private String sex;
  private int capitalGain;
  private int capitalLoss;
  private int hoursPerWeek;
  private String nativeCountry;
  private boolean incomeLessThan50k;

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public int getAge() {

    return age;
  }

  public void setAge(int age) {

    this.age = age;
  }

  public String getWorkclass() {

    return workclass;
  }

  public void setWorkclass(String workclass) {

    this.workclass = workclass;
  }

  public int getFnlwgt() {

    return fnlwgt;
  }

  public void setFnlwgt(int fnlwgt) {

    this.fnlwgt = fnlwgt;
  }

  public String getEducation() {

    return education;
  }

  public void setEducation(String education) {

    this.education = education;
  }

  public int getEducationNum() {

    return educationNum;
  }

  public void setEducationNum(int educationNum) {

    this.educationNum = educationNum;
  }

  public String getMaritalStatus() {

    return maritalStatus;
  }

  public void setMaritalStatus(String maritalStatus) {

    this.maritalStatus = maritalStatus;
  }

  public String getOccupation() {

    return occupation;
  }

  public void setOccupation(String occupation) {

    this.occupation = occupation;
  }

  public String getRelationship() {

    return relationship;
  }

  public void setRelationship(String relationship) {

    this.relationship = relationship;
  }

  public String getRace() {

    return race;
  }

  public void setRace(String race) {

    this.race = race;
  }

  public String getSex() {

    return sex;
  }

  public void setSex(String sex) {

    this.sex = sex;
  }

  public int getCapitalGain() {

    return capitalGain;
  }

  public void setCapitalGain(int capitalGain) {

    this.capitalGain = capitalGain;
  }

  public int getCapitalLoss() {

    return capitalLoss;
  }

  public void setCapitalLoss(int capitalLoss) {

    this.capitalLoss = capitalLoss;
  }

  public int getHoursPerWeek() {

    return hoursPerWeek;
  }

  public void setHoursPerWeek(int hoursPerWeek) {

    this.hoursPerWeek = hoursPerWeek;
  }

  public String getNativeCountry() {

    return nativeCountry;
  }

  public void setNativeCountry(String nativeCountry) {

    this.nativeCountry = nativeCountry;
  }

  public boolean isIncomeLessThan50k() {

    return incomeLessThan50k;
  }

  public void setIncomeLessThan50k(boolean incomeLessThan50k) {

    this.incomeLessThan50k = incomeLessThan50k;
  }

  @Override
  public String toString() {

    return "CensusIncome [id=" + id + ", age=" + age + ", workclass=" + workclass + ", fnlwgt=" + fnlwgt + ", education=" + education
        + ", educationNum=" + educationNum + ", maritalStatus=" + maritalStatus + ", occupation=" + occupation + ", relationship=" + relationship
        + ", race=" + race + ", sex=" + sex + ", capitalGain=" + capitalGain + ", capitalLoss=" + capitalLoss + ", hoursPerWeek=" + hoursPerWeek
        + ", nativeCountry=" + nativeCountry + ", incomeLessThan50k=" + incomeLessThan50k + "]";
  }

}
