package com.khel.web.controller.player;

import com.khel.web.controller.security.SecuredController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
@PreAuthorize("hasAnyAuthority('PLAYER_HOME')")
public class PlayerHome extends SecuredController
{

  public String viewHomePage(Principal principal)
  {
    return "home";
  }
}
