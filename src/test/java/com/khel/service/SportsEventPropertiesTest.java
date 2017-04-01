package com.khel.service;

import com.khel.data.jpa.entity.SportsEvent;
import com.khel.data.jpa.type.SportsEventCategory;
import com.khel.data.jpa.type.SportsEventType;
import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:/user-truncate.sql")
public class SportsEventPropertiesTest
{
  public static final String[][] details = {
          {"foo", "bar"},
          {"x", "y"}};
  public static final String NAME = "Cricket Game";
  public static final int MIN_PARTICIPANTS = 2;
  public static final int MAX_PARTICIPANTS = 22;
  @Autowired
  SportsEventService sportsEventService;
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  UserService userService;
  SportsEvent sportsEvent;

  @Before
  public void init()
  {
    sportsEvent = getSportsEvent(userHelper.getUserAuthentication(RoleType.ORGANIZER));
    EventHolder.reset();
    sportsEvent = sportsEventService.createSportsEvent(sportsEvent);
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  private SportsEvent getSportsEvent(Authentication authentication)
  {
    SportsEvent sportsEvent = new SportsEvent();
    sportsEvent.setCategory(SportsEventCategory.SPORT);
    sportsEvent.setType(SportsEventType.CRICKET);
    sportsEvent.setMinParticipants(MIN_PARTICIPANTS);
    sportsEvent.setMaxParticipants(MAX_PARTICIPANTS);
    sportsEvent.setName(NAME);
    sportsEvent.setAppUserId(((UserAccount) authentication.getPrincipal()).getId());
    JSONObject jsonObj = new JSONObject();
    for (String[] pair : details)
    {
      jsonObj.put(pair[0], pair[1]);
    }
    sportsEvent.setDetails(jsonObj);
    return sportsEvent;
  }

  @Test
  public void testId() throws Exception
  {
    assertThat(sportsEvent.getId()).isNotNull();
  }

  @Test
  public void testSportsEventName() throws Exception
  {
    assertThat(sportsEvent.getName()).isEqualTo(NAME);
  }

  @Test
  public void testMinParticipants() throws Exception
  {
    assertThat(sportsEvent.getMinParticipants()).isEqualTo(MIN_PARTICIPANTS);
  }

  @Test
  public void testMaxParticipants() throws Exception
  {
    assertThat(sportsEvent.getMaxParticipants()).isEqualTo(MAX_PARTICIPANTS);
  }

  @Test
  public void testType() throws Exception
  {
    assertThat(sportsEvent.getId()).isNotNull();
  }

  @Test
  public void testCategory() throws Exception
  {
    assertThat(sportsEvent.getId()).isNotNull();
  }

  @Test
  public void testAppUserId() throws Exception
  {
    assertThat(userService.findByUserId(sportsEvent.getAppUserId())).isNotNull();
  }

  @Test
  public void testDetails() throws Exception
  {
    JSONObject jsonObject = sportsEvent.getDetails();
    for (String[] pair : details)
    {
      String val = (String) jsonObject.get(pair[0]);
      assertThat(val).isEqualTo(pair[1]);
    }
  }
}