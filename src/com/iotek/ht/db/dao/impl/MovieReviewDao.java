package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.MovieReview;

/**
 * ��Ӱ����Ĳ���
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReviewDao extends BaseDao {
	/**
	 * ����Ӱ����Ϣ
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
	 * ɾ��Ӱ����Ϣ
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
	 * ׷������
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
	 * �����ӵ�Ӱ���ĵ�Ӱ��ƽ����
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
	 * ����û��Ե�Ӱ������
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
	 * �鿴��Ӱ�������û�����
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
