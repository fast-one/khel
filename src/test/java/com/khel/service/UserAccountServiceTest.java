package com.khel.service;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.impl.UserAccountService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAccountServiceTest
{
  //expected exceptions
  @Rule
  public ExpectedException exception = ExpectedException.none();
  @Autowired
  UserAccountService userAccountService;
  
  @Test
  public void createUser()
  {
    User user = initUser();
    UserAccount userAccount = userAccountService.createNewUserAccount(user);
    assertThat(userAccount).isNotNull();
  }


  private User initUser()
  {
    User user = new User();
    user.setEmail("test@testme.com");
    user.setFirstName("Me");
    user.setLastName("Test");
    user.setMobile("1234567890");
    user.setUserName("tester");
    user.setPassword("password");
    return user;
  }
}