package com.khel.helper;

import com.khel.data.jpa.entity.GeoLocation;
import com.khel.holder.EventHolder;
import com.khel.service.GeoLocationService;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometries;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystems;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by RKasturi on 4/5/2017.
 */
@Component
public class GeoLocationHelper
{
  public static final String ADDRESS_JSON = "address.json";
  public static final String NAME = "VB Cricket Club (V.B.C.C)";
  public static final String LAT = "78.290007";
  public static final String LNG = "17.410189";
  @Autowired
  GeoLocationService addressService;

  public GeoLocation createGeoLocation()
  {
    EventHolder.reset();
    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setName(NAME);
    Point p = Geometries.mkPoint(new G2D(Double.parseDouble(LNG), Double.parseDouble(LAT)), CoordinateReferenceSystems.WGS84);
    return addressService.createAddress(geoLocation);
  }

  public Set<GeoLocation> createGeoLocations() throws URISyntaxException, IOException, ParseException
  {
    Set<GeoLocation> resultList = new HashSet<>();
    JSONParser parser = new JSONParser();
    File jsonFile = new File(this.getClass().getClassLoader().getResource(ADDRESS_JSON).toURI());
    JSONArray arr = (JSONArray) parser.parse(new FileReader(jsonFile));
    for (Object obj : arr)
    {
      JSONObject jsonObject = (JSONObject) obj;
      GeoLocation geoLocation = new GeoLocation();
      geoLocation.setName((String) jsonObject.get("name"));
      Point p = Geometries.mkPoint(new G2D(Double.parseDouble(jsonObject.get("lon").toString()), Double.parseDouble(jsonObject.get("lat").toString())), CoordinateReferenceSystems.WGS84);
      geoLocation.setLocation(p);
      resultList.add(addressService.createAddress(geoLocation));
      EventHolder.reset();
    }
    return resultList;
  }
}
