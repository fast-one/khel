package com.khel.data.jpa.entity;

import com.khel.data.jpa.dao.listeners.RemoveListener;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Audited
@MappedSuperclass
@EntityListeners(RemoveListener.class)
public abstract class PersistentEntity implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
  @SequenceGenerator(name = "id_seq", sequenceName = "APP_SEQ", allocationSize=1)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  @Column(name = "active_flag", columnDefinition = "boolean default true", nullable = false)
  private Boolean activeFlag = Boolean.TRUE;

  public boolean isActiveFlag()
  {
    return activeFlag;
  }

  public void setActiveFlag(Boolean activeFlag)
  {
    this.activeFlag = activeFlag;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

}
