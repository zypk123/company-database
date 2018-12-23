package com.zhang._0419;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestCopy2 {
	public static void main(String[] args) throws Exception {
		
		File src = new File("E:/11.png");
		File dest = new File("D:/11.png");
		
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dest);
		
		byte[] buff = new byte[1024];
		int len = 0;
		while(-1!=(len=fis.read(buff))){
			fos.write(buff, 0, len);
		}
		System.out.println("¸´ÖÆÍê±Ï");
		fos.close();
		fis.close();
	}
}
