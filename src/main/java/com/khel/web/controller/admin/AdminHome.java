package com.khel.web.controller.admin;

import com.khel.web.controller.security.SecuredController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/home")
@PreAuthorize("hasAnyAuthority('ADMIN_HOME')")
public class AdminHome extends SecuredController
{

  public String viewHomePage(Principal principal)
  {
    return "home";
  }
}
