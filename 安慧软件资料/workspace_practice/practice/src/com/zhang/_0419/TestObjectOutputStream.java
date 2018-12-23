package com.zhang._0419;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TestObjectOutputStream {
	public static void main(String[] args) throws Exception {
		//ObjectOutputStream:对象流（写入对象）
		File file = new File("c.txt");
		FileOutputStream fis = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fis);
		oos.writeObject(new Student()); //写入的对象类必须实现序列化接口
		oos.close();
		fis.close();
	}
}
