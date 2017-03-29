package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.GeoLocation;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoLocationDao extends RevisionRepository<GeoLocation, Long, Long>, CustomRepository<GeoLocation, Long>
{
}
