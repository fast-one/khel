package com.khel.web.controller.support;

import com.khel.web.controller.security.SecuredController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/support/home")
@PreAuthorize("hasAnyAuthority('SUPPORT_HOME')")
public class SupportHome extends SecuredController
{

  public String viewHomePage(Principal principal)
  {
    return "home";
  }
}
