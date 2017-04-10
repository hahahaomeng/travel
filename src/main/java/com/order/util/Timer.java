package com.order.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Timer {
	private static final String url = "jdbc:mysql://112.74.46.101:3306/parknshop?characterEncoding=utf8";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "jingxin.sjx";

	private Connection conn = null;
	private PreparedStatement pst = null;

	public void doTimer() {
		System.out.println("timer");
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			String sql = "update orders set state=3 where TIMESTAMPDIFF(SECOND,createDate,DATE_FORMAT(NOW(),"
					+ "'%Y-%m-%d %H:%i:%s'))>86400 and state=2";
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pst) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (null != conn) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
