package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.PhoneNumber;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberDao extends RevisionRepository<PhoneNumber, Long, Long>, CustomRepository<PhoneNumber, Long>
{
}
