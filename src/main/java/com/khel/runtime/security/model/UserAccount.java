package com.khel.runtime.security.model;

import java.util.Collection;
import java.util.HashSet;

import com.khel.runtime.security.type.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccount extends User implements UserDetails
{
  private static final long serialVersionUID = 1L;
  private Collection<SimpleGrantedAuthority> authorities;

  public UserAccount(User user)
  {
    super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobile(), user.getUserName(), user.getPassword(), user.getRoles());
    grantAutorities();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired()
  {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    // TODO Auto-generated method stub
    return true;
  }

  private void grantAutorities()
  {
    authorities = new HashSet<SimpleGrantedAuthority>();
    for (Role role : this.getRoles())
    {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
      for (Permission permission : role.getPermissions())
      {
        authorities.add(new SimpleGrantedAuthority(permission.name()));
      }
    }
  }

  @Override
  public String getUsername()
  {
    return getUserName();
  }
}
