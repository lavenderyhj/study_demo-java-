package com.example.springsecuritydemo.auth.service;

import com.example.springsecuritydemo.auth.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpll implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public void create(User user) {

        log.info("new user has been created: {}", user.getUsername());
    }


}
