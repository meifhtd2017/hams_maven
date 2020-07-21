package com.metime.syn;

public class SyncThread implements Runnable {

	private static int count;

	public SyncThread() {
		count = 0;
	}

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
			}
		}

	}

	public int getCount() {
		return count;
	}
}
