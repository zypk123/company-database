package com.zhang._0413;

public class AndroidPhone extends Phone {

	String name = "子类的名字";

	String version; //版本号
	
	public void takePhoto() {
		System.out.println("拍照");
	}

	// 方法重写
	public void call() {
		System.out.println("安卓手机打电话");
	}

	// 方法重写
	public void sms() {
		System.out.println("安卓手机发短信");
	}

}