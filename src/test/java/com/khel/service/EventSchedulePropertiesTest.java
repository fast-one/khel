package com.khel.service;

import com.khel.data.jpa.entity.EventSchedule;
import com.khel.helper.EventScheduleHelper;
import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:/user-truncate.sql")
public class EventSchedulePropertiesTest
{
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  EventScheduleHelper eventScheduleHelper;
  @Autowired
  UserService userService;
  @Autowired
  EventScheduleService eventScheduleService;
  EventSchedule eventSchedule;

  @Before
  public void init()
  {
    EventHolder.reset();
    userHelper.getUserAuthentication(RoleType.ORGANIZER);
    EventHolder.reset();
    eventSchedule = eventScheduleService.findById(eventScheduleHelper.createEventSchedule().getId());
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  @Test
  public void testId() throws Exception
  {
    assertThat(eventSchedule.getId()).isNotNull();
  }

  @Test
  public void testSportsEventName() throws Exception
  {
    assertThat(eventSchedule.getName()).isEqualTo(EventScheduleHelper.NAME);
  }

  @Test
  public void testStartDate() throws Exception
  {
    Date testDate = new SimpleDateFormat("yyyyMMdd").parse(EventScheduleHelper.STATE_DATE);
    assertThat(eventSchedule.getStartTime()).hasSameTimeAs(testDate);
  }

  @Test
  public void testDuration() throws Exception
  {
    assertThat(eventSchedule.getDuration()).isEqualTo(EventScheduleHelper.DURATION);
  }

  @Test
  public void testGeoLocation() throws Exception
  {
    assertThat(eventSchedule.getGeoLocation()).isNotNull();
  }

  @Test
  public void testEvent() throws Exception
  {
    assertThat(eventSchedule.getSportsEvent()).isNotNull();
  }

  @Test
  public void testAppUserId() throws Exception
  {
    assertThat(userService.findByUserId(eventSchedule.getOrganizerId())).isNotNull();
  }

  @Test
  public void testDetails() throws Exception
  {
    JSONObject jsonObject = eventSchedule.getDetails();
    for (String[] pair : EventScheduleHelper.details)
    {
      String val = (String) jsonObject.get(pair[0]);
      assertThat(val).isEqualTo(pair[1]);
    }
  }
}