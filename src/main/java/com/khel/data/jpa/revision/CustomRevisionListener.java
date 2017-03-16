package com.khel.data.jpa.revision;

import com.khel.data.jpa.type.EventType;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionListener implements RevisionListener
{
  @Override
  public void newRevision(Object revisionEntity)
  {
    CustomRevisionEntity entity=(CustomRevisionEntity) revisionEntity;
    //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    entity.setUserId(new Long(1));
    entity.setEventType(EventType.USER_CREATE);
  }
}
