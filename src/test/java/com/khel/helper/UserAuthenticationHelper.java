package com.khel.helper;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by RKasturi on 3/31/2017.
 */
@Component
public class UserAuthenticationHelper
{
  public static final String USER_NAME = "tester";
  public static final String EMAIL = "test@testme.com";
  public static final String MOBILE_NUMBER = "1234567890";
  public static final String PASSWORD = "password";
  @Autowired
  UserService userService;
  @Autowired
  private ApplicationContext context;

  public Authentication getUserAuthentication(RoleType roleType)
  {
    registerUser(roleType);
    AuthenticationManager authenticationManager = this.context
            .getBean(AuthenticationManager.class);
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(USER_NAME, PASSWORD));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return authentication;
  }

  public UserAccount registerParticipant()
  {
    return registerUser(RoleType.PARTICIPANT);
  }

  private UserAccount registerUser(RoleType roleType)
  {
    User user = new User();
    user.setEmail(EMAIL);
    user.setFirstName("Me");
    user.setLastName("Test");
    user.setMobile(MOBILE_NUMBER);
    user.setUserName(USER_NAME);
    user.setPassword(PASSWORD);
    UserAccount userAccount = null;
    switch (roleType)
    {
      case ORGANIZER:
        userAccount = userService.registerOrganizer(user);
        break;
      case SUPPORT:
        userAccount = userService.registerSupportUser(user);
        break;
      default:
        userAccount = userService.registerParticipant(user);
        break;
    }
    return userAccount;
  }
}
