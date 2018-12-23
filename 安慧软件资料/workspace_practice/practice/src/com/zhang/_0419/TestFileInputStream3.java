package com.zhang._0419;

import java.io.File;
import java.io.FileInputStream;

public class TestFileInputStream3 {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("a.txt"));
		byte[] buff = new byte[1024];
		int len = 0;
		while (-1 != (len = fis.read(buff))) {
			System.out.println(new String(buff, 0, len));
		}
		fis.close();
	}
}
