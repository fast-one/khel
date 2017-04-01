package com.khel.service.impl;

import com.khel.data.jpa.dao.SportsEventDao;
import com.khel.data.jpa.entity.SportsEvent;
import com.khel.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public class SportsEventServiceImpl implements SportsEventService
{
  @Autowired
  SportsEventDao sportsEventDao;

  @Override
  public SportsEvent saveSportsEvent(SportsEvent sportsEvent)
  {
    return sportsEventDao.save(sportsEvent);
  }
}
