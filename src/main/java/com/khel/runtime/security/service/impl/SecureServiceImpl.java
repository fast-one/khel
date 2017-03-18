package com.khel.runtime.security.service.impl;

import com.khel.runtime.security.service.SecureService;
import org.springframework.stereotype.Service;

/**
 * Created by RKasturi on 3/16/2017.
 */
@Service
public class SecureServiceImpl implements SecureService
{
  @Override
  public String secure()
  {
    return "Hello Security";
  }

  @Override
  public String authorized()
  {
    return "Hello World";
  }

  @Override
  public String denied()
  {
    return "Goodbye World";
  }
}

