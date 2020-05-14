package com.metime.runnable;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<Integer>{

	public Integer call() throws Exception {
		Thread.currentThread().setName("Callable线程");
		int i = 0;
		for (int j = 0; j < 20; j++) {
			System.out.println(Thread.currentThread().getName() + (i++));
		}
		return i;
	}

	
	

}
