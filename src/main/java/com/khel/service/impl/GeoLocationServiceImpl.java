package com.khel.service.impl;

import com.khel.data.jpa.dao.GeoLocationDao;
import com.khel.data.jpa.entity.GeoLocation;
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
  public GeoLocation createAddress(GeoLocation address)
  {
    return geoLocationDao.save(address);
  }

  @Override
  @Transactional
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
