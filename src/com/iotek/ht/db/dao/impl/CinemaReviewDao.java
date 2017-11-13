package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.CinemaReview;
import com.iotek.ht.entity.MovieReview;

public class CinemaReviewDao extends BaseDao {

	/**
	 * 新增影院评论信息
	 * 
	 * @param movieReview
	 * @return
	 */
	public boolean insert(CinemaReview cinemaReview) {
		String sql = "insert into cinemaJudge (userId,cinemaId,cinemaReviews,cinemaScore) values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(cinemaReview.getUserId());
		params.add(cinemaReview.getCinemaId());
		params.add(cinemaReview.getCinemaReview());
		params.add(cinemaReview.getCinemaScore());
		return sendBackWithNums(sql, params);
	}

	/**
	 * 删除影院评论信息
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public boolean delete(int userId, Cinema cinema) {
		String sql = "delete from cinemaJudge where cinemaJudge.userId=? and cinemaJudge.cinemaId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(cinema.getId());
		return sendBackWithNums(sql, params);
	}

	/**
	 * 追加评论
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public boolean update(int userId, Cinema cinema, String reviews) {
		String sql = "update cinemaJudge set cinemaReviews=? where cinemaJudge.userId=? and cinemaJudge.cinemaId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(reviews);
		params.add(userId);
		params.add(cinema.getId());
		return sendBackWithNums(sql, params);
	}

	/**
	 * 获得添加的影院评的电影院的平均分
	 * @param cinemaReview
	 * @return avg
	 */
	public double selectAvgScore(CinemaReview cinemaReview){
		String sql="select avg(cinemaScore) from cinemaJudge where  cinemaJudge.cinemaId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(cinemaReview.getCinemaId());
		double score=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				score=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, ps, rs);
		}
		return score;
	}
	/**
	 * 获得用户对电院的评价
	 * 
	 * @param userId
	 * @param cinemaName
	 * @return
	 */
	public String seleteReview(int userId, Cinema cinema) {
		String sql = "select cinemaReviews from cinemaJudge where cinemaJudge.userId=? and cinemaJudge.cinemaId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(cinema.getId());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		String review = null;
		try {
			while (rs.next()) {
				review=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return review;
	}

	/**
	 * 查看电影院的所有用户评论
	 * @param movieName
	 * @return
	 */
	public List<CinemaReview> seleteAll(Cinema cinema) {
		String sql = "select users.userName,cinemaJudge.cinemaReviews,cinemaJudge.cinemaScore from cinemaJudge,users,cinema where cinemaJudge.cinemaId=? and cinemaJudge.userId=users.id and cinemaJudge.cinemaId=cinema.id";
		List<Object> params = new ArrayList<Object>();
		params.add(cinema.getId());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		List<CinemaReview> list = new ArrayList<CinemaReview>();
		CinemaReview cinemaReview = null;
		try {
			while (rs.next()) {
				cinemaReview = new CinemaReview();
				cinemaReview.setUserName(rs.getString(1));
				cinemaReview.setCinemaName(cinema.getCinemaName());
				cinemaReview.setCinemaAddress(cinema.getCinemaAddress());
				cinemaReview.setCinemaReview(rs.getString(2));
				cinemaReview.setCinemaScore(rs.getDouble(3));
				list.add(cinemaReview);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		Collections.sort(list);
		return list;
	}

}
