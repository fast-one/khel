package com.khel.data.jpa.entity;

import org.hibernate.envers.Audited;
import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by RKasturi on 4/5/2017.
 */
@Entity
@Audited
@Table(name = "EVENT_SCHEDULE")
public class EventSchedule extends PersistentEntity
{
  String name;
  Boolean isPublic = Boolean.TRUE;
  JSONObject details;
  Date startTime;
  Long duration;
  Boolean isRepeat = Boolean.FALSE;
  Long recurrenceId;
  SportsEvent sportsEvent;
  GeoLocation geoLocation;
  Long organizerId;

  @Column(name = "IS_PUBLIC")
  public Boolean getPublic()
  {
    return isPublic;
  }

  public void setPublic(Boolean aPublic)
  {
    isPublic = aPublic;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public JSONObject getDetails()
  {
    return details;
  }

  public void setDetails(JSONObject details)
  {
    this.details = details;
  }

  public Date getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Long getDuration()
  {
    return duration;
  }

  public void setDuration(Long duration)
  {
    this.duration = duration;
  }

  public Boolean getRepeat()
  {
    return isRepeat;
  }

  public void setRepeat(Boolean repeat)
  {
    isRepeat = repeat;
  }

  public Long getRecurrenceId()
  {
    return recurrenceId;
  }

  public void setRecurrenceId(Long recurrenceId)
  {
    this.recurrenceId = recurrenceId;
  }

  @ManyToOne
  @JoinColumn(name = "SPORTS_EVENT_ID")
  public SportsEvent getSportsEvent()
  {
    return sportsEvent;
  }

  public void setSportsEvent(SportsEvent sportsEvent)
  {
    this.sportsEvent = sportsEvent;
  }

  @ManyToOne
  @JoinColumn(name = "LOCATION_ID")
  public GeoLocation getGeoLocation()
  {
    return geoLocation;
  }

  public void setGeoLocation(GeoLocation geoLocation)
  {
    this.geoLocation = geoLocation;
  }

  public Long getOrganizerId()
  {
    return organizerId;
  }

  public void setOrganizerId(Long organizerId)
  {
    this.organizerId = organizerId;
  }
}
