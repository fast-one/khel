package com.khel.security;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Sql("classpath:/user-truncate.sql")
public class UserAuthorizationTest
{
  public static final String USER_NAME = "tester";
  public static final String EMAIL = "test@testme.com";
  public static final String MOBILE_NUMBER = "1234567890";
  public static final String PASSWORD = "password";
  @Autowired
  UserService userService;
  User user;

  @Before
  public void init()
  {
    user = new User();
    user.setEmail(EMAIL);
    user.setFirstName("Me");
    user.setLastName("Test");
    user.setMobile(MOBILE_NUMBER);
    user.setUserName(USER_NAME);
    user.setPassword(PASSWORD);
    userService.registerParticipant(user);
  }


  @Test(expected = org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class)
  public void testWithNoAuthentication()
  {
    userService.updateUserAccountPassword(user, "newPassword");
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser
  public void testWithNoAuthority()
  {
    userService.updateUserAccountPassword(user, "newPassword");
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser(authorities =  { "DELETE_USER"})
  public void testWithWrongAuthority()
  {
    userService.updateUserAccountPassword(user, "newPassword");
  }

  @Test
  @WithMockUser(authorities = {"UPDATE_USER_PASSWORD"})
  public void testWithCorrectAuthority()
  {
    user = userService.registerParticipant(user);
    userService.updateUserAccountPassword(user, "newPassword");
  }
}