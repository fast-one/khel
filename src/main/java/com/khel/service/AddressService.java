package com.khel.service;

import com.khel.data.jpa.dao.AddressDao;
import com.khel.data.jpa.entity.Address;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService
{
  private AddressDao addressDao;

  public AddressService(AddressDao addressDao)
  {
    this.addressDao = addressDao;
  }

  @Transactional
  public Address createAddress(Address address)
  {
    return addressDao.save(address);
  }

  @Transactional
  public Address updateAddress(Address address)
  {
    return addressDao.save(address);
  }

  @Transactional(readOnly = true)
  public Address findByVersion(Long id, int version)
  {
    //array indexing
    version = version <= 1 ? 0 : version - 1;
    //get all revisions
    Revisions revisions = addressDao.findRevisions(id);
    //get specific order based on version
    Revision revision = (Revision) revisions.getContent().get(version);
    Address address = (Address) revision.getEntity();
    return address;
  }

  @Transactional(readOnly = true)
  public List<Address> loadAddressHistory(Long id)
  {
    Revisions<Long, Address> revisions = addressDao.findRevisions(id);
    List<Address> addressList = new ArrayList<>();
    for (Revision<Long, Address> revision : revisions)
    {
      Address address = revision.getEntity();
      addressList.add(address);
    }
    return addressList;
  }

  @Transactional(readOnly = true)
  public Revisions getNumberOfVersions(Long id)
  {
    Revisions revisions = addressDao.findRevisions(id);
    return revisions;
  }

  @Transactional(readOnly = true)
  public Revision getCurrentVersion(Long id)
  {
    return addressDao.findLastChangeRevision(id);
  }
}
