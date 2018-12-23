package com.zhang._0419;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestObjectInputStream {
	public static void main(String[] args) throws Exception, IOException {
		//ObjectInputStream:对象输入流--读数据
		File file = new File("c.txt");
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();//读对象
		System.out.println(obj);
		ois.close();
		fis.close();
	}
}
