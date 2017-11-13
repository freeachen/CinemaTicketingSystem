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
 * һ������ �����ݿ⽨�������Լ��������Ĳ���
 * 
 * @author zhangjiaqi
 * 
 */
public class BaseDao {
	private static Properties prop = null;

	/**
	 * �������ݿ������ͼ��������ļ�
	 */
	static {
		try {
			prop = Tools.myLoad();
			Class.forName(prop.getProperty("CLASSNAME"));
		} catch (ClassNotFoundException e) {
			System.out.println("δ�ҵ�����");
		} catch (IOException e) {
			System.out.println("�����ļ�����");
		}
	}

	/**
	 * ������ݿ�����
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException {
		return DriverManager.getConnection(prop.getProperty("PROTOCAL")
				+ prop.getProperty("DBURL"));
	}

	/**
	 * �滻0~n��ռλ��
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
	 * ����sql�����һ�������
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
	 * ����sql��䷵�ظı������
	 * 
	 * @return
	 */
	public boolean sendBackWithNums(String sql, List<Object> params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int num = 0;// ���շ��صĸı������
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
	 * �ر����Ӽ��ͷ���Դ
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
