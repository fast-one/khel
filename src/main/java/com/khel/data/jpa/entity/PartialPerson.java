package com.khel.data.jpa.entity;

import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "person")
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class PartialPerson extends PersistentEntity
{
  private String firstName;

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
}
