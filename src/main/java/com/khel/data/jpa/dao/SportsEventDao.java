package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.SportsEvent;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsEventDao extends RevisionRepository<SportsEvent, Long, Long>, CustomRepository<SportsEvent, Long>
{
}
