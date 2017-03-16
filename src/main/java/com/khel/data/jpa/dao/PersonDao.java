package com.khel.data.jpa.dao;

import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.khel.data.jpa.entity.Person;

@Repository
public interface PersonDao extends RevisionRepository<Person, Long, Long>, CustomRepository<Person, Long>
{

}
