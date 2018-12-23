package com.zhang._0413;

/**
 * 抽象类abstract
 *
 */
public abstract class Shape{
	
	String name;
	
	public abstract double calcS(); //抽象方法,abstract可以省略
	
	public Shape(){
		System.out.println("Shape 构造方法");
	}
	public void printS(double d){
		System.out.println(name+"的面积："+d);
	}
}
