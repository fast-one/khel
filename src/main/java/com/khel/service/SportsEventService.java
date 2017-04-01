package com.khel.service;

import com.khel.data.jpa.entity.SportsEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public interface SportsEventService
{
  @Transactional
  SportsEvent saveSportsEvent(SportsEvent sportsEvent);
}
