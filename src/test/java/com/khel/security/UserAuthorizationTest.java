package com.khel.security;

import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import org.junit.After;
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
  public static final String NEW_PASSWORD = "newPassword";
  @Autowired
  UserService userService;
  @Autowired
  UserAuthenticationHelper userHelper;
  UserAccount user;

  @Before
  public void init()
  {
    user = userHelper.registerParticipant();
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  @Test(expected = org.springframework.security.authentication.AuthenticationCredentialsNotFoundException.class)
  public void testWithNoAuthentication()
  {
    userService.updateUserAccountPassword(user.getId(), NEW_PASSWORD);
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser
  public void testWithNoAuthority()
  {
    userService.updateUserAccountPassword(user.getId(), NEW_PASSWORD);
  }

  @Test(expected = org.springframework.security.access.AccessDeniedException.class)
  @WithMockUser(authorities = {"DELETE_USER"})
  public void testWithWrongAuthority()
  {
    userService.updateUserAccountPassword(user.getId(), NEW_PASSWORD);
  }

  @Test
  @WithMockUser(authorities = {"UPDATE_USER_PASSWORD"})
  public void testWithCorrectAuthority()
  {
    userService.updateUserAccountPassword(user.getId(), NEW_PASSWORD);
  }
}