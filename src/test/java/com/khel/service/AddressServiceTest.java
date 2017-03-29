package com.khel.service;

import com.khel.data.jpa.entity.GeoLocation;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest
{
  @Autowired
  AddressService addressService;

  @Test
  @WithMockUser(authorities = {"ADD_ADDRESS"})
  public void createAddress()
  {
    JSONParser parser = new JSONParser();
    try
    {
      File jsonFile = new File(this.getClass().getClassLoader().getResource("address.json").toURI());
      JSONArray arr = (JSONArray) parser.parse(new FileReader(jsonFile));
      for (Object obj : arr)
      {
        JSONObject jsonObject = (JSONObject) obj;
        GeoLocation address = new GeoLocation();
        address.setName((String) jsonObject.get("name"));
        Point p = Geometries.mkPoint(new G2D(Double.parseDouble(jsonObject.get("lon").toString()), Double.parseDouble(jsonObject.get("lat").toString())), CoordinateReferenceSystems.WGS84);
        address.setLocation(p);
        address = addressService.createAddress(address);
        assertThat(address.getId()).isNotNull();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
  }

  @Test
  @WithMockUser(authorities = {"PARTICIPANT_VIEW_ACTIVITY"})
  public void viewActivitiesWithIn5Kms()
  {
  }
}