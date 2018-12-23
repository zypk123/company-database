package com.zhang._0419;

import java.io.FileReader;

public class TestReader {
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("a.txt");
		int ch = 0;
		while((ch=fr.read())!=-1){ //返回-1说明没有数据了
			System.out.println(ch);
		}
		fr.close();
	}
}
