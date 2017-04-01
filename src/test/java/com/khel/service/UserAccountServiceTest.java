package com.khel.service;

import com.khel.holder.EventHolder;
import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Sql("classpath:/user-truncate.sql")
public class UserAccountServiceTest
{
  public static final String NEW_PASSWORD = "newPassword";
  public static final String USER_NAME = "tester";
  public static final String EMAIL = "test@testme.com";
  public static final String MOBILE_NUMBER = "1234567890";
  public static final String PASSWORD = "password";

  @Autowired
  UserService userService;
  User user;
  @Autowired
  private ApplicationContext context;

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
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }

  @Test
  public void registerParticipant()
  {
    UserAccount userAccount = userService.registerParticipant(user);
    assertThat(userAccount).isNotNull();
    assertThat(userAccount.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.PARTICIPANT.name()))).isTrue();
  }

  @Test
  public void registerOrganizer()
  {
    UserAccount userAccount = userService.registerOrganizer(user);
    assertThat(userAccount).isNotNull();
    assertThat(userAccount.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ORGANIZER.name()))).isTrue();
  }

  @Test
  public void registerSupport()
  {
    UserAccount userAccount = userService.registerSupportUser(user);
    assertThat(userAccount).isNotNull();
    assertThat(userAccount.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.SUPPORT.name()))).isTrue();
  }

  @Test
  @WithMockUser(authorities = {"UPDATE_USER_PASSWORD"})
  public void updatePassword()
  {
    user = userService.registerParticipant(user);
    userService.updateUserAccountPassword(user.getId(), NEW_PASSWORD);
    AuthenticationManager authenticationManager = this.context
            .getBean(AuthenticationManager.class);
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUserName(), NEW_PASSWORD));
    assertThat(authentication.isAuthenticated()).isTrue();
  }

}