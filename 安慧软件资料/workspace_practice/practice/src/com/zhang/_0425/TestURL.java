package com.zhang._0425;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL类：浏览器访问地址的url 在java.net.url包下
 * 
 * @author zhangyu
 *
 */
public class TestURL {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com/index.jsp");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(url.getProtocol());// 获取协议名
		System.out.println(url.getPort());// 获取端口号，没有测返回-1
		System.out.println(url.getDefaultPort());// 获取默认得端口号，80端口是默认得端口号
		System.out.println(url.getPath());// 获取url的路径部分----/index.jsp
	}
}
