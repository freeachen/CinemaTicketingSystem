package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.MovieReview;

/**
 * 对影评表的操作
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReviewDao extends BaseDao {
	/**
	 * 新增影评信息
	 * 
	 * @param movieReview
	 * @return
	 */
	public boolean insert(MovieReview movieReview) {
		String sql = "insert into movieJudge (userId,movieId,movieReviews,movieScore) values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(movieReview.getUserId());
		params.add(movieReview.getMovieId());
		params.add(movieReview.getMovieReview());
		params.add(movieReview.getMovieScore());
		return sendBackWithNums(sql, params);
	}

	/**
	 * 删除影评信息
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public boolean delete(int userId, String movieName) {
		String sql = "delete from movieJudge where movieJudge.userId=? and movieJudge.movieId=(select movies.id from movies where movies.movieName=?)";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(movieName);
		return sendBackWithNums(sql, params);
	}

	/**
	 * 追加评论
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public boolean update(int userId, String movieName, String reviews) {
		String sql = "update movieJudge set movieReviews=? where movieJudge.userId=? and movieJudge.movieId=(select movies.id from movies where movies.movieName=?)";
		List<Object> params = new ArrayList<Object>();
		params.add(reviews);
		params.add(userId);
		params.add(movieName);
		return sendBackWithNums(sql, params);
	}

	/**
	 * 获得添加的影评的电影的平均分
	 * @param movieName
	 * @return
	 */
	public double selectAvgScore(MovieReview movieReview){
		String sql="select avg(movieScore) from movieJudge where  movieJudge.movieId=?";
		List<Object> params=new ArrayList<Object>();
		params.add(movieReview.getMovieId());
		double score=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				score=rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(conn, ps, rs);
		}
		return score;
	}
	/**
	 * 获得用户对电影的评价
	 * 
	 * @param userId
	 * @param movieId
	 * @return
	 */
	public String seleteReview(int userId, String movieName) {
		String sql = "select movieReviews from movieJudge where movieJudge.userId=? and movieJudge.movieId=(select movies.id from movies where movies.movieName=?)";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(movieName);
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
	 * 查看电影的所有用户评论
	 * @param movieName
	 * @return
	 */
	public List<MovieReview> seleteAll(String movieName) {
		String sql = "select users.userName,movieJudge.movieReviews,movieJudge.movieScore from movieJudge,users,movies where movieJudge.userId=users.id and movieJudge.movieId=movies.id and movieJudge.movieId=(select movies.id from movies where movies.movieName=?)";
		List<Object> params = new ArrayList<Object>();
		params.add(movieName);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		List<MovieReview> list = new ArrayList<MovieReview>();
		MovieReview movieReview = null;
		try {
			while (rs.next()) {
				movieReview = new MovieReview();
				movieReview.setUserName(rs.getString(1));
				movieReview.setMovieName(movieName);
				movieReview.setMovieReview(rs.getString(2));
				movieReview.setMovieScore(rs.getDouble(3));
				list.add(movieReview);
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
