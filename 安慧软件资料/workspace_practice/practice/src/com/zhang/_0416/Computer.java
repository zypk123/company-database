package com.zhang._0416;

/**
 * 计算机类
 * 
 * @author zhangyu
 *
 */
public class Computer {

	private IMobileStorage imobilestorage;// 给IMobileStorage接口一个引用变量
	
	public Computer() {

	}

	public Computer(IMobileStorage imobilestorage) {
		super();
		this.imobilestorage = imobilestorage;
	}

	// 为IMobileStorage引用提供get/set方法
	public IMobileStorage getImobilestorage() {
		return imobilestorage;
	}

	public void setImobilestorage(IMobileStorage imobilestorage) {
		this.imobilestorage = imobilestorage;
	}

	/**
	 * 调用IMobileStorage中的读数据方法
	 */
	public void readData() {
		imobilestorage.read();
	}

	/**
	 * 调用IMobileStorage中的写数据方法
	 */
	public void writeData() {
		imobilestorage.write();
	}

}
