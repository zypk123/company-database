package com.zhang._0425;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket客户端client
 * @author zhangyu
 *
 */
public class C_Socket {

	public static void main(String[] args) {
		new Thread(new c_start()).start(); //启动客服端
	}
}
/**
 * 客户端线程类
 * @author zhangyu
 *
 */
class c_start implements Runnable {

	public void run() {

		BufferedWriter o_b; //高效字符输出流

		try {
			System.out.println("socket客户端已启动......");
			Socket s = new Socket("127.0.0.1", 8080);  //创建一个Socket对象，并将其绑定在本机的8080端口上
			o_b = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));//s.getOutputStream():得到输出流
			System.out.println("请输入你需要传输的内容:");
			Scanner s_c = new Scanner(System.in);
			o_b.write("客户端-传入：" + s_c.next());
			o_b.flush();
			o_b.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}