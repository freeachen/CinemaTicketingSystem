package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iotek.ht.entity.Cinema;

/**
 * �Ե�ӰԺ�����ݿ����Ĳ���
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaDao extends BaseDao {

	/**
	 * ������ӰԺ
	 * 
	 * @param movie
	 * @return true �����ɹ�
	 */
	public boolean insert(Cinema cinema) {
		String sql = "insert into Cinema(cinemaName,cinemaAddress,halls) values(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		fillParams(cinema, params);
		return sendBackWithNums(sql, params);
	}

	private void fillParams(Cinema cinema, List<Object> params) {
		params.add(cinema.getCinemaName());
		params.add(cinema.getCinemaAddress());
		params.add(cinema.getHalls());

	}

	/**
	 * ɾ������ָ����ӰԺ
	 * 
	 * @return true ��ʾ�޸ĳɹ�
	 */
	public boolean delete(int  id) {
		String sql = "delete from Cinema where cinema.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ���ݵ�ӰԺ���޸ĵ�ӰԺ����Ϣ
	 * 
	 * @param cinemaName
	 * @return
	 */
	public boolean update(int id, Cinema cinema) {
		String sql = "update Cinema set cinemaName=?,cinemaAddress=?,halls=? where cinema.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(cinema.getCinemaName());
		params.add(cinema.getCinemaAddress());
		params.add(cinema.getHalls());
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/*
	 * ����ӰԺ��id �޸�ӰԺ����
	 */
	public boolean updateAvgScore(double score, int cinemaId) {
		String sql = "update cinema set score=? where cinema.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(score);
		params.add(cinemaId);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ��ӰԺid��ö�Ӧ����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public Cinema selectById(int id) {
		String sql = "select  cinema.id,cinema.cinemaName,cinemaAddress,cinema.halls from cinema where cinema.id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cinema cinema = new Cinema();
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				getCinema(rs, cinema);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return cinema;
	}

	/**
	 * ��õ�ӰԺ������
	 * 
	 * @param movieName
	 * @return
	 */
	public double seleteScore(Cinema cinema) {
		String sql = "select cinema.score from cinema where cinema.Id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(cinema.getId());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		double score = -1;
		try {
			score = rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return score;
	}

	/**
	 * ��õ�ӰԺ�ĵ�ַ
	 * 
	 * @param id
	 * @return
	 */
	public String selectAddress(int id) {
		String sql = "select  cinemaAddress from cinema where cinema.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		String address = null;
		try {
			address = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return address;
	}

	/**
	 * �鿴��ӰԺ����Ӧ�����е�ӰԺ����Ϣ
	 * 
	 * @param cinemaName
	 * @return
	 */
	public List<Cinema> selectName(String cinemaName) {
		String sql = "select * from Cinema where cinemaName like ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cinema cinema = null;
		List<Cinema> list=new ArrayList<Cinema>();
		List<Object> params = new ArrayList<Object>();
		params.add("%"+cinemaName+"%");
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				cinema=new Cinema();
				cinema.setId(rs.getInt(1));
				cinema.setCinemaName(rs.getString(2));
				cinema.setCinemaAddress(rs.getString(3));
				cinema.setHalls(rs.getInt(4));
				list.add(cinema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return list;
	}

	/**
	 * ��ѯ���е�ӰԺ��Ϣ
	 * 
	 * @return list
	 */
	public List<Cinema> selectAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cinema cinema = null;
		List<Cinema> list = new ArrayList<Cinema>();
		String sql = "select * from Cinema ";
		rs = sendBackWithQuery(sql, null, conn, ps);
		try {
			while (rs.next()) {
				cinema = new Cinema();
				getCinema(rs, cinema);
				list.add(cinema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return list;
	}

	private void getCinema(ResultSet rs, Cinema cinema) throws SQLException {
		cinema.setId(rs.getInt(1));
		cinema.setCinemaName(rs.getString(2));
		cinema.setCinemaAddress(rs.getString(3));
		cinema.setHalls(rs.getInt(4));
	}

	/**
	 * �жϵ�ӰԺ�Ƿ��ظ�
	 * 
	 * @param cinemaName
	 * @return
	 */
	public boolean judgeExist(String cinemaName, String cinemaAddress) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(cinemaName);
		params.add(cinemaAddress);
		String sql = "select id from Cinema where cinemaName=? and cinemaAddress=?";
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
		return id > 0;// �����ӰԺ�����ظ� ����false
	}
}
