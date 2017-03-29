package com.khel.data.jpa.entity;

import org.hibernate.envers.Audited;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@Table(name = "person")
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Person extends PersistentEntity
{
  private String firstName;
  private String lastName;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "person_address",
          joinColumns = @JoinColumn(name = "person_id"),
          inverseJoinColumns = @JoinColumn(name = "address_id")
  )
  private List<GeoLocation> addresses;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private List<PhoneNumber> phoneNumbers;

  public Person()
  {
    this.addresses = new ArrayList<GeoLocation>();
    this.phoneNumbers = new ArrayList<PhoneNumber>();
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public List<GeoLocation> getAddresses()
  {
    return addresses;
  }

  public void addAddress(GeoLocation address)
  {
    this.addresses.add(address);
  }

  public List<PhoneNumber> getPhoneNumbers()
  {
    return phoneNumbers;
  }

  public void addPhoneNumber(PhoneNumber phoneNumber)
  {
    this.phoneNumbers.add(phoneNumber);
  }
}
