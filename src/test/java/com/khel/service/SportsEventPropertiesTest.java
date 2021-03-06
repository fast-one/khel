package com.khel.service;

import com.khel.data.jpa.entity.SportsEvent;
import com.khel.helper.SportsEventHelper;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:/user-truncate.sql")
public class SportsEventPropertiesTest
{
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  SportsEventHelper sportsEventHelper;
  @Autowired
  UserService userService;
  SportsEvent sportsEvent;

  @Before
  public void init()
  {
    EventHolder.reset();
    userHelper.getUserAuthentication(RoleType.ORGANIZER);
    EventHolder.reset();
    sportsEvent = sportsEventHelper.createSportsEvent();
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }


  @Test
  public void testId() throws Exception
  {
    assertThat(sportsEvent.getId()).isNotNull();
  }

  @Test
  public void testSportsEventName() throws Exception
  {
    assertThat(sportsEvent.getName()).isEqualTo(SportsEventHelper.NAME);
  }

  @Test
  public void testMinParticipants() throws Exception
  {
    assertThat(sportsEvent.getMinParticipants()).isEqualTo(SportsEventHelper.MIN_PARTICIPANTS);
  }

  @Test
  public void testMaxParticipants() throws Exception
  {
    assertThat(sportsEvent.getMaxParticipants()).isEqualTo(SportsEventHelper.MAX_PARTICIPANTS);
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
    assertThat(userService.findByUserId(sportsEvent.getOrganizerId())).isNotNull();
  }

  @Test
  public void testDetails() throws Exception
  {
    JSONObject jsonObject = sportsEvent.getDetails();
    for (String[] pair : SportsEventHelper.details)
    {
      String val = (String) jsonObject.get(pair[0]);
      assertThat(val).isEqualTo(pair[1]);
    }
  }
}