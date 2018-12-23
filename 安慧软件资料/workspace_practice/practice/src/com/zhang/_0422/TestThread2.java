package com.zhang._0422;

/**
 * Thread线程类：实现Runnable接口，实现run方法
 * 
 * @author zhangyu
 *
 */
public class TestThread2 implements Runnable {

	private int i;

	public void run() {
		for (; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + "             .." + i);
			if (i == 10) {
				TestThread2 t = new TestThread2();
				// 通过new Thread（ Runable target,String name）来创建新线程
				new Thread(t, "线程1").start();
				new Thread(t, "线程2").start();
			}
		}
	}
}