package com.example.gateway.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/accounts")
public class AccountController {
//    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public String getCurrentAccount(Principal principal) {
        return principal.getName();
    }


}
