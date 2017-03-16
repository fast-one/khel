package com.khel.service;

import com.khel.data.jpa.entity.Address;
import com.khel.data.jpa.entity.Person;
import com.khel.data.jpa.entity.PhoneNumber;
import com.khel.data.jpa.type.AddressType;
import com.khel.data.jpa.type.PhoneNumberType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql("classpath:/test-schema.sql")
public class PersonServiceTest
{
  // Test constants
  static final String FIRST_NAME = "Bob";
  static final String LAST_NAME = "Smith";
  static final String NEW_FIRST_NAME = "John";
  static final String NEW_LAST_NAME = "Johnson";
  static final String STREET = "123 South 456 East";
  static final String CITY = "Some City";
  static final String STATE = "Utah";
  static final String POSTAL_CODE = "84111";
  static final AddressType ADDRESS_TYPE = AddressType.HOME;
  public static final String PHONE_NUMBER = "801-521-0600";
  public static final String NEW_PHONE_NUMBER = "801-521-0601";
  //expected exceptions
  @Rule
  public ExpectedException exception = ExpectedException.none();
  @Autowired
  PersonService personService;
  @Autowired
  AddressService addressService;
  @Autowired
  PhoneNumberService phoneNumberService;

  @Test
  public void createPerson()
  {
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p).isNotNull();
    System.out.println(p.getId());
  }

  @Test
  public void createPersonVersion()
  {
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    Assert.notNull(p);
    System.out.println(p.getId());
    //test if a version is created, and is the latest revision is 1
    Person revision = personService.findByVersion(p.getId(), 1);
    assertThat(revision).isNotNull();
    assertThat(revision.getFirstName()).isEqualTo(FIRST_NAME);
  }

  @Test
  public void personByVersion()
  {
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    Assert.notNull(p);
    Long pId = p.getId();
    p.setFirstName(NEW_FIRST_NAME);
    personService.updatePerson(p);
    p.setLastName(NEW_LAST_NAME);
    personService.updatePerson(p);
    //version 1
    Person p1 = personService.findByVersion(pId, 1);
    assertThat(p1.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(p1.getLastName()).isEqualTo(LAST_NAME);
    //version 2 should have updated first name
    Person p2 = personService.findByVersion(pId, 2);
    assertThat(p2.getFirstName()).isEqualTo(NEW_FIRST_NAME);
    //version 3 should have updated first name, last_name
    Person p3 = personService.findByVersion(pId, 3);
    assertThat(p3.getFirstName()).isEqualTo(NEW_FIRST_NAME);
    assertThat(p3.getLastName()).isEqualTo(NEW_LAST_NAME);
  }

  @Test
  public void personByAddressVersion()
  {
    // Create person
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p).isNotNull();
    Long pId = p.getId();
    Revision<Long, Person> rv1 = personService.getCurrentVersion(pId);
    // Add an address
    Address address = new Address();
    address.setStreet(STREET);
    address.setCity(CITY);
    address.setState(STATE);
    address.setPostalCode(POSTAL_CODE);
    address.setType(ADDRESS_TYPE);
    p.addAddress(address);
    p = personService.updatePerson(p);
    Revision<Long, Person> rv2 = personService.getCurrentVersion(pId);
    // Change first name to create another version
    p.setFirstName(NEW_FIRST_NAME);
    personService.updatePerson(p);
    Revision<Long, Person> rv3 = personService.getCurrentVersion(pId);
    // Version 1 no address first name original
    Person p1 = rv1.getEntity();
    assertThat(p1.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(p1.getAddresses()).isEmpty();
    // Version 2 has an address original first name
    Person p2 = rv2.getEntity();
    assertThat(p2.getAddresses().get(0).getCity()).isEqualTo(CITY);
    assertThat(p2.getFirstName()).isEqualTo(FIRST_NAME);
    // Version 3 has an address new first name
    Person p3 = rv3.getEntity();
    assertThat(p3.getAddresses().get(0).getCity()).isEqualTo(CITY);
    assertThat(p3.getFirstName()).isEqualTo(NEW_FIRST_NAME);
  }


  @Test
  public void personAndPhoneVersion()
  {

    // Initialize person
    Person p = new Person();
    p.setFirstName(FIRST_NAME);
    p.setLastName(LAST_NAME);

    // add a phone number and save person
    PhoneNumber phoneNumber = new PhoneNumber();
    phoneNumber.setNumber(PHONE_NUMBER);
    phoneNumber.setType(PhoneNumberType.WORK);
    phoneNumber.setPerson(p);
    p.addPhoneNumber(phoneNumber);
    personService.savePerson(p);
    Revision<Long, Person> rv1 = personService.getCurrentVersion(p.getId());

    //update phone number and not person
    PhoneNumber savedPhoneNumber = phoneNumberService.findById(phoneNumber.getId());
    assertThat(savedPhoneNumber.getId()).isNotNull();
    savedPhoneNumber.setNumber(NEW_PHONE_NUMBER);
    PhoneNumber updatedPhoneNumber = phoneNumberService.updatePhoneNumber(savedPhoneNumber);
    assertThat(updatedPhoneNumber.getId()).isNotNull();
    Revision<Long, PhoneNumber> rv2 = phoneNumberService.getCurrentVersion(phoneNumber.getId());

    // Change first name to create another version
    Person savedPerson = personService.findById(p.getId());
    savedPerson.setFirstName(NEW_FIRST_NAME);
    personService.updatePerson(savedPerson);
    Revision<Long, Person> rv3 = personService.getCurrentVersion(savedPerson.getId());


    // Version 1 has phone number and first name
    Person p1 = personService.getRevision(p.getId(), rv1.getRevisionNumber());
    assertThat(p1.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(p1.getPhoneNumbers().get(0).getNumber()).isEqualTo(PHONE_NUMBER);

    // Version 2 has new phone number
    Person p2 = personService.getRevision(p.getId(), rv2.getRevisionNumber());
    assertThat(p2.getPhoneNumbers().get(0).getNumber()).isEqualTo(NEW_PHONE_NUMBER);
    assertThat(p2.getFirstName()).isEqualTo(FIRST_NAME);

    // Version 3 has new phone number and new first name
    Person p3 = personService.getRevision(p.getId(), rv3.getRevisionNumber());
    assertThat(p3.getFirstName()).isEqualTo(NEW_FIRST_NAME);
    assertThat(p3.getPhoneNumbers().get(0).getNumber()).isEqualTo(NEW_PHONE_NUMBER);
  }

  @Test
  public void personAndAddressTrans()
  {
    //initialize person
    Person person = new Person();
    person.setFirstName(FIRST_NAME);
    person.setLastName(LAST_NAME);
    // initialize an address
    Address address = new Address();
    address.setStreet(STREET);
    address.setCity(CITY);
    address.setState(STATE);
    address.setPostalCode(POSTAL_CODE);
    address.setType(ADDRESS_TYPE);
    //save person and address in same transactions with time delay in between
    Person p = personService.savePersonAddress(person, address, 5);
    Revision pRevision = personService.getCurrentVersion(p.getId());
    Revision aRevision = addressService.getCurrentVersion(p.getAddresses().get(0).getId());
    assertThat(pRevision.getRevisionNumber()).isEqualTo(aRevision.getRevisionNumber());
    assertThat(pRevision.getRevisionDate()).isEqualTo(aRevision.getRevisionDate());
  }

  @Test
  public void personAndAddressNonTrans()
  {
    //initialize person
    Person person = new Person();
    person.setFirstName(FIRST_NAME);
    person.setLastName(LAST_NAME);
    // initialize an address
    Address address = new Address();
    address.setStreet(STREET);
    address.setCity(CITY);
    address.setState(STATE);
    address.setPostalCode(POSTAL_CODE);
    address.setType(ADDRESS_TYPE);
    //save person and address in different transactions
    Person p = personService.savePersonAddressNonTrans(person, address);
    Revision pRevision = personService.getCurrentVersion(p.getId());
    Revision aRevision = addressService.getCurrentVersion(p.getAddresses().get(0).getId());
    assertThat(pRevision.getRevisionNumber()).isNotEqualTo(aRevision.getRevisionNumber());
    assertThat(pRevision.getRevisionDate()).isNotEqualTo(aRevision.getRevisionDate());
  }

  @Test
  public void personAddressHistory()
  {
    // Create person
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p).isNotNull();
    Long pId = p.getId();
    // Add an address
    Address address = new Address();
    address.setStreet(STREET);
    address.setCity(CITY);
    address.setState(STATE);
    address.setPostalCode(POSTAL_CODE);
    address.setType(ADDRESS_TYPE);
    p.addAddress(address);
    personService.updatePerson(p);
    // Change last name to create another version
    p.setLastName(NEW_LAST_NAME);
    personService.updatePerson(p);
    // Load history
    List<Person> pHistory = personService.loadPersonHistory(pId);
    // Should be correct size
    assertThat(pHistory).hasSize(3);
    // First should have no address and original last name
    assertThat(pHistory.get(0).getAddresses()).isEmpty();
    assertThat(pHistory.get(0).getLastName()).isEqualTo(LAST_NAME);
    // Second should have address and original last name
    assertThat(pHistory.get(1).getAddresses().get(0).getState()).isEqualTo(STATE);
    assertThat(pHistory.get(1).getLastName()).isEqualTo(LAST_NAME);
    // Third should have address and new last name
    assertThat(pHistory.get(2).getAddresses().get(0).getState()).isEqualTo(STATE);
    assertThat(pHistory.get(2).getLastName()).isEqualTo(NEW_LAST_NAME);
  }

  @Test
  public void personActiveFlag()
  {
    // Create person - default active flag is true
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    //retrieve saved person
    Person savedPerson = personService.findById(p.getId());
    //remove the person
    personService.markPersonInactive(p.getId());
    //retrieve saved person
    Person deletedPerson = personService.findById(p.getId());
    //do the tests
    assertThat(p).isNotNull();
    assertThat(savedPerson.isActiveFlag()).isTrue();
    assertThat(deletedPerson.isActiveFlag()).isFalse();
  }

  @Test
  public void personDeleteById()
  {
    //expect exception
    exception.expect(org.springframework.dao.InvalidDataAccessApiUsageException.class);
    // Create person
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    //delete the person
    personService.deletePersonById(p.getId());
    assertThat(personService.findById(p.getId())).isNotNull();
  }

  @Test
  public void personDeleteByEntity()
  {
    //expect exception
    exception.expect(org.springframework.dao.InvalidDataAccessApiUsageException.class);
    // Create person
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    //delete the person
    personService.deletePerson(p);
    assertThat(personService.findById(p.getId())).isNotNull();
  }

  @Test
  public void personDeleteAll()
  {
    // Create person
    Person p1 = personService.createPerson(FIRST_NAME, LAST_NAME);
    // Create person
    Person p2 = personService.createPerson(NEW_FIRST_NAME, NEW_LAST_NAME);
    //delete all persons
    personService.deletePersonAll();

    //assert that person still exists
    assertThat(personService.findById(p1.getId())).isNotNull();
    assertThat(personService.findById(p2.getId())).isNotNull();
  }

  @Test
  public void personDeleteList()
  {
    //expect exception
    exception.expect(org.springframework.dao.InvalidDataAccessApiUsageException.class);
    List<Person> list = new ArrayList<Person>();
    // Create person
    Person p1 = personService.createPerson(FIRST_NAME, LAST_NAME);
    list.add(p1);
    // Create person
    Person p2 = personService.createPerson(NEW_FIRST_NAME, NEW_LAST_NAME);
    list.add(p2);

    //delete all persons
    personService.deletePersonList(list);

    //assert that person still exists
    assertThat(personService.findById(p1.getId())).isNotNull();
    assertThat(personService.findById(p2.getId())).isNotNull();
  }


  @Test
  public void personDeleteBatch()
  {
    // we need to find a way to block deleteInBatch - do we really need?
    //expect exception
    exception.expect(org.springframework.dao.InvalidDataAccessApiUsageException.class);
    List<Person> list = new ArrayList<Person>();
    // Create person
    Person p1 = personService.createPerson(FIRST_NAME, LAST_NAME);
    list.add(p1);
    // Create person
    Person p2 = personService.createPerson(NEW_FIRST_NAME, NEW_LAST_NAME);
    list.add(p2);
    //delete all persons
    personService.deleteInBatch(list);

    //assert that person still exists
    assertThat(personService.findById(p1.getId())).isNotNull();
    assertThat(personService.findById(p2.getId())).isNotNull();
  }

  @Test
  public void personDeleteBatchAll()
  {
    List<Person> list = new ArrayList<Person>();
    // Create person
    Person p1 = personService.createPerson(FIRST_NAME, LAST_NAME);
    list.add(p1);
    // Create person
    Person p2 = personService.createPerson(NEW_FIRST_NAME, NEW_LAST_NAME);
    list.add(p2);
    //delete all persons
    personService.deleteInBatchAll();
    //assert that person still exists
    assertThat(personService.findById(p1.getId())).isNotNull();
    assertThat(personService.findById(p2.getId())).isNotNull();
  }

  @Test
  public void personActiveFlagVersion()
  {
    // Create person
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p).isNotNull();
    //update person
    p.setFirstName(NEW_FIRST_NAME);
    personService.updatePerson(p);
    //remove the person
    personService.markPersonInactive(p.getId());
    //find revisions
    Person pv1 = personService.findByVersion(p.getId(), 1);
    Person pv2 = personService.findByVersion(p.getId(), 2);
    Person pv3 = personService.findByVersion(p.getId(), 3);
    //do the tests
    assertThat(pv1.isActiveFlag()).isTrue();
    assertThat(pv2.getFirstName()).isEqualTo(NEW_FIRST_NAME);
    assertThat(pv3.isActiveFlag()).isFalse();
  }

  /**
   * Test partial entity save, like entity would target only few fields
   */
  @Test
  public void savePartialPerson()
  {
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p).isNotNull();
    personService.updateFirstName(p.getId(), NEW_FIRST_NAME);
    Revision revision = personService.getCurrentVersion(p.getId());
    assertThat(((Person)revision.getEntity()).getFirstName()). isEqualTo(NEW_FIRST_NAME);
    assertThat(((Person)revision.getEntity()).getLastName()). isNull();

    Person updatedPerson = personService.findById(p.getId());
    assertThat(updatedPerson.getLastName()).isEqualTo(LAST_NAME);
  }

}