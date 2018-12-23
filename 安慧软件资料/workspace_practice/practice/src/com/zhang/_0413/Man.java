package com.zhang._0413;

/**
 * 人类
 * 标准的javabean类
 *
 */
public class Man {

	private String name;// 姓名属性
	private int age;// 年龄属性

	public Man() { // 无参构造

	}

	public Man(String name, int age) { // 带参构造
		this.name = name;
		this.age = age;
	}

	public void eat() { 	// 吃饭行为（方法）

		System.out.println("吃饭");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
