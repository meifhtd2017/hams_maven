package com.metime.thread;

public class RunnableTest implements Runnable{

	public void run() {
		synchronized (RunnableTest.class) {
			for (int i = 0; i < 10; i++) {
				try {
					RunnableTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + i);
			}
		}
	}
}
