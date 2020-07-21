package com.metime.login.service.impl;

import org.springframework.stereotype.Service;

import com.metime.bean.User;
import com.metime.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	public void saveUser(User user) {
		user.setName("aa");
		System.out.println(user.getName());
		user.setAge("20");
		System.out.println(user);
	}

}
