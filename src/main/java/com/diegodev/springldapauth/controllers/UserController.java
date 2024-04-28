package com.diegodev.springldapauth.controllers;

import com.diegodev.springldapauth.entities.LdapUser;
import com.diegodev.springldapauth.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    LdapService ldapService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/addUserForm")
    public String addUserForm(Model model) {
        model.addAttribute("ldapUser", new LdapUser());
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String addUser(LdapUser ldapUser) {
        ldapService.addUser(ldapUser);
        return "successUserCreated";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        model.addAttribute("ldapUsers", ldapService.getAllUsers());
        return "userList";
    }
}
