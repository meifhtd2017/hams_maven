package com.metime.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.metime.login.controller.LoginController;

public class Test1 implements Test2<String>{
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("");
	
	@Test
	public void test1(){
		Object bean = ioc.getBean("dataSource");
		
	}
	
}
