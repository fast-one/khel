package com.khel.runtime.security.service.impl;

import com.khel.runtime.security.dao.UserDao;
import com.khel.runtime.security.model.User;
import com.khel.runtime.security.model.UserAccount;
import com.khel.runtime.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService implements UserService
{

  @Autowired
  UserDao userDao;
  
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
  public UserAccount createNewUserAccount(User user)
  {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return new UserAccount(userDao.save(user));
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
    user.setPassword(passwordEncoder.encode(password));
    userDao.save(user);
  }

}
