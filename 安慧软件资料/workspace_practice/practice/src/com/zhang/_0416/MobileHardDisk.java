package com.zhang._0416;

/**
 * 移动硬盘
 * 实现了IMobileStorage接口
 * 重写了自己特有的数据交互功能
 * @author zhangyu
 *
 */
public class MobileHardDisk implements IMobileStorage {

	@Override
	public void read() {
		System.out.println("移动硬盘读数据");
	}

	@Override
	public void write() {
		System.out.println("移动硬盘写数据");
	}

}
