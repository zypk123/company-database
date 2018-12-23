package com.zhang._0415;

import java.util.Arrays;

public class TestString3 {
	public static void main(String[] args) throws Exception {
		byte[] buff = {1,2,3};
		String s= new String(buff,"utf-8");//为String指定编码方式
		
		s = "zhangyu linwanyu  ";
		char c = s.charAt(4);//取指定位置的字符
		System.out.println(c);
		
		String s1 = "4zhangyu";
		String s2 = "3linwanyu";
		System.out.println(s1.compareTo(s2));//按照自然顺序比较字符串，返回数字（正数，负数，0）
		
		String s3 ="我是中国人";
		byte[] by= s3.getBytes();//将字符串转换成字节数组
		System.out.println(Arrays.toString(by));
		
		
		
		String s4 = "zhangyuzhangyuzhangyu";

		// indexOf:得到某个字符第一次出现的位置的索引
		System.out.println(s4.indexOf("z"));
		System.out.println(s4.indexOf("han"));

		// lastIndexOf:返回指定子字符串在此字符串中最后一次出现处的索引
		System.out.println(s4.lastIndexOf('z'));
		System.out.println(s4.lastIndexOf("yu"));

		String s5 = "linwanyu";
		String s6 = s5.replace('w', 'q');
		System.out.println(s6);

		// trim():返回字符串的副本，忽略前导空白和尾部空白。
		String s7 = "  z h a n g y u    ";
		String s8 = s7.trim();
		System.out.println(s8);

		// 问题：怎么去除中间的空格？
		// replace
		String s9 = s7.replace(" ", "");
		System.out.println(s9);

		// split:根据给定正则表达式的匹配拆分此字符串。

		String s10 = "zhangyu.linwanyu.yangchenlin";
		String[] str = s10.split("\\.");
		System.out.println(Arrays.toString(str));

		// substring:返回一个新的字符串，它是此字符串的一个子字符串。该子字符串从指定索引处的字符开始，直到此字符串末尾。
		String s11 = s6.substring(8);
		System.out.println(s11);

		// substring(int beginIndex,int endIndex):返回一个新字符串，它是此字符串的一个子字符串。
		// 该子字符串从指定的 beginIndex 处开始，直到索引 endIndex - 1 处的字符.
		String s12 = s6.substring(8, 16);
		System.out.println(s12);

		// valueOf:把其他 任意类型 的数值，变成字符串（静态方法，类名调用）
		// 等同于+"",只是比较正规。
		System.out.println(String.valueOf(12.3f));

	}

}
