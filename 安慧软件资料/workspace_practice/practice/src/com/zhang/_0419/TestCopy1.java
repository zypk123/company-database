package com.zhang._0419;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestCopy1 {
	
	public static void main(String[] args) throws Exception {
		//复制图片
		File src = new File("F:/11.png");//源文件
		File dest = new File("E:/11.png");//目的地
		
		FileInputStream fis = new FileInputStream(src);//读数据
		FileOutputStream fos = new FileOutputStream(dest);//写数据
		
		byte[] buff = new byte[1024];
		int len =0;
		while(-1!=(len=fis.read(buff))){ //读
			fos.write(buff, 0, len); //写
		}
		System.out.println("-------图片复制完毕--------");
		fos.close();
		fis.close();
	}
}
