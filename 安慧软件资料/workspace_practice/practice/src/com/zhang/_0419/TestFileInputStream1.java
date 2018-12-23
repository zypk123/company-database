package com.zhang._0419;

import java.io.File;
import java.io.FileInputStream;

public class TestFileInputStream1 {
	public static void main(String[] args) throws Exception {
		
		//FileInputStream:字节输入流，读取数据
		File file = new File("a.txt");
		FileInputStream fis  = new FileInputStream(file);
		byte[] buff = new byte[1024];//创建字节数组
		while(fis.available()>0){ //返回值大于0表示数据还没有读取完
			int len = fis.read(buff);
			System.out.println(new String(buff,0,len));
		}
		fis.close();
	}
}
