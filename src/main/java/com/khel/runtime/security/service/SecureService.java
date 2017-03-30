package com.khel.runtime.security.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by RKasturi on 3/16/2017.
 */
public interface SecureService
{
  @PreAuthorize("authenticated")
  String secure();

  @PreAuthorize("hasAuthority('PARTICIPANT')")
  String authorized();

  @PreAuthorize("hasAuthority('ADMIN')")
  String denied();
}
