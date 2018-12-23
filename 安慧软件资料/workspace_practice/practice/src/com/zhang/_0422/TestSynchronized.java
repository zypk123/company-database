package com.zhang._0422;

import java.util.Arrays;

public class TestSynchronized {

	int[] a = new int[3];

	public void set(int i) {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " start");
			a[0] = i;
			a[1] = i;
			a[2] = i;
			System.out.println(Thread.currentThread().getName() + " end");
		}
	}

	public void print() {
		System.out.println(Arrays.toString(a));
	}

	public static void main(String[] args) {
		final TestSynchronized ts = new TestSynchronized();
		Thread t1 = new Thread() {
			public void run() {
				ts.set(1);
			};
		};
		Thread t2 = new Thread() {
			public void run() {
				ts.set(2);
			};
		};
		t1.start();
		t1.setName("t1");
		t2.start();
		t2.setName("t2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ts.print();
	}
}
