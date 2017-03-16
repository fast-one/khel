package com.khel.runtime.security.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.khel.data.jpa.entity.PersistentEntity;
import com.khel.runtime.security.type.Permission;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Audited
@Table(name = "role")
public class Role extends PersistentEntity
{
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;
  @Size(min = 1)
  @ElementCollection(targetClass = Permission.class)
  @JoinTable(
          name = "role_permission",
          joinColumns = @JoinColumn(name = "role_id")
  )
  @Enumerated(EnumType.STRING)
  @Column(name = "permission")
  private List<Permission> permissions;

  public Role()
  {
  }

  public Role(String name, String description, List<Permission> permissions)
  {
    this.name = name;
    this.description = description;
    this.permissions = permissions;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public List<Permission> getPermissions()
  {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions)
  {
    this.permissions = permissions;
  }
}
