package com.zhang._0415;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSdf2 {
	public static void main(String[] args) throws Exception {
		String s = "20150808160520";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyyMMddHHmmss");// 这里的模式要和被解析的格式相同
		Date date = sdf.parse(s);// parse：解析s的格式
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss E");// 要解析成的模式
		System.out.println(sdf.format(date));// 格式化
	}
}
