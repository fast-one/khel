package com.khel.data.jpa.entity;

import com.khel.data.jpa.converter.JsonConverter;
import com.khel.data.jpa.type.SportsEventCategory;
import com.khel.data.jpa.type.SportsEventType;
import org.hibernate.envers.Audited;
import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Entity
@Audited
@Table(name = "SPORTS_EVENT")
public class SportsEvent extends PersistentEntity
{
  String name;
  SportsEventType type;
  SportsEventCategory category;
  Integer minParticipants;
  Integer maxParticipants;
  JSONObject details;
  private Long appUserId;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "TYPE")
  public SportsEventType getType()
  {
    return type;
  }

  public void setType(SportsEventType type)
  {
    this.type = type;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "CATEGORY")
  public SportsEventCategory getCategory()
  {
    return category;
  }

  public void setCategory(SportsEventCategory category)
  {
    this.category = category;
  }

  public Integer getMinParticipants()
  {
    return minParticipants;
  }

  public void setMinParticipants(Integer minParticipants)
  {
    this.minParticipants = minParticipants;
  }

  public Integer getMaxParticipants()
  {
    return maxParticipants;
  }

  public void setMaxParticipants(Integer maxParticipants)
  {
    this.maxParticipants = maxParticipants;
  }

  @Convert(converter = JsonConverter.class)
  public JSONObject getDetails()
  {
    return details;
  }

  public void setDetails(JSONObject details)
  {
    this.details = details;
  }

  public Long getAppUserId()
  {
    return appUserId;
  }

  public void setAppUserId(Long appUserId)
  {
    this.appUserId = appUserId;
  }
}
