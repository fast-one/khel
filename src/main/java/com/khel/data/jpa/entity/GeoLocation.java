package com.khel.data.jpa.entity;

import org.geolatte.geom.Point;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "GEO_LOCATION")
public class GeoLocation extends PersistentEntity
{
  private String name;
  private Point location;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Point getLocation()
  {
    return location;
  }

  public void setLocation(Point location)
  {
    this.location = location;
  }
}
