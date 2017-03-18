package com.khel.runtime.security.service;

import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by RKasturi on 3/16/2017.
 */
public interface UserService extends UserDetailsService
{
  @PreAuthorize("hasAuthority('ADD_USER')")
  UserAccount createNewUserAccount(User user);

  @PreAuthorize("hasAuthority('UPDATE_USER_PASSWORD')")
  void updateUserAccountPassword(User user, String password);
}
