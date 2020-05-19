package com.metime.proxy;

import net.sf.cglib.proxy.Enhancer;
/**
 * 基于cglib的动态代理
 *实现MethodInterceptor拦截器
 */
public class CglibProxyTest {
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Cglibject.class);
		enhancer.setCallback(new HelloInterceptor());
		Cglibject create = (Cglibject)enhancer.create();
		create.hello("hello");
	}
	
}
