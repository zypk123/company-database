package com.zhang._0413;

public class TestMan {
	
	public static void main(String[] args) {
		
		Man m = new Man();//创建Man对象，就是调用Man的无参构造方法
		m.eat();//调用方法
		m.setAge(22);//给属性赋值
		m.setName("张宇");//给属性赋值
		System.out.println(m.getName()+"今年"+m.getAge()+"岁！");//获得属性的值
		
		Man m1 = new Man("关羽",45);//创建Man对象，就是调用Man的带参构造方法
		m1.eat();
		System.out.println(m1.getName()+"今年"+m1.getAge()+"岁！");
	
	}
}
