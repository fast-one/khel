package com.khel.data.jpa.revision;

import com.khel.data.jpa.type.EventType;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.UserAccount;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionListener implements RevisionListener
{
  @Override
  public void newRevision(Object revisionEntity)
  {
    CustomRevisionEntity entity=(CustomRevisionEntity) revisionEntity;
    EventType eventType = EventHolder.get();
    if (eventType.equals(EventType.PARTICIPANT_REGISTER)
            || eventType.equals(EventType.ORGANIZER_REGISTER)
            || eventType.equals(EventType.SUPPORT_REGISTER)
            || eventType.equals(EventType.UPDATE_USER_PASSWORD))
    {
      entity.setUserId(1L);
    }
    else
    {
      UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      entity.setUserId(user.getId());
    }
    entity.setEventType(eventType);
  }
}
