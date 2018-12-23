package com.zhang._0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket服务器server
 * @author zhangyu
 *
 */
public class S_Socket {
	public static void main(String[] args) {
		new Thread(new s_start()).start(); //启动服务端
	}
}

/**
 * 服务端线程类
 * @author zhangyu
 *
 */
class s_start implements Runnable {

	public void run() {
		BufferedReader re; //高效字符输入流
		try {
			System.out.println("socket服务器已启动......");
			ServerSocket socket = new ServerSocket(8080); //创建ServerSocket对象，并绑定到8080端口
			Socket s = socket.accept();//监听并接受socket的连接，返回Socket
			re = new BufferedReader(new InputStreamReader(s.getInputStream()));//s.getInputStream():返回Socket的输入流
			String re_s = re.readLine();//读数据
			while (re_s != null) {
				System.out.println(re_s);
				re_s = re.readLine();
			}
			re.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
