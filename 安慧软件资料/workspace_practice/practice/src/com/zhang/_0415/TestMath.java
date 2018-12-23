package com.zhang._0415;

public class TestMath {
	public static void main(String[] args) {
		// 随机数
		// random()返回一个double值，[0.0,1.0)
		System.out.println(Math.random());

		// 产生一个1-10的随机整数
		int a = (int) ((Math.random() * 10) + 1);
		System.out.println(a);

		// 次幂pow(底，幂);
		System.out.println(Math.pow(2, 3));

		// 向上取整 ceil
		System.out.println(Math.ceil(12.1));

		// 向下取整floor
		System.out.println(Math.floor(12.9));

		// 四舍五入round
		System.out.println(Math.round(12.6));
		System.out.println(Math.round(12.4));

		// 绝对值abs
		System.out.println(Math.abs(-12.5));
		System.out.println(Math.abs(12.5));

		// 平方根sprt
		System.out.println(Math.sqrt(4.0));

		// 最大值，最小值 max和min
		System.out.println(Math.max(10, 20));

		System.out.println(Math.max(Math.max(10, 20), 30));

	}

}
