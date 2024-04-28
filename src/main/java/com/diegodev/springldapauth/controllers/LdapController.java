package com.diegodev.springldapauth.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Spring LDAP Auth";
    }

    @GetMapping("/getUserDetails")
    public String getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean accountNonExpired = userDetails.isAccountNonExpired();
        return "Username: " + userDetails.getUsername() +
                "\n Account Non Expired: " + accountNonExpired;
    }
}
