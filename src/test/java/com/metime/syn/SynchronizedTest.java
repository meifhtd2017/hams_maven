package com.metime.syn;

public class SynchronizedTest {
	
	public static void main(String[] args) {
		//方式一：传入的是相同的对象，则锁是互斥的
		SyncThread syncThread = new SyncThread();
		new Thread(syncThread).start();
		new Thread(syncThread).start();
		//方式二：传入的是两个不同的对象，所以每个线程获取的是自己对象的锁，不是同一个对象的锁。不存在互斥
		/*new Thread(new SyncThread(),"A").start();
		new Thread(new SyncThread(),"B").start();*/
	}
	
}
