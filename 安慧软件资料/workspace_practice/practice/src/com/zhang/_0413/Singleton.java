package com.zhang._0413;

/**
 * 单例模式：类在内存中只存在一个对象
 */
public class Singleton {
	
	/**
	 * 饿汉式：在类初始化时，就自己实例化创建对象
	 */
	
	//构造方法私有化(只有在本类可以创建对象)，这样就不能创建对象了
	private Singleton(){
		
	}
	//在本类中创建的唯一的一个对象
	//private修饰是为了不让外界直接访问；由于下面的方法用了static，静态只能访问静态，所以要加static修饰
	private static Singleton s = new Singleton();
	
	/**
	 * 方法用来返回本类创建的唯一的一个对象
	 * 因为构造方法被私有化了，所以加static修饰，这样外界可以通过类名访问该方法
	 */
	public static Singleton getInstance(){
		
		return s;
	}
}
