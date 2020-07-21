package com.metime.thread;

public class RunnableTest implements Runnable {

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
