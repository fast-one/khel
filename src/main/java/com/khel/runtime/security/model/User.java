package com.khel.runtime.security.model;

import com.khel.data.jpa.entity.PersistentEntity;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

/**
 * Created by RKasturi on 3/15/2017.
 */
@Entity
@Audited
@Table(name = "app_user")
public class User extends PersistentEntity
{

  String firstName;
  String lastName;
  String email;
  String mobile;
  String userName;
  String password;


  @Size(min=1)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  public User()
  {

  }

  public User(Long userId, String firstName, String lastName, String email, String mobile, String userName, String password, Set<Role> roles)
  {
    super.setId(userId);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.mobile = mobile;
    this.userName = userName;
    this.password = password;
    this.roles = roles;
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

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getMobile()
  {
    return mobile;
  }

  public void setMobile(String mobile)
  {
    this.mobile = mobile;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Set<Role> getRoles()
  {
    return roles != null ? roles : Collections.emptySet();
  }

  public void setRoles(Set<Role> roles)
  {
    this.roles = roles;
  }
}
