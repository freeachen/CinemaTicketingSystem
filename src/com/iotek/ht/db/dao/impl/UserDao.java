package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.Users;

/**
 * 对用户信息的数据库层的操作
 * 
 * @author zhangjiaqi
 * 
 */
public class UserDao extends BaseDao {
	/**
	 * 新增用户
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
		params.add(user.getRemainder());// 刚开始注册时 用户余额都为0元
		params.add(user.getPower());// 所有用户注册的权限都设置为 0
		return sendBackWithNums(sql, params);
	}

	/**
	 * 根据id删除用户信息
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
	 * 修改密保
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
	 * 修改密码
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
	 * 充值操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateRemainder(int id, double remainder) {
		String sql = "update Users set remainder=? where id=?";
		// 先取出对应id的内容
		Users user = showFromId(id);
		// 将原有的余额加上新增的充值金额累计放进数据库
		List<Object> params = new ArrayList<Object>();
		params.add(remainder + user.getRemainder());
		params.add(id);
		return sendBackWithNums(sql, params);

	}

	/**
	 * 查看数据库所有用户信息
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
	 * 根据id返回信息
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
	 * 判断登录是否成功
	 * 
	 * @param userName
	 * @return true 表示登录成功
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
		return id;// 如果用户和密码对应用户存在 返回true
	}

	/**
	 * 判断该用户名是否已被注册
	 * 
	 * @param userName
	 * @return false 表示未被注册 可以注册该用户名
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
		return id > 0;// 如果注册名不重复 返回false
	}

	/**
	 * 查找相应登陆名与密码对应的权限
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
		return power > 0;// 为管理员返回true 为用户返回false
	}

	/**
	 * 根据用户名获得密保问题、答案、id、密码 用于找到密码后直接登录
	 * 
	 * @param userName
	 * @return
	 */
	public Users getQuestion(String userName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = new Users();// 接收返回的密保问题与答案
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
