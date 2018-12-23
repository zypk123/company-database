package com.zhang._0416;
/**
 * U盘
 * 实现了IMobileStorage接口
 * 重写了自己特有的数据交互功能
 * @author zhangyu
 *
 */
public class FlashDisk implements IMobileStorage {

	@Override
	public void read() {
		System.out.println("U盘读数据");
	}

	@Override
	public void write() {
		System.out.println("U盘写数据");
	}

}
