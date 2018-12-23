package com.zhang._0422;

public class MyThread {
	public static void main(String[] args) {
		Thread t = Thread.currentThread();// 得到当前的线程
		System.out.println("当前的线程是：" + t);
		t.setName("MyThread");
		System.out.println("当前的线程是：" + t);
	}
}
