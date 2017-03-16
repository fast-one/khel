package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.PartialPerson;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartialPersonDao extends RevisionRepository<PartialPerson, Long, Long>, CustomRepository<PartialPerson, Long>
{

}
