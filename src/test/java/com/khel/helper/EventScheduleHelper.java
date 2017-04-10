package com.khel.helper;

import com.khel.data.jpa.entity.EventSchedule;
import com.khel.data.jpa.entity.GeoLocation;
import com.khel.data.jpa.entity.SportsEvent;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.UserAccount;
import com.khel.service.EventScheduleService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by RKasturi on 4/5/2017.
 */
@Component
public class EventScheduleHelper
{
  public static final long DURATION = 120L;
  public static final String[][] details = {
          {"foo", "bar"},
          {"x", "y"}};
  public static final String NAME = "Cricket Game - Weekend";
  public static final String STATE_DATE = "20170401";
  @Autowired
  SportsEventHelper sportsEventHelper;
  @Autowired
  GeoLocationHelper geoLocationHelper;
  @Autowired
  EventScheduleService eventScheduleService;

  public EventSchedule createEventSchedule()
  {
    EventSchedule eventSchedule = null;
    try
    {
      Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
      Long organizerId = ((UserAccount) userAuthentication.getPrincipal()).getId();
      SportsEvent sportsEvent = sportsEventHelper.createSportsEvent();
      EventHolder.reset();
      GeoLocation geoLocation = geoLocationHelper.createGeoLocation();
      EventHolder.reset();
      eventSchedule = new EventSchedule();
      eventSchedule.setGeoLocation(geoLocation);
      eventSchedule.setSportsEvent(sportsEvent);
      eventSchedule.setName(NAME);
      JSONObject jsonObj = new JSONObject();
      for (String[] pair : details)
      {
        jsonObj.put(pair[0], pair[1]);
      }
      eventSchedule.setDetails(jsonObj);
      eventSchedule.setOrganizerId(organizerId);
      eventSchedule.setPublic(Boolean.TRUE);
      eventSchedule.setStartTime(new SimpleDateFormat("yyyyMMdd").parse(STATE_DATE));
      eventSchedule.setDuration(DURATION);
      eventSchedule.setRepeat(Boolean.FALSE);
      eventSchedule = eventScheduleService.createEventSchedule(eventSchedule);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return eventSchedule;
  }
}
