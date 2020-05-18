package com.metime.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TestTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println(Thread.currentThread().getName() + i);
				}
			}
		},"A");
		CallableTest callableTest = new CallableTest();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callableTest);
		new Thread(futureTask).start();
		thread1.start();
		try {
			Integer integer = futureTask.get();
			System.out.println(integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}
