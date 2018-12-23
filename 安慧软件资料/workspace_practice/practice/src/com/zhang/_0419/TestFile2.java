package com.zhang._0419;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestFile2 {
	public static void main(String[] args) {
		File file = new File("D:/eclipse");

		File[] arr = file.listFiles(); // 获取所有文件的，返回文件数组

		// System.out.println(Arrays.toString(arr));

		/*
		 * for (File f : arr) { //遍历File数组 System.out.println(f); }
		 */

		List<File> list = Arrays.asList(arr);
		for (File f : list) {
			System.out.println(f);
		}
	}
}
