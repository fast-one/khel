package com.khel.service;

import com.khel.data.jpa.dao.GeoLocationDao;
import com.khel.data.jpa.dao.PartialPersonDao;
import com.khel.data.jpa.dao.PersonDao;
import com.khel.data.jpa.entity.GeoLocation;
import com.khel.data.jpa.entity.PartialPerson;
import com.khel.data.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PersonService
{
  @Autowired
  private PersonDao personDao;
  @Autowired
  private GeoLocationDao addressDao;
  @Autowired
  private PartialPersonDao partialPersonDao;



  @Transactional
  public Person createPerson(String firstName, String lastName)
  {
    Person p = new Person();
    p.setFirstName(firstName);
    p.setLastName(lastName);
    Person savedPerson = personDao.save(p);
    return savedPerson;
  }

  @Transactional
  public Person savePerson(Person person)
  {
    Person savedPerson = personDao.save(person);
    return savedPerson;
  }

  @Transactional
  public Person savePersonWithEvent(Person person)
  {
    Person savedPerson = personDao.save(person);
    return savedPerson;
  }

  @Transactional
  public Person updatePerson(Person person)
  {
    Person savedPerson = personDao.save(person);
    return savedPerson;
  }

  @Transactional
  public Person updatePersonWithEvent(Person person)
  {
    Person savedPerson = personDao.save(person);
    return savedPerson;
  }

  @Transactional(readOnly = true)
  public Person findByVersion(Long id, int version)
  {
    //array indexing
    version = version <= 1 ? 0 : version - 1;
    //get all revisions
    Revisions revisions = personDao.findRevisions(id);
    //get specific order based on version
    Revision revision = (Revision) revisions.getContent().get(version);
    Person person = (Person) revision.getEntity();
    //lazy loading issue
    person.getAddresses().size();
    person.getPhoneNumbers().size();
    return person;
  }

  @Transactional(readOnly = true)
  public List<Person> loadPersonHistory(Long id)
  {
    Revisions<Long, Person> revisions = personDao.findRevisions(id);
    List<Person> personList = new ArrayList<>();
    for (Revision<Long, Person> revision : revisions)
    {
      Person person = revision.getEntity();
      person.getAddresses().size();
      person.getPhoneNumbers().size();
      personList.add(person);
    }
    return personList;
  }

  @Transactional(readOnly = true)
  public Revisions getNumberOfVersions(Long id)
  {
    Revisions revisions = personDao.findRevisions(id);
    return revisions;
  }

  @Transactional(readOnly = true)
  public Revision getCurrentVersion(Long id)
  {
    Revision revision = personDao.findLastChangeRevision(id);
    ((Person)revision.getEntity()).getAddresses().size();
    ((Person)revision.getEntity()).getPhoneNumbers().size();
    return revision;
  }

  @Transactional
  public Person savePersonAddress(Person person, GeoLocation address, int delay)
  {
    Person p = savePerson(person);
    //Pause for 30 seconds
    try
    {
      TimeUnit.SECONDS.sleep(delay);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    GeoLocation a = saveAddress(address);
    p.addAddress(a);
    return p;
  }

  // save an address
  @Transactional
  private GeoLocation saveAddress(GeoLocation address)
  {
    return addressDao.save(address);
  }

  public Person savePersonAddressNonTrans(Person person, GeoLocation address)
  {
    Person p = savePerson(person);
    GeoLocation a = saveAddress(address);
    p.addAddress(a);
    return p;
  }

  @Transactional
  public boolean deletePersonById(Long id)
  {

    personDao.delete(id);
    return true;
  }

  @Transactional
  public boolean deletePerson(Person person)
  {

    personDao.delete(person);
    return true;
  }

  @Transactional
  public boolean deletePersonList(List<Person> personList)
  {

    personDao.delete(personList);
    return true;
  }

  @Transactional
  public boolean deleteInBatch(List<Person> personList)
  {
    personDao.deleteInBatch(personList);
    return true;
  }

  @Transactional
  public boolean deletePersonAll()
  {
    personDao.deleteAll();
    return true;
  }


  public Person findById(Long id)
  {
    return personDao.findOne(id);
  }

  @Transactional
  public void markPersonInactive(Long id)
  {
    Person p = findById(id);
    p.setActiveFlag(false);
    personDao.save(p);
  }

  @Transactional
  public Person updateFirstNameById(Long personId, String firstName, Long version)
  {
    Person person = findById(personId);
    person.setFirstName(firstName);
    Person savedPerson = savePerson(person);
    return savedPerson;
  }

  public boolean deleteInBatchAll()
  {
    personDao.deleteAllInBatch();
    return true;
  }

  @Transactional(readOnly = true)
  public Person getRevision(Long personId, Long revisionNumber)
  {
    Revision<Long, Person> revision = personDao.findRevision(personId, revisionNumber);
    Person person = revision.getEntity();
    person.getAddresses().size();
    person.getPhoneNumbers().size();
    return person;
  }

  public PartialPerson updateFirstName(Long id, String newFirstName)
  {
    PartialPerson partialPerson = new PartialPerson();
    partialPerson.setId(id);
    partialPerson.setFirstName(newFirstName);
    return partialPersonDao.save(partialPerson);
  }
}
