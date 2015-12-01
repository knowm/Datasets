package org.knowm.datasets.ucsdanomaly;

import java.sql.Blob;

/**
 * @author timmolter
 */
public class UCSDAnomaly {

  private int id;
  private int tifid;
  private Blob tifbytes;
  private boolean isanomaly;

  public int getId() {

    return id;
  }

  public void setId(int id) {

    this.id = id;
  }

  public int getTifid() {

    return tifid;
  }

  public void setTifid(int tifid) {

    this.tifid = tifid;
  }

  public Blob getTifbytes() {

    return tifbytes;
  }

  public void setTifbytes(Blob tifbytes) {

    this.tifbytes = tifbytes;
  }

  public boolean isIsanomaly() {

    return isanomaly;
  }

  public void setIsanomaly(boolean isanomaly) {

    this.isanomaly = isanomaly;
  }

  @Override
  public String toString() {

    return "UCSDAnomaly [id=" + id + ", tifid=" + tifid + ", isanomaly=" + isanomaly + "]";
  }

}
