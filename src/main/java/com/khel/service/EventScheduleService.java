package com.khel.service;

import com.khel.data.jpa.entity.EventSchedule;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Service
public interface EventScheduleService
{
  @PreAuthorize("hasAuthority('ADD_EVENT')")
  EventSchedule createEventSchedule(EventSchedule eventSchedule);

  @PreAuthorize("hasAuthority('EDIT_EVENT')")
  EventSchedule updateEventSchedule(EventSchedule eventSchedule);

  EventSchedule findById(Long eventScheduleId);
}
