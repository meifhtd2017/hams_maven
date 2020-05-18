package com.metime.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.metime.login.controller.LoginController;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
		System.out.println(ioc);
		LoginController bean = ioc.getBean(LoginController.class);
		String login = bean.login();
		System.out.println(login);
	}
	
}
