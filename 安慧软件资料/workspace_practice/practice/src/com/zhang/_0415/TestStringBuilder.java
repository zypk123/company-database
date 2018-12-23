package com.zhang._0415;

public class TestStringBuilder {
	public static void main(String[] args) {
		
		/*StringBuilder和StringBuffer
		 * 1.StringBuffer线程安全，但是效率低，StringBuilder线程不安全,但是效率高。推荐使用StringBuilder。
		 * 2.都在java.lang包下。
		 * 3.StringBuilder比String拼接效率高。
		 */
		//+: 每次需要去询问常量池有没有这个字符串。
		// append:直接加。当大文本时候，效率明显高
		StringBuilder sb = new StringBuilder();
		sb.append(1).append("abc").append(true);
		System.out.println(sb);
	}
}
