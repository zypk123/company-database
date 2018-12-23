package com.zhang._0413;

public class TestSingleton {
	
	public static void main(String[] args) {
		
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		//==用来判断两个引用的地址值是否相等
		System.out.println(s1==s2);	//true
		
	}

}
