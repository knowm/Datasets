package org.knowm.datasets.lshtc4;

/**
 * @author timmolter
 */
public class LSHTC4Hierarchy {

  int parentid;
  int nodeid;

  public int getParentid() {

    return parentid;
  }

  public void setParentid(int parentid) {

    this.parentid = parentid;
  }

  public int getNodeid() {

    return nodeid;
  }

  public void setNodeid(int nodeid) {

    this.nodeid = nodeid;
  }

  @Override
  public String toString() {

    return "LSHTC4Hierarchy [parentid=" + parentid + ", nodeid=" + nodeid + "]";
  }

}
