package com.khel.service;

import com.khel.data.jpa.entity.SportsEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public interface SportsEventService
{
  @PreAuthorize("hasAuthority('ADD_EVENT')")
  SportsEvent createSportsEvent(SportsEvent sportsEvent);

  @PreAuthorize("hasAuthority('EDIT_EVENT')")
  SportsEvent updateSportsEvent(SportsEvent sportsEvent);
}
