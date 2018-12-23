package com.zhang._0416;
/**
 * MP3
 * 实现了IMobileStorage接口
 * 重写了自己特有的数据交互功能
 * @author zhangyu
 *
 */
public class MP3Player implements IMobileStorage{

	@Override
	public void read() {
		System.out.println("MP3读数据");
	}

	@Override
	public void write() {
		System.out.println("MP3写数据");
	}
	
	public void PlayMusic(){
		System.out.println("MP3放音乐");
	}

}
