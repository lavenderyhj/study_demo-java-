package com.piggymetrics.auth.service;

import com.piggymetrics.auth.convert.UserConverter;
import com.piggymetrics.auth.domain.User;
import com.piggymetrics.auth.repository.UserRepository;
import com.piggymetrics.serviceapi.users.UserApi;
import com.piggymetrics.serviceapi.users.UserApiOuterClass;
import com.ppdai.framework.raptor.spring.annotation.RaptorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RaptorService
public class UserServiceImpl implements UserApi {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

//	@Override
//	public void create(User user) {
//
//		User existing = repository.findOne(user.getUsername());
//		Assert.isNull(existing, "user already exists: " + user.getUsername());
//
//		String hash = encoder.encode(user.getPassword());
//		user.setPassword(hash);
//
//		repository.save(user);
//
//		log.info("new user has been created: {}", user.getUsername());
//	}

	@Override
	public UserApiOuterClass.NewUserResponse create(UserApiOuterClass.NewUserRequest newUserRequest) {
		User newUser = new UserConverter().convert(newUserRequest.getUser());

		User existing = repository.findOne(newUser.getUsername());
		Assert.isNull(existing, "user already exists: " + newUser.getUsername());

		String hash = encoder.encode(newUser.getPassword());
		newUser.setPassword(hash);

		repository.save(newUser);

		log.info("new user has been created: {}", newUser.getUsername());

		return null;
	}
}
