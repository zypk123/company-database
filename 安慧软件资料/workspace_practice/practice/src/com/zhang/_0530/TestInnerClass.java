package com.zhang._0530;

public class TestInnerClass {
	public static void main(String[] args) {

		// 匿名内部类，重写父类的方法，本质上是子类的匿名对象
		new Person() {

			@Override
			public void eat() {
				System.out.println("吃东西");
			}
		}.eat();
	}
}
