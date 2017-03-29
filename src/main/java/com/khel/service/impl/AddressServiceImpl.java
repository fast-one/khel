package com.khel.service.impl;

import com.khel.data.jpa.dao.GeoLocationDao;
import com.khel.data.jpa.entity.GeoLocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements com.khel.service.AddressService
{
  private GeoLocationDao addressDao;

  public AddressServiceImpl(GeoLocationDao addressDao)
  {
    this.addressDao = addressDao;
  }

  @Override
  @Transactional
  public GeoLocation createAddress(GeoLocation address)
  {
    return addressDao.save(address);
  }

  @Override
  @Transactional
  public GeoLocation updateAddress(GeoLocation address)
  {
    return addressDao.save(address);
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
    return addressDao.findOne(addressId);
  }
}
