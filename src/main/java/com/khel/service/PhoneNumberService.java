package com.khel.service;

import com.khel.data.jpa.dao.PhoneNumberDao;
import com.khel.data.jpa.entity.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberService
{
  @Autowired
  private PhoneNumberDao phoneNumberDao;

  @Transactional
  public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber)
  {
    return phoneNumberDao.save(phoneNumber);
  }

  @Transactional
  public PhoneNumber updatePhoneNumber(PhoneNumber phoneNumber)
  {
    return phoneNumberDao.save(phoneNumber);
  }

  @Transactional(readOnly = true)
  public PhoneNumber findByVersion(Long id, int version)
  {
    //array indexing
    version = version <= 1 ? 0 : version - 1;
    //get all revisions
    Revisions revisions = phoneNumberDao.findRevisions(id);
    //get specific order based on version
    Revision revision = (Revision) revisions.getContent().get(version);
    PhoneNumber phoneNumber = (PhoneNumber) revision.getEntity();
    return phoneNumber;
  }

  @Transactional(readOnly = true)
  public List<PhoneNumber> loadAddressHistory(Long id)
  {
    Revisions<Long, PhoneNumber> revisions = phoneNumberDao.findRevisions(id);
    List<PhoneNumber> phoneNumbers = new ArrayList<>();
    for (Revision<Long, PhoneNumber> revision : revisions)
    {
      PhoneNumber phoneNumber = revision.getEntity();
      phoneNumbers.add(phoneNumber);
    }
    return phoneNumbers;
  }

  @Transactional(readOnly = true)
  public Revisions getNumberOfVersions(Long id)
  {
    Revisions revisions = phoneNumberDao.findRevisions(id);
    return revisions;
  }

  @Transactional(readOnly = true)
  public Revision getCurrentVersion(Long id)
  {
    return phoneNumberDao.findLastChangeRevision(id);
  }

  public PhoneNumber findById(Long id)
  {
    return phoneNumberDao.findOne(id);
  }
}
