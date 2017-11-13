package com.iotek.ht.db.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import com.iotek.ht.util.Tools;

/**
 * 一个基类 与数据库建立连接以及发送语句的操作
 * 
 * @author zhangjiaqi
 * 
 */
public class BaseDao {
	private static Properties prop = null;

	/**
	 * 加载数据库驱动和加载配置文件
	 */
	static {
		try {
			prop = Tools.myLoad();
			Class.forName(prop.getProperty("CLASSNAME"));
		} catch (ClassNotFoundException e) {
			System.out.println("未找到驱动");
		} catch (IOException e) {
			System.out.println("配置文件错误");
		}
	}

	/**
	 * 获得数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		return DriverManager.getConnection(prop.getProperty("PROTOCAL")
				+ prop.getProperty("DBURL"));
	}

	/**
	 * 替换0~n个占位符
	 * 
	 * @param params
	 * @param ps
	 * @throws SQLException
	 */
	private void fillPs(List<Object> params, PreparedStatement ps)
			throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
	}

	/**
	 * 发送sql语句获得一个结果集
	 * 
	 * @param sql
	 * @param params
	 * @param conn
	 * @param ps
	 * @return
	 */
	public ResultSet sendBackWithQuery(String sql, List<Object> params,Connection conn, PreparedStatement ps) {
		ResultSet rs = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			fillPs(params, ps);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 发送sql语句返回改变的行数
	 * 
	 * @return
	 */
	public boolean sendBackWithNums(String sql, List<Object> params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int num = 0;// 接收返回的改变的行数
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			fillPs(params, ps);
			num = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, null);
		}

		return num > 0;

	}

	/**
	 * 关闭连接及释放资源
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	protected void closeAll(Connection conn, Statement ps, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
