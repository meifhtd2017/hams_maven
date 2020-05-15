package com.metime.thread;

public class ThreadTest extends Thread{
	
	@Override
	public void run() {
		Thread.currentThread().setName("Thread线程");
		for (int i = 0; i < 20; i++) {
			System.out.println(ThreadTest.currentThread().getName() + i);
		}
	}
	
}
