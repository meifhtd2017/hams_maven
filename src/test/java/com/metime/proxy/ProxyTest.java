package com.metime.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk基于接口实现动态代理
 * @author hurui
 *
 */
public class ProxyTest {
	public static void main(String[] args) {
		//创建实现类
		Subject subject = new SubjectImpl();
		//创建代理类
		InvocationHandler handler = new SubjectProxy(subject);
		//获取代理对象。如果实现类实现多个接口，第二个参数改为数组
		Subject object = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
		
		object.sayHello("hello");
		object.sayBye("Bye");
		
	}
}
