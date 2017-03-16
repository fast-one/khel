package com.khel.data.jpa.dao;

import com.khel.data.jpa.entity.Address;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends RevisionRepository<Address, Long, Long>, CustomRepository<Address, Long>
{
}
