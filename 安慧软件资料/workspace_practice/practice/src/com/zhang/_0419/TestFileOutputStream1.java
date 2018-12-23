package com.zhang._0419;

import java.io.FileOutputStream;

public class TestFileOutputStream1 {

	// FileOutputStream:字符输出流 ----写数据
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("b.txt", true);// 会自动创建文件,true表示可以追加
		fos.write("zhangyu".getBytes());// 写入，因为参数是字节类型，所以用getByte方法
		fos.close();
	}
}
