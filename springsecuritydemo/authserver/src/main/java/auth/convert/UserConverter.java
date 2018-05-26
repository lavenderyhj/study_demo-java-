package com.piggymetrics.auth.convert;

import com.piggymetrics.auth.domain.User;
import com.piggymetrics.piggyserviceapi.ConvertUtil;
import com.piggymetrics.serviceapi.users.UserModels;
import org.springframework.stereotype.Component;

/**
 * Created by yuhuijuan on 2018/3/21
 */
@Component
public class UserConverter {

	public UserModels.User convert(User user) {
		UserModels.User.Builder userBuilder = ConvertUtil.convert(user, UserModels.User.newBuilder());
		return userBuilder.build();
	}

	public User convert(UserModels.User user) {
		return ConvertUtil.convert(user, new User());
	}


}
