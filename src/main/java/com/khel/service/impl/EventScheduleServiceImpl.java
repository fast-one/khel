package com.khel.service.impl;

import com.khel.aspect.Event;
import com.khel.data.jpa.dao.EventScheduleDao;
import com.khel.data.jpa.entity.EventSchedule;
import com.khel.data.jpa.type.EventType;
import com.khel.service.EventScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public class EventScheduleServiceImpl implements EventScheduleService
{
  @Autowired
  EventScheduleDao eventScheduleDao;

  @Override
  @Transactional
  @Event(type = EventType.EDIT_SCHEDULE)
  public EventSchedule updateEventSchedule(EventSchedule eventSchedule)
  {
    return eventScheduleDao.save(eventSchedule);
  }

  @Override
  @Transactional
  @Event(type = EventType.ADD_SCHEDULE)
  public EventSchedule createEventSchedule(EventSchedule eventSchedule)
  {
    return eventScheduleDao.save(eventSchedule);
  }

  @Override
  public EventSchedule findById(Long eventScheduleId)
  {
    return eventScheduleDao.findOne(eventScheduleId);
  }
}
