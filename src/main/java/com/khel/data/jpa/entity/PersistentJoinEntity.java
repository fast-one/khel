package com.khel.data.jpa.entity;

import com.khel.data.jpa.dao.listeners.EntityListener;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Audited
@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class PersistentJoinEntity implements Serializable
{
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
}
