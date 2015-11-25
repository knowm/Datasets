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
package com.xeiam.datasets.breastcancerwisconsinorginal;

/**
 * @author alexnugent
 */
public class BreastCancer {

  private int id;
  private int sampleCodeNumber;
  private int clumpThickness;
  private int uniformityOfCellSize;
  private int uniformityOfCellShape;
  private int marginalAdhesion;
  private int singleEpithelialCellSize;
  private int bareNuclei;
  private int blandChromatin;
  private int normalNucleoli;
  private int mitoses;
  private int cellClass;

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public int getSampleCodeNumber() {

    return sampleCodeNumber;
  }

  public void setSampleCodeNumber(int sampleCodeNumber) {

    this.sampleCodeNumber = sampleCodeNumber;
  }

  public int getClumpThickness() {

    return clumpThickness;
  }

  public void setClumpThickness(int clumpThickness) {

    this.clumpThickness = clumpThickness;
  }

  public int getUniformityOfCellSize() {

    return uniformityOfCellSize;
  }

  public void setUniformityOfCellSize(int uniformityOfCellSize) {

    this.uniformityOfCellSize = uniformityOfCellSize;
  }

  public int getUniformityOfCellShape() {

    return uniformityOfCellShape;
  }

  public void setUniformityOfCellShape(int uniformityOfCellShape) {

    this.uniformityOfCellShape = uniformityOfCellShape;
  }

  public int getMarginalAdhesion() {

    return marginalAdhesion;
  }

  public void setMarginalAdhesion(int marginalAdhesion) {

    this.marginalAdhesion = marginalAdhesion;
  }

  public int getSingleEpithelialCellSize() {

    return singleEpithelialCellSize;
  }

  public void setSingleEpithelialCellSize(int singleEpithelialCellSize) {

    this.singleEpithelialCellSize = singleEpithelialCellSize;
  }

  public int getBareNuclei() {

    return bareNuclei;
  }

  public void setBareNuclei(int bareNuclei) {

    this.bareNuclei = bareNuclei;
  }

  public int getBlandChromatin() {

    return blandChromatin;
  }

  public void setBlandChromatin(int blandChromatin) {

    this.blandChromatin = blandChromatin;
  }

  public int getNormalNucleoli() {

    return normalNucleoli;
  }

  public void setNormalNucleoli(int normalNucleoli) {

    this.normalNucleoli = normalNucleoli;
  }

  public int getMitoses() {

    return mitoses;
  }

  public void setMitoses(int mitoses) {

    this.mitoses = mitoses;
  }

  public int getCellClass() {

    return cellClass;
  }

  public void setCellClass(int cellClass) {

    this.cellClass = cellClass;
  }

  @Override
  public String toString() {

    return "BreastCancer [id=" + id + ", sampleCodeNumber=" + sampleCodeNumber + ", clumpThickness=" + clumpThickness + ", uniformityOfCellSize="
        + uniformityOfCellSize + ", uniformityOfCellShape=" + uniformityOfCellShape + ", marginalAdhesion=" + marginalAdhesion
        + ", singleEpithelialCellSize=" + singleEpithelialCellSize + ", bareNuclei=" + bareNuclei + ", blandChromatin=" + blandChromatin
        + ", normalNucleoli=" + normalNucleoli + ", mitoses=" + mitoses + ", cellClass=" + cellClass + "]";
  }

}
