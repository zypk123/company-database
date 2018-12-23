package com.zhang._0624;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC_1 {

	public static void main(String[] args) throws Exception {

		// 加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 获取连接，得到Connection对象
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.240:1521:orcl", "trff_app","trff_app");

		// 获取Statement对象
		Statement statement = conn.createStatement();

		// Statement对象执行查询sql，返回ResultSet结果集对象
		String sql = "select * from t_user";
		ResultSet rs = statement.executeQuery(sql);

		// 遍历结果集
		while (rs.next()) {
			System.out.println(rs.getString("username")); // 输出结果
		}
		
		// 关闭资源
		rs.close();
		statement.close();
		conn.close();
	}
}
