package com.diegodev.springldapauth.controllers;

import com.diegodev.springldapauth.entities.LdapUser;
import com.diegodev.springldapauth.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LdapController {
    @Autowired
    LdapService ldapService;

    @GetMapping("/getUserDetails")
    public String getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean accountNonExpired = userDetails.isAccountNonExpired();
        return "Username: " + userDetails.getUsername() +
                "\n Account Non Expired: " + accountNonExpired;
    }

    @GetMapping("/getAllUsers")
    public List<LdapUser> getAllUsers() {
        return ldapService.getAllUsers();
    }

    @GetMapping("/getUserByUid/{uid}")
    public LdapUser getUserByUid(@PathVariable String uid) {
        return ldapService.getUserByUid(uid);
    }

    @GetMapping("/deleteByUid/{uid}")
    public String deleteUserByUid(@PathVariable String uid) {
        ldapService.deleteUserByUid(uid);
        return "User deleted successfully";
    }

}
