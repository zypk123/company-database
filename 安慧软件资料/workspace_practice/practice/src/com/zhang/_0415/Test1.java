package com.zhang._0415;

public class Test1 {
	public static void main(String[] args) {
		int a = 10;
		Integer i = new Integer(20);//Integer:int的包装类
		System.out.println(i);

		int b = i.intValue();
		System.out.println(b);

		Float f = new Float(3.1f);//Float:float的包装类
		System.out.println(f);

		float f1 = f.floatValue();
		System.out.println(f1);

		// jdk5新特性：自动装箱和自动拆箱（只能用于包装类中）
		// 自动装箱：把 基本类型 给 引用类型
		Integer in1 = 100;
		Integer in2 = 200;

		Double b1 = 3.1;
		Float f2 = 3.1f;

		// 自动拆箱：把 引用类型 给 基本类型
		int i1 = in1 + in2;
		System.out.println(i1);
	}

}
