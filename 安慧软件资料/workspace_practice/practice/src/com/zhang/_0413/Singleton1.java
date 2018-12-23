package com.zhang._0413;

/**
 * 单例模式
 */
public class Singleton1 {

	/**
	 * 懒汉式：在调用的时候才去实例化创建对象
	 */

	// 构造方法私有化，不给外界创建对象
	private Singleton1() {

	}

	// 先声明一个对象，后面用的时候再实例化
	private static Singleton1 s = null;

	/*public static Singleton1 getInstance() {
		if (s == null) {
			s = new Singleton1();
		}
		return s;
	}*/
	
	//懒汉式没有考虑到线程的安全问题，所以可以加以优化，通过synchronized关键字，实现同步锁
	public synchronized static Singleton1 getInstance() {
		if (s == null) {
			s = new Singleton1();
		}
		return s;
	}
}
