package com.khel.service;

import com.khel.runtime.security.model.Role;
import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceAuthorizationTest
{
  public static final String USER_NAME = "testme";
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
    assertThat(userAccount.getUsername()).isEqualTo(USER_NAME);
  }

 private User initUser()
  {
    User user = new User();
    user.setEmail("test@khel.com");
    user.setFirstName("Test");
    user.setLastName("Tester");
    user.setMobile("1234567890");
    user.setUserName(USER_NAME);
    user.setPassword("aata");
    Role role = new Role();
    role.setName("TestRole");
    role.setDescription("Some Description");
    role.setPermissions(Arrays.asList(Permission.ADD_USER));
    user.setRoles(Arrays.asList(role));
    return user;
  }
}