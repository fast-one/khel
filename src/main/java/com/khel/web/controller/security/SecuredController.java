package com.khel.web.controller.security;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This class should be extended by all controller
 * classes. It denies access by default. The subclass
 * will be forced to define it's own access rules.
 * 
 * @author rkasturi
 *
 */
@PreAuthorize("hasAuthority('NOT_GRANTED')")
public class SecuredController {}
