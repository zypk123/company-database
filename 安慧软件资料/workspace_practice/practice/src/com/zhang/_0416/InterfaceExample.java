package com.zhang._0416;

/**
 * 测试接口
 * 
 * @author zhangyu
 *
 */
public class InterfaceExample {
	public static void main(String[] args) {
		Computer computer = new Computer();
		IMobileStorage flashdisk = new FlashDisk(); // U盘对象
		IMobileStorage mp3player = new MP3Player();// MP3对象
		IMobileStorage mobileharddisk = new MobileHardDisk();// 移动硬盘对象

		IMobileStorage sdcard = new SdCard();// 新添加的SD卡

		computer.setImobilestorage(flashdisk);
		computer.readData(); // U盘读数据
		computer.writeData();// U盘写数据

		computer.setImobilestorage(mp3player);
		computer.readData(); // Mp3盘读数据
		computer.writeData();// MP3写数据

		computer.setImobilestorage(mobileharddisk);
		computer.readData(); // 移动硬盘读数据
		computer.writeData();// 移动硬盘写数据

		computer.setImobilestorage(sdcard);
		computer.readData();
		computer.writeData();

		SuperStorage superstorage = new SuperStorage(); // 新设备对象
		SuperStorageAdapter superstorageadapter = new SuperStorageAdapter();// 新设备适配器对象
		superstorageadapter.setSuperstorage(superstorage);// 转换过程
		computer.setImobilestorage(superstorageadapter);
		computer.readData();
		computer.writeData();

	}

}
