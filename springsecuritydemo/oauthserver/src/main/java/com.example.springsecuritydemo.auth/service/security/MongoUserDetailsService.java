package com.example.springsecuritydemo.auth.service.security;

import com.example.springsecuritydemo.auth.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = new User();
		user.setUsername(username);
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("123456"));

		return user;
	}
}
