package com.benajo.model;

/**
 * Model class used to store the entity and trade price, which is used for ranking the entities by buy price.
 */
public class Entity implements Comparable<Entity> {

  private String entity;
  private double price;

  public Entity(String entity, double price) {
    this.entity = entity;
    this.price = price;
  }

  public String getEntity() {
    return entity;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public int compareTo(Entity o) {
    if (price > o.getPrice())
      return -1;
    else if (price < o.getPrice())
      return 1;
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Entity entity1 = (Entity) o;

    if (Double.compare(entity1.price, price) != 0) return false;
    return entity != null ? entity.equals(entity1.entity) : entity1.entity == null;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = entity != null ? entity.hashCode() : 0;
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
