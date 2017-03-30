package com.khel.data.jpa.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Audited
@MappedSuperclass
public abstract class PersistentEntity extends PersistentJoinEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
  @SequenceGenerator(name = "id_seq", sequenceName = "APP_SEQ", allocationSize=1)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

}
