package com.khel.security;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.service.SecureService;
import com.khel.runtime.security.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Sql("classpath:/user-truncate.sql")
public class UserAuthenticationTest
{
  public static final String USER_NAME = "tester";
  public static final String EMAIL = "test@testme.com";
  public static final String MOBILE_NUMBER = "1234567890";
  public static final String PASSWORD = "password";
  @Autowired
  UserService userService;
  @Autowired
  private ApplicationContext context;
  private Authentication authentication;
  @Autowired
  private SecureService secureService;

  @Before
  public void init()
  {
    User user = new User();
    user.setEmail(EMAIL);
    user.setFirstName("Me");
    user.setLastName("Test");
    user.setMobile(MOBILE_NUMBER);
    user.setUserName(USER_NAME);
    user.setPassword(PASSWORD);
    userService.registerParticipant(user);
    AuthenticationManager authenticationManager = this.context
            .getBean(AuthenticationManager.class);
    this.authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(USER_NAME, PASSWORD));

  }

  @After
  public void close()
  {
    SecurityContextHolder.clearContext();
  }

  @Test
  public void authenticateWithUserName() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication userNameAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USER_NAME, PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(userNameAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }


  @Test
  public void authenticateWithEmail() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(emailAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticateWithMobile() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(MOBILE_NUMBER, PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(emailAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test(expected = AuthenticationException.class)
  public void secure() throws Exception
  {
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticated() throws Exception
  {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void preauth() throws Exception
  {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    assertThat("Hello World").isEqualTo(secureService.authorized());
  }

  @Test(expected = AccessDeniedException.class)
  public void denied() throws Exception
  {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    assertThat("Goodbye World").isEqualTo(secureService.denied());
  }
}