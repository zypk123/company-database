package com.zhang._0425;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress:这个类表示IP地址
 * @author zhangyu
 *
 */
public class TestInetAddress1 {
	public static void main(String[] args) {
		InetAddress ia = null;
		try {
			 ia = InetAddress.getLocalHost();//返回本地主机
			 System.out.println(ia);//---------USER-20160318NC/192.168.10.251
			 System.out.println(ia.getHostName());//主机名---USER-20160318NC
			 System.out.println(ia.getHostAddress());//IP地址----192.168.10.251
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
