package com.zhang._0413;

public class TestPhone {
	
	public static void main(String[] args) {
		
		/*AndroidPhone ap = new AndroidPhone();
		System.out.println(ap.name);
		ap.call();
		ap.sms();*/
		
		//多态写法
		Phone p = new AndroidPhone();   //多态，从右到左理解，安卓手机是手机
		System.out.println(p.name); //父类的名字----属性不存在重写
		p.call();//安卓手机打电话-----方法实现了重写，打印的是子类重写的方法。
		p.sms();
		
		//如果非要调用子类的属性，可以采用，对象转型
		
		AndroidPhone ap = (AndroidPhone)p;//对象转型
		System.out.println(ap.name);//------子类的名字
		
		//判断一个对象是不是一个类的实例：instanceof
		System.out.println(p instanceof Phone);//true
		System.out.println(p instanceof AndroidPhone);//true
		System.out.println(p instanceof Object);//true----任何对象都继承自object，都是Object的一个实例
	}
}
