package com.khel.data.jpa.entity;

import com.khel.data.jpa.type.PhoneNumberType;
import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Audited
@Table(name = "person_phone_number")
@AttributeOverride(name = "id", column = @Column(name = "person_phone_number_id"))
public class PhoneNumber extends PersistentEntity
{
  @Column(name = "phone_number")
  private String number;
  @Column(name = "number_type")
  private PhoneNumberType type;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  public String getNumber()
  {
    return number;
  }

  public void setNumber(String number)
  {
    this.number = number;
  }

  public PhoneNumberType getType()
  {
    return type;
  }

  public void setType(PhoneNumberType type)
  {
    this.type = type;
  }

  public Person getPerson()
  {
    return person;
  }

  public void setPerson(Person person)
  {
    this.person = person;
  }
}