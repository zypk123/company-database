package com.zhang._0422;

/**
 * Thread线程类：继承Thread类，重写run方法
 * 
 * @author zhangyu
 *
 */
public class TestThread1 extends Thread {

	private int i;

	// 重写run方法，run方法的方法体就是线程执行体
	public void run() {
		for (; i < 10; i++) {
			System.out.println(this.getName() + ":" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + "             .." + i);//返回当前线程对象的名称
			if (i == 10) {
				System.out.println("--------------------------------------------");
				new TestThread1().start(); //启动线程
				new TestThread1().start(); //启动线程
				System.out.println("---------------------------------------------");
			}
		}
	}
}
