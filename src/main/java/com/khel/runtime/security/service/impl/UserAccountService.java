package com.khel.runtime.security.service.impl;

import com.khel.runtime.security.dao.RoleDao;
import com.khel.runtime.security.dao.UserDao;
import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import com.khel.runtime.security.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserAccountService implements UserService
{

  @Autowired
  UserDao userDao;
  @Autowired
  RoleDao roleDao;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  /**
   * Load by username
   * 
   * Required method for Spring Security UserDetailService
   */
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    User user = userDao.findByUsername(username);
    if (user == null)
    {
      throw new UsernameNotFoundException("Unable to find username:"+username);
    }
    UserAccount userAccount = new UserAccount(user);
    return userAccount;
  }
  
  /**
   * Create a new UserAccount
   * 
   * @param user
   * @return
   */
  @Override
  @Transactional
  public UserAccount registerParticipant(User user)
  {
    user.setRoles(new HashSet<>(Arrays.asList(roleDao.findByName(RoleType.PARTICIPANT.name()))));
    return new UserAccount(saveUser(user));
  }

  /**
   * Create a new Organizer User
   *
   * @param user
   * @return
   */
  @Override
  @Transactional
  public UserAccount registerOrganizer(User user)
  {
    user.setRoles(new HashSet<>(Arrays.asList(roleDao.findByName(RoleType.ORGANIZER.name()))));
    return new UserAccount(saveUser(user));
  }

  /**
   * Create a new Support User
   *
   * @param user
   * @return
   */
  @Override
  @Transactional
  public UserAccount registerSupportUser(User user)
  {
    user.setRoles(new HashSet<>(Arrays.asList(roleDao.findByName(RoleType.SUPPORT.name()))));
    return new UserAccount(saveUser(user));
  }

  @Transactional
  private User saveUser(User user)
  {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userDao.save(user);
  }

  /**
   * Update the password for an account
   * 
   * This exists as a separate method to keep the password
   * encryption consistent
   * 
   * @param user
   * @param password
   */
  @Override
  @Transactional
  public void updateUserAccountPassword(User user, String password)
  {
    user = userDao.findOne(user.getId());
    user.setPassword(passwordEncoder.encode(password));
    userDao.save(user);
  }

}
