package com.khel.security;

import com.khel.runtime.security.service.SecureService;
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
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserAuthenticationTest
{
  @Autowired
  private ApplicationContext context;
  private Authentication authentication;
  @Autowired
  private SecureService secureService;

  @Before
  public void init()
  {
    AuthenticationManager authenticationManager = this.context
            .getBean(AuthenticationManager.class);
    this.authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken("khel", "aata"));
  }

  @After
  public void close()
  {
    SecurityContextHolder.clearContext();
  }

  @Test
  public void authenticateWithEmail() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin@khel.com", "aata"));
    SecurityContextHolder.getContext().setAuthentication(emailAuth);
    assertThat("Hello Security").isEqualTo(secureService.secure());
  }

  @Test
  public void authenticateWithMobile() throws Exception
  {
    AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
    Authentication emailAuth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("7702222194", "aata"));
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