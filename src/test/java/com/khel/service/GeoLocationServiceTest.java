package com.khel.service;

import com.khel.data.jpa.entity.GeoLocation;
import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("classpath:/user-truncate.sql")
public class GeoLocationServiceTest
{
  public static final String ADDRESS_JSON = "address.json";
  @Autowired
  SportsEventService sportsEventService;
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  UserService userService;
  GeoLocation geoLocation;

  @Autowired
  GeoLocationService addressService;

  @Before
  public void createAddress() throws Exception
  {
    userHelper.getUserAuthentication(RoleType.ORGANIZER);
    EventHolder.reset();
    createGeoLocations();
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  private void createGeoLocations() throws URISyntaxException, IOException, ParseException
  {
    JSONParser parser = new JSONParser();
    File jsonFile = new File(this.getClass().getClassLoader().getResource(ADDRESS_JSON).toURI());
    JSONArray arr = (JSONArray) parser.parse(new FileReader(jsonFile));
    for (Object obj : arr)
    {
      JSONObject jsonObject = (JSONObject) obj;
      geoLocation = new GeoLocation();
      geoLocation.setName((String) jsonObject.get("name"));
      Point p = Geometries.mkPoint(new G2D(Double.parseDouble(jsonObject.get("lon").toString()), Double.parseDouble(jsonObject.get("lat").toString())), CoordinateReferenceSystems.WGS84);
      geoLocation.setLocation(p);
      geoLocation = addressService.createAddress(geoLocation);
    }
  }

  @Test
  @WithMockUser(authorities = {"PARTICIPANT_VIEW_EVENT"})
  public void viewActivitiesWithIn5Kms()
  {
  }
}