package com.zhang._0425;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress2 {
	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.baidu.com");//根据url自动查找
			System.out.println(ia.getHostAddress());//获得ip地址
			System.out.println(ia.getHostName());//获得主机名
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
