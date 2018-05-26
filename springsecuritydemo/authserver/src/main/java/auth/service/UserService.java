package com.piggymetrics.auth.service;

import com.piggymetrics.auth.domain.User;
import com.ppdai.framework.raptor.annotation.RaptorInterface;


public interface UserService {

	void create(User user);

}
