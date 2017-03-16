package com.khel.service;

import com.khel.runtime.security.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserAuthenticationTest
{
  //expected exceptions
  @Rule
  public ExpectedException exception = ExpectedException.none();

  private MockMvc mockMvc;
  
  @Test
  @WithMockUser(username = "rkasturi",password = "password")
  public void testUserAuth() throws Exception
  {
    mockMvc.perform(get("/home")).andExpect(status().is(200));
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