package com.khel.service;

import com.khel.helper.GeoLocationHelper;
import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.type.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:/user-truncate.sql")
public class GeoLocationServiceTest
{
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  GeoLocationHelper geoLocationHelper;

  @Before
  public void createAddress() throws Exception
  {
    EventHolder.reset();
    userHelper.getUserAuthentication(RoleType.ORGANIZER);
    EventHolder.reset();
    geoLocationHelper.createGeoLocations();
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  @Test
  @WithMockUser(authorities = {"PARTICIPANT_VIEW_EVENT"})
  public void viewActivitiesWithIn5Kms()
  {
  }
}