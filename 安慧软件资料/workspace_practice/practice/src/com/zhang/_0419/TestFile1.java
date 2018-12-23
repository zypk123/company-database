package com.zhang._0419;

import java.io.File;
import java.io.IOException;

public class TestFile1 {
	public static void main(String[] args) {
		
		File file = new File("E:/java.java"); //表示文件的指向，并不是创建文件
		try {
			boolean b = file.createNewFile(); //创建文件
			System.out.println("创建文件的状态:"+b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(file.exists());//判断文件是否存在
		
		System.out.println(file.getName());//得到文件名
		
		System.out.println(file.getAbsolutePath());//得到文件的绝对路径
		
		System.out.println(file.getPath());//得到相对路径
		
		/**
		 * 删除文件，若是文件夹，里面有文件的话，是不能被删除的
		 * 注意：不会走回收站的
		 */
		System.out.println(file.delete());
	}

}
