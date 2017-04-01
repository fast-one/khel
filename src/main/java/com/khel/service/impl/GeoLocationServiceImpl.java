package com.khel.service.impl;

import com.khel.aspect.Event;
import com.khel.data.jpa.dao.GeoLocationDao;
import com.khel.data.jpa.entity.GeoLocation;
import com.khel.data.jpa.type.EventType;
import com.khel.service.GeoLocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeoLocationServiceImpl implements GeoLocationService
{
  private GeoLocationDao geoLocationDao;

  public GeoLocationServiceImpl(GeoLocationDao geoLocationDao)
  {
    this.geoLocationDao = geoLocationDao;
  }

  @Override
  @Transactional
  @Event(type = EventType.ADD_GEO_LOCATION)
  public GeoLocation createAddress(GeoLocation address)
  {
    return geoLocationDao.save(address);
  }

  @Override
  @Transactional
  @Event(type = EventType.EDIT_GEO_LOCATION)
  public GeoLocation updateAddress(GeoLocation address)
  {
    return geoLocationDao.save(address);
  }

  @Override
  @Transactional(readOnly = true)
  public List<GeoLocation> findAddressesWithin(Long meters)
  {
    return null;
  }

  @Override
  public GeoLocation findById(Long addressId)
  {
    return geoLocationDao.findOne(addressId);
  }
}
