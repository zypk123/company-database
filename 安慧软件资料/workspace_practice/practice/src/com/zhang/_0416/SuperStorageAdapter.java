package com.zhang._0416;

/**
 * SuperStorage的适配器，实现IMobileStorage接口 把ra和wt方法转换成Read和Write方法
 * 
 * @author zhangyu
 *
 */
public class SuperStorageAdapter implements IMobileStorage {

	private SuperStorage superstorage; // 创建SuperStorage引用变量

	public SuperStorage getSuperstorage() {
		return superstorage;
	}

	public void setSuperstorage(SuperStorage superstorage) {
		this.superstorage = superstorage;
	}

	@Override
	public void read() {
		superstorage.ra(); // 将ra()转换成read()
	}

	@Override
	public void write() {
		superstorage.wt(); // 将wt()转换成write()
	}

}
