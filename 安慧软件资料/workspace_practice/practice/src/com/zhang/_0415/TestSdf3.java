package com.zhang._0415;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSdf3 {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String s = sdf.format(date); //format：按照构造方法后的模式格式化Date对象，返回的是字符串
		System.out.println(s);
		
		String s1 = "201604151609";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = sdf1.parse(s1); //parse：将字符串转换成Data对象
		System.out.println(d);
		
	}

}
