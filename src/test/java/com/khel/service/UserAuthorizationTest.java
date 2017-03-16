package com.khel.service;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.service.impl.UserAccountService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserAuthorizationTest
{
  @Autowired
  UserService userService;


  @Test(expected = org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class)
  public void testWithNoAuthentication()
  {
    User user = initUser();
    UserAccount userAccount = userService.createNewUserAccount(user);
    assertThat(userAccount).isNotNull();
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser
  public void testWithNoAuthority()
  {
    User user = initUser();
    UserAccount userAccount = userService.createNewUserAccount(user);
    assertThat(userAccount).isNotNull();
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser(authorities =  { "DELETE_USER"})
  public void testWithWrongAuthority()
  {
    User user = initUser();
    UserAccount userAccount = userService.createNewUserAccount(user);
  }

  @Test
  @WithMockUser(authorities =  { "ADD_USER"})
  public void testWithCorrectAuthority()
  {
    User user = initUser();
    UserAccount userAccount = userService.createNewUserAccount(user);
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