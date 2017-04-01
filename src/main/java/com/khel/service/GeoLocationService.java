package com.khel.service;

import com.khel.data.jpa.entity.GeoLocation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by RKasturi on 3/18/2017.
 */
public interface GeoLocationService
{
  @PreAuthorize("hasAuthority('ADD_GEO_LOCATION')")
  GeoLocation createAddress(GeoLocation address);

  @PreAuthorize("hasAuthority('EDIT_GEO_LOCATION')")
  GeoLocation updateAddress(GeoLocation address);

  @Transactional(readOnly = true)
  List<GeoLocation> findAddressesWithin(Long meters);

  @Transactional(readOnly = true)
  GeoLocation findById(Long addressId);

}
