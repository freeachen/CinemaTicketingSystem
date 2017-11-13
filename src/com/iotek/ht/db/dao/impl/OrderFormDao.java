package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.OrderForm;

/**
 * 订单表
 * 
 * @author Administrator
 * 
 */
public class OrderFormDao extends BaseDao {
	/**
	 * 新增订单
	 * 
	 * @param orderForm
	 * @return
	 */
	public boolean insert(OrderForm orderForm) {
		String sql = "insert into orderform (userid,ticketsid,orderflag) values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(orderForm.getUserId());
		params.add(orderForm.getTicketsId());
		params.add(orderForm.getOrderFlag());
		return sendBackWithNums(sql, params);
	}

	/**
	 * 删除订单
	 * 
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "delete from orderForm where orderForm.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * 修改订单状态
	 * 
	 * @param userId
	 * @param ticketsId
	 */
	public void updateFlag(int userId, int ticketsId) {
		String sql = "update orderform set orderflag=1 where orderForm.userId=? and orderForm.ticketsId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(ticketsId);
		sendBackWithNums(sql, params);

	}

	/**
	 * 查看用户的所有订单
	 * 
	 * @param userId
	 * @return
	 */
	public List<OrderForm> selectAll(int userId) {
		String sql = "select cinemaName,movieName,price,orderflag,ticketsid,orderForm.id,hall,cinema.cinemaAddress,movieSession.time,tickets.setNum from orderform,tickets,moviesession,cinema,movies,users where orderform.userid=? and orderform.userid=tickets.userid and orderform.ticketsid=tickets.id and tickets.userid=users.id and tickets.sessionid=moviesession.id and moviesession.movieid=movies.id and moviesession.cinemaid=cinema.id";
		return getOrderList(userId, sql);

	}

	/**
	 * 查看用户未完成的订单 并获得未完成的订单的 影票id 为了购买付款用
	 * 
	 * @param userId
	 * @return
	 */
	public List<OrderForm> selectUndone(int userId) {
		String sql = "select cinemaName,movieName,price,orderflag, ticketsid,orderForm.id,hall,cinema.cinemaAddress,movieSession.time,tickets.setNum  from orderform,tickets,moviesession,cinema,movies,users where orderform.userid=? and orderflag=0 and orderform.userid=users.id and orderform.ticketsid=tickets.id and tickets.userid=users.id and tickets.sessionid=moviesession.id and moviesession.movieid=movies.id and moviesession.cinemaid=cinema.id";
		return getOrderList(userId, sql);

	}

	private List<OrderForm> getOrderList(int userId, String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<OrderForm> list = new ArrayList<OrderForm>();
		OrderForm orderForm = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				orderForm = new OrderForm();
				orderForm.setCinemaName(rs.getString(1));
				orderForm.setMovieName(rs.getString(2));
				orderForm.setPrice(rs.getDouble(3));
				orderForm.setOrderFlag(rs.getInt(4));
				orderForm.setTicketsId(rs.getInt(5));
				orderForm.setId(rs.getInt(6));
				orderForm.setHall(rs.getInt(7));
				orderForm.setAddress(rs.getString(8));
				orderForm.setTime(rs.getString(9));
				orderForm.setSeat(rs.getInt(10));
				list.add(orderForm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return list;
	}
}
