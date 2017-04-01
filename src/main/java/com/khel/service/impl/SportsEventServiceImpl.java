package com.khel.service.impl;

import com.khel.aspect.Event;
import com.khel.data.jpa.dao.SportsEventDao;
import com.khel.data.jpa.entity.SportsEvent;
import com.khel.data.jpa.type.EventType;
import com.khel.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public class SportsEventServiceImpl implements SportsEventService
{
  @Autowired
  SportsEventDao sportsEventDao;

  @Override
  @Transactional
  @Event(type = EventType.EDIT_EVENT)
  public SportsEvent updateSportsEvent(SportsEvent sportsEvent)
  {
    return sportsEventDao.save(sportsEvent);
  }

  @Override
  @Transactional
  @Event(type = EventType.ADD_EVENT)
  public SportsEvent createSportsEvent(SportsEvent sportsEvent)
  {
    return sportsEventDao.save(sportsEvent);
  }
}
