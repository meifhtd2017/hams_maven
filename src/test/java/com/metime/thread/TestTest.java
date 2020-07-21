package com.metime.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class TestTest {
	
	@Test
	public void test1(){
		Thread thread = new Thread(new RunnableTest(),"AA");
		Thread thread1 = new Thread(new RunnableTest(),"BB");
	}
	
	@Test
	public void test2(){
		ExecutorService executorService = Executors.newCachedThreadPool();
		
	}
	
	public static void main(String[] args) {
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
