package com.zhang._0419;

import java.io.FileWriter;

public class TestWriter {
	public static void main(String[] args) throws Exception {
		
		/**
		 * 输入流：读取数据，输出流：写数据
		 * 字节流输入流：InputStream 字节输出流:OutPutStream
		 * 字符输入流：Reader 字符输出流：Write
		 */
		//A.创建了a.txt文件
		//B.创建了字符输出流对象fw,并且将该对象指向文件
		FileWriter fw = new FileWriter("a.txt");
		fw.write("张宇");//向文件写数据，注意此时数据没有进去，需要flush一下才可以
		fw.flush();//刷新
		fw.close();//关流，释放资源
	}
}
