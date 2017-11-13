package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.Users;

/**
 * ���û���Ϣ�����ݿ��Ĳ���
 * 
 * @author zhangjiaqi
 * 
 */
public class UserDao extends BaseDao {
	/**
	 * �����û�
	 * 
	 * @return
	 */
	public boolean insert(Users user) {
		List<Object> params = new ArrayList<Object>();
		String sql = "insert into Users(userName,pwd,question,answer,remainder,power)values(?,?,?,?,?,?)";
		params.add(user.getUserName());
		params.add(user.getPwd());
		params.add(user.getQuestion());
		params.add(user.getAnswer());
		params.add(user.getRemainder());// �տ�ʼע��ʱ �û���Ϊ0Ԫ
		params.add(user.getPower());// �����û�ע���Ȩ�޶�����Ϊ 0
		return sendBackWithNums(sql, params);
	}

	/**
	 * ����idɾ���û���Ϣ
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean deleteFromId(int id) {
		int num = 0;
		String sql = "delete from users where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * �޸��ܱ�
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateQusetion(Users user) {
		String sql = "update Users set question=? ,answer=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getQuestion());
		params.add(user.getAnswer());
		params.add(user.getId());
		return sendBackWithNums(sql, params);
	}

	/**
	 * �޸�����
	 * 
	 * @param user
	 * @return
	 */
	public boolean updatePwd(Users user) {
		String sql = "update Users set pwd=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getPwd());
		params.add(user.getId());
		return sendBackWithNums(sql, params);
	}

	/**
	 * ��ֵ����
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateRemainder(int id, double remainder) {
		String sql = "update Users set remainder=? where id=?";
		// ��ȡ����Ӧid������
		Users user = showFromId(id);
		// ��ԭ�е������������ĳ�ֵ����ۼƷŽ����ݿ�
		List<Object> params = new ArrayList<Object>();
		params.add(remainder + user.getRemainder());
		params.add(id);
		return sendBackWithNums(sql, params);

	}

	/**
	 * �鿴���ݿ������û���Ϣ
	 * 
	 * @return ArrayList
	 */
	public List<Users> showAllUsers() {
		String sql = "select * from Users";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Users> list = new ArrayList<Users>();
		Users user = null;
		rs = sendBackWithQuery(sql, null, conn, ps);
		try {
			while (rs.next()) {
				user = new Users();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setQuestion(rs.getString(4));
				user.setAnswer(rs.getString(5));
				user.setRemainder(rs.getDouble(6));
				user.setPower(rs.getInt(7));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return list;
	}

	/**
	 * ����id������Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Users showFromId(int id) {
		String sql = "select * from Users where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = new Users();
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPwd(rs.getString(3));
				user.setQuestion(rs.getString(4));
				user.setAnswer(rs.getString(5));
				user.setRemainder(rs.getDouble(6));
				user.setPower(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return user;
	}

	/**
	 * �жϵ�¼�Ƿ�ɹ�
	 * 
	 * @param userName
	 * @return true ��ʾ��¼�ɹ�
	 */
	public int judgeLogin(Users user) {
		String sql = "select id from Users where userName=? and pwd=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserName());
		params.add(user.getPwd());
		rs = sendBackWithQuery(sql, params, conn, ps);
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return id;// ����û��������Ӧ�û����� ����true
	}

	/**
	 * �жϸ��û����Ƿ��ѱ�ע��
	 * 
	 * @param userName
	 * @return false ��ʾδ��ע�� ����ע����û���
	 */
	public boolean judgeNameExist(String userName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		String sql = "select id from Users where userName=?";
		rs = sendBackWithQuery(sql, params, conn, ps);
		int id = -1;
		try {
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return id > 0;// ���ע�������ظ� ����false
	}

	/**
	 * ������Ӧ��½���������Ӧ��Ȩ��
	 * 
	 * @param user
	 * @return
	 */
	public boolean judgeUserOrAdmin(Users user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUserName());
		params.add(user.getPwd());
		String sql = "select power from Users where userName=? and pwd=?";
		rs = sendBackWithQuery(sql, params, conn, ps);
		int power = -1;
		try {
			while (rs.next()) {
				power = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return power > 0;// Ϊ����Ա����true Ϊ�û�����false
	}

	/**
	 * �����û�������ܱ����⡢�𰸡�id������ �����ҵ������ֱ�ӵ�¼
	 * 
	 * @param userName
	 * @return
	 */
	public Users getQuestion(String userName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = new Users();// ���շ��ص��ܱ��������
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		String sql = "select id,question,answer,pwd from Users where userName=?";
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setQuestion(rs.getString(2));
				user.setAnswer(rs.getString(3));
				user.setPwd(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return user;
	}
}
