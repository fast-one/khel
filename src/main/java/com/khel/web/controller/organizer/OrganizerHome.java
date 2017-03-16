package com.khel.web.controller.organizer;

import com.khel.web.controller.security.SecuredController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/organizer/home")
@PreAuthorize("hasAnyAuthority('ORGANIZER_HOME')")
public class OrganizerHome extends SecuredController
{

  public String viewHomePage(Principal principal)
  {
    return "home";
  }
}
