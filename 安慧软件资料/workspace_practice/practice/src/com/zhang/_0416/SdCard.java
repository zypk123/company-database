package com.zhang._0416;

/**
 * 新来的一个存储设备SD卡
 * 只需要将其实现IMobileStorage就ok
 * @author zhangyu
 *
 */
public class SdCard implements IMobileStorage {

	@Override
	public void read() {
		System.out.println("SD卡读数据");
	}

	@Override
	public void write() {
		System.out.println("SD卡写数据");
	}

}
