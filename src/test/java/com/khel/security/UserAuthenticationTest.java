package com.khel.security;

import com.khel.helper.UserAuthenticationHelper;
import com.khel.holder.EventHolder;
import com.khel.runtime.security.service.SecureService;
import com.khel.runtime.security.type.RoleType;
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
  @Autowired
  UserAuthenticationHelper userHelper;
  @Autowired
  private ApplicationContext context;
  private Authentication authentication;
  @Autowired
  private SecureService secureService;

  @Before
  public void init()
  {
    SecurityContextHolder.clearContext();
    this.authentication = userHelper.getUserAuthentication(RoleType.PARTICIPANT);
  }

  @After
  public void after()
  {
    EventHolder.reset();
  }


  @Test
  public void authenticateWithUserName() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication userNameAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserAuthenticationHelper.USER_NAME, UserAuthenticationHelper.PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(userNameAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticateWithEmail() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserAuthenticationHelper.EMAIL, UserAuthenticationHelper.PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(emailAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticateWithMobile() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserAuthenticationHelper.MOBILE_NUMBER, UserAuthenticationHelper.PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(emailAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test(expected = AuthenticationException.class)
  public void secure() throws Exception
  {
    SecurityContextHolder.clearContext();
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticated() throws Exception
  {
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void preauth() throws Exception
  {
    assertThat("Hello World").isEqualTo(secureService.authorized());
  }

  @Test(expected = AccessDeniedException.class)
  public void denied() throws Exception
  {
    assertThat("Goodbye World").isEqualTo(secureService.denied());
  }
}