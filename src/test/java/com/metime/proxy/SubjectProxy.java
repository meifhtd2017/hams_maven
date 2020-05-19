package com.metime.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectProxy implements InvocationHandler{
	
	private Subject subject;
	
	public SubjectProxy(Subject subject){
		this.subject = subject;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("代理方法执行前");
		Object invoke = method.invoke(subject, args);
		System.out.println("代理方法执行后");
		return invoke;
	}

}
