package org.knowm.datasets.pcb;

/**
 * @author timmolter
 */
public class PCBAnnotation {

  private int id;
  private float x;
  private float y;
  private float width;
  private float height;
  private float rotation;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public float getRotation() {
    return rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "PCBAnnotation [id=" + id + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", rotation=" + rotation + ", name="
        + name + "]";
  }

}
