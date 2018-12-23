package com.zhang._0419;

import java.io.File;

public class TestFile3 {
	public static void main(String[] args) {
		
		/**
		 * 创建文件夹：mkdir--必须保证父级目录存在
		 * 		 mkdirs--不必要保证父级目录存在，可以直接创建全部的目录结构（通常用这个）
		 */
		File file = new File("E:/java/java");
//		boolean b = file.mkdir();
//		System.out.println(b); //false
		boolean b1 = file.mkdirs();
		System.out.println(b1);//true
	}
}
