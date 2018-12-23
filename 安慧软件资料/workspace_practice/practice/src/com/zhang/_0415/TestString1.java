package com.zhang._0415;

public class TestString1 {
	public static void main(String[] args) {
		
		 String s1 = "abc"; //在字符串常量池创建一个"abc"对象，并保存起来
		 String s2 = "abc"; //直接将字符串常量池中的"abc"引用赋给s2
		 System.out.println(s2==s1);//true
		 
		 String s3 = "def";//在字符串常量池创建一个"def"对象，并保存起来
		 String s4 = new String("def");//创建两个对象，new String():在堆中创建一个对象，将引用赋给s4，"def":在字符串常量池创建一个对象
		 System.out.println(s3==s4);//false
		 
		 /**
		  * ==:比较地址值
		  * equals：字符串的equals被重写了，比较字面值
		  * 一般来说，都会重写equals方法，来自的object的equals不是比较字面值的，必须要重写
		  */
		 System.out.println(s3.equals(s4));//true
	}

}
