package com.khel.helper;

import com.khel.data.jpa.entity.SportsEvent;
import com.khel.data.jpa.type.SportsEventCategory;
import com.khel.data.jpa.type.SportsEventType;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.UserAccount;
import com.khel.service.SportsEventService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by RKasturi on 4/5/2017.
 */
@Component
public class SportsEventHelper
{
  public static final String[][] details = {
          {"foo", "bar"},
          {"x", "y"}};
  public static final String NAME = "Cricket Game";
  public static final int MIN_PARTICIPANTS = 2;
  public static final int MAX_PARTICIPANTS = 22;
  @Autowired
  SportsEventService sportsEventService;

  public SportsEvent createSportsEvent()
  {
    Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    Long organizerId = ((UserAccount) userAuthentication.getPrincipal()).getId();
    SportsEvent sportsEvent = getSportsEvent(organizerId);
    EventHolder.reset();
    sportsEvent = sportsEventService.createSportsEvent(sportsEvent);
    return sportsEvent;
  }

  public SportsEvent getSportsEvent(Long organizerId)
  {
    SportsEvent sportsEvent = new SportsEvent();
    sportsEvent.setCategory(SportsEventCategory.SPORT);
    sportsEvent.setType(SportsEventType.CRICKET);
    sportsEvent.setMinParticipants(MIN_PARTICIPANTS);
    sportsEvent.setMaxParticipants(MAX_PARTICIPANTS);
    sportsEvent.setName(NAME);
    sportsEvent.setOrganizerId(organizerId);
    JSONObject jsonObj = new JSONObject();
    for (String[] pair : details)
    {
      jsonObj.put(pair[0], pair[1]);
    }
    sportsEvent.setDetails(jsonObj);
    return sportsEvent;
  }
}
