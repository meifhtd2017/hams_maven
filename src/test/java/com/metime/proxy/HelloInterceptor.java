package com.metime.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class HelloInterceptor implements MethodInterceptor{

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("q");
		Object invokeSuper = proxy.invokeSuper(obj, args);
		System.out.println("h");
		return invokeSuper;
	}

}
