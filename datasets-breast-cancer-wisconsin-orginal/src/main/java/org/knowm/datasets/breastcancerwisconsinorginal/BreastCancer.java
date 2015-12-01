package org.knowm.datasets.breastcancerwisconsinorginal;

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
