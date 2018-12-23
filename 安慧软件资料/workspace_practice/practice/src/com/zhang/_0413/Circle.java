package com.zhang._0413;

public class Circle extends Shape {

	double r;

	// 构造带参构造的作用是为了不能使用无参构造
	// 这样就必须给属性赋值
	public Circle(double r) {
		// super(); //调用父类的无参构造
		this.r = r;
		name = "圆";
	}
	
	/**
	 * 重写抽象方法calcS()
	 */
	@Override
	public double calcS() {
		return 3.14 * r * r;
	}
}
