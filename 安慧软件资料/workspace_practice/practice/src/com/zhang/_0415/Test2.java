package com.zhang._0415;

public class Test2 {
	public static void main(String[] args) {
		// 将字符串转换成int类型：用Integer.parseInt()
		String s = "12345";
		int a = Integer.parseInt(s);
		System.out.println(a);

		s = "100000000000000";
		long l = Long.parseLong(s);
		System.out.println(l);

		s = "3.125f";
		float f = Float.parseFloat(s);
		System.out.println(f);

		// 将十进制数转换成其他进制数(注意返回String)
		// 二进制
		String s1 = Integer.toBinaryString(100);
		System.out.println(s1);

		// 十六进制
		s1 = Integer.toHexString(1000000);
		System.out.println(s1);

		// 八进制
		s1 = Integer.toOctalString(10000);
		System.out.println(s1);
		
		int b = 10;
		String ss = Integer.toString(b);//默认是十进制
		System.out.println(ss);

		ss = Integer.toString(b, 2);
		System.out.println(ss);//二进制

		ss = Integer.toString(b, 8);
		System.out.println(ss);//八进制

	}

}
