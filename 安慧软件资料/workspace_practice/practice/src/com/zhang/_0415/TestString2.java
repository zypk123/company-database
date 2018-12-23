package com.zhang._0415;

public class TestString2 {
	public static void main(String[] args) {
		
		/**
		 * 字符串的拼接：+
		 * 任何类型与字符串拼接都会变成字符串
		 */
		String s1 = 10+"abc";
		System.out.println(s1);//10abc
		
		String s2 = true+"abc";
		System.out.println(s2);//trueabc
		
		String s3 = 1+2+'0'+"a"+2+3;
		System.out.println(s3);//51a23('0'：字符类型0会被转换成ASCII为48)

	}

}
