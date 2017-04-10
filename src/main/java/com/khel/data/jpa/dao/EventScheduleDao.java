package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.EventSchedule;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventScheduleDao extends RevisionRepository<EventSchedule, Long, Long>, CustomRepository<EventSchedule, Long>
{
}
