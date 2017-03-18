package com.khel.data.jpa.revision;

import com.khel.data.jpa.type.EventType;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "app_audit")
@RevisionEntity(CustomRevisionListener .class)
public class CustomRevisionEntity implements Serializable
{
  @Id
  @GeneratedValue
  @RevisionNumber
  @Column(name = "version_number")
  private Long versionNumber;

  @RevisionTimestamp
  @Column(name = "version_time")
  private long timestamp;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "event_type")
  private EventType eventType;

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public Long getVersionNumber()
  {
    return versionNumber;
  }

  public void setVersionNumber(Long versionNumber)
  {
    this.versionNumber = versionNumber;
  }

  public long getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(long timestamp)
  {
    this.timestamp = timestamp;
  }

  public EventType getEventType()
  {
    return eventType;
  }

  public void setEventType(EventType eventType)
  {
    this.eventType = eventType;
  }
}
