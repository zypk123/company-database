package com.zhang._0413;

public class Phone {

	String name = "父类的名字";
	double price;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void call() {
		System.out.println("打电话");
	}

	public void sms() {
		System.out.println("发短信");

	}

}
