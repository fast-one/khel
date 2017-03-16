package com.khel.concurrency;

import com.khel.data.jpa.entity.Person;
import com.khel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test concurrent threads updating/inserting records
 */
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql("classpath:/test-schema.sql")
public class PersonConcurrencyTests extends AbstractTestNGSpringContextTests
{
  static final String FIRST_NAME = "Bob";
  static final String LAST_NAME = "Smith";
  @Autowired
  private PersonService personService;
  private Long personId;
  private Long personId2;

  @BeforeClass
  public void setup()
  {
    personId = personService.createPerson(FIRST_NAME, LAST_NAME).getId();
    personId2 = personService.createPerson(FIRST_NAME, LAST_NAME).getId();
  }

  @Test(threadPoolSize = 5, invocationCount = 20, timeOut = 10000)
  public void createMultipleThreads()
  {
    Person p = personService.createPerson(FIRST_NAME, LAST_NAME);
    assertThat(p.getId()).isNotNull();
  }

  @Test(threadPoolSize = 5, invocationCount = 20, timeOut = 10000)
  public void updateMultipleThreads()
  {
    try
    {
      long id = ThreadLocalRandom.current().nextInt();
      String firstName = FIRST_NAME + id;
      Person p = personService.updateFirstNameById(personId, firstName, null);
      assertThat(p.getFirstName()).isEqualTo(firstName);
    }
    catch (Exception ex)
    {
      assertThat(ex).isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
  }

  @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 10000)
  public void personConcurrentUpdate()
  {
    try
    {
      long id = ThreadLocalRandom.current().nextInt();
      String firstName = FIRST_NAME + id;
      Person p = personService.updateFirstNameById(personId2, firstName, new Long(0));
      assertThat(p.getFirstName()).isEqualTo(firstName);
    }
    catch (Exception ex)
    {
      assertThat(ex).isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
  }


  @AfterClass
  public void validateUpdateMultiple()
  {
    // it should have only 2 revisions 1 for create and other for update
    assertThat(personService.getNumberOfVersions(personId2).getContent()).hasSize(2);
  }

}

