package com.metime.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metime.bean.User;
import com.metime.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(value="/toLoginPage",method=RequestMethod.POST)
	public String login(User user){
		loginService.saveUser(user);
		return "nihao";
	}
	
}
