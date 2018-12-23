package com.zhang._0415;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSdf1 {
	public static void main(String[] args) {
		/**
		 * SimpleDateFormat:解析(parse)，格式化(format)时间
		 */
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss E"); //applyPattern:按照这个模式解析
		String s = sdf.format(new Date());	//format:格式化
		System.out.println(s);
		
		System.out.println("----------------------");
		sdf.applyPattern("yyyy年MM月dd日  HH:mm:ss");
		String s1 = sdf.format(new Date());	//format:格式化
		System.out.println(s1);
	}
}
