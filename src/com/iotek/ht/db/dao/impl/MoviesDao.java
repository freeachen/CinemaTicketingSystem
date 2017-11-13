package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.Movies;

/**
 * �Ե�Ӱ���һЩ���ݿ����Ĳ���
 * 
 * @author zhangjiaqi
 * 
 */
public class MoviesDao extends BaseDao {

	/**
	 * ������Ӱ
	 * 
	 * @param movie
	 * @return true �����ɹ�
	 */
	public boolean insert(Movies movie) {
		// ������Ӱʱ ��������
		String sql = "insert into Movies(movieName,Lead,time,movieDescribe,movieType,flag,duration) values(?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		fillParams(movie, params);
		return sendBackWithNums(sql, params);
	}

	private void fillParams(Movies movie, List<Object> params) {
		params.add(movie.getMovieName());
		params.add(movie.getLead());
		params.add(movie.getTime());
		params.add(movie.getMovieDescribe());
		params.add(movie.getMovieType());
		params.add(movie.getFlag());
		params.add(movie.getDuration());
	}

	/**
	 * ɾ������
	 * 
	 * @return true ��ʾ�޸ĳɹ�
	 */
	public boolean delete(int id) {
		String sql = "delete from movies where movies.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ���ݵ�Ӱ���޸ĵ�Ӱ����Ϣ
	 * 
	 * @param movieName
	 * @return
	 */
	public boolean update(Movies movie) {
		String sql = "update Movies set lead=?," + "time=?,"
				+ "movieDescribe=?," + "movieType=?," + "flag=? "
				+ "where movies.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(movie.getLead());
		params.add(movie.getTime());
		params.add(movie.getMovieDescribe());
		params.add(movie.getMovieType());
		params.add(movie.getFlag());
		params.add(movie.getId());
		return sendBackWithNums(sql, params);
	}

	/**
	 * �޸ĵ�Ӱ������
	 * 
	 * @param movieName
	 * @param score
	 * @return
	 */
	public boolean updateScore(String movieName, double score) {
		String sql = "update movies set score=? where movies.movieName=?";
		List<Object> params = new ArrayList<Object>();
		params.add(score);
		params.add(movieName);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ���ָ����Ӱ��ʱ��
	 * 
	 * @param id
	 * @return time
	 */
	public int selectTime(int id) {
		String sql = "select movies.duration from movies where movies.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		int time = -1;
		try {
			while (rs.next()) {
				time = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return time;
	}

	/**
	 * ��õ�Ӱ������
	 * 
	 * @param movieName
	 * @return
	 */
	public double seleteScore(String movieName) {
		String sql = "select movies.score from movies where movies.movieName=?";
		List<Object> params = new ArrayList<Object>();
		params.add(movieName);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		double score = -1;
		try {
			while (rs.next()) {
				score = rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return score;
	}

	/**
	 * �鿴ָ����Ӱ���ĵ�Ӱ
	 * 
	 * @param movieName
	 * @return
	 */
	public Movies selectName(String movieName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movies movie = new Movies();
		String sql = "select * from Movies where movieName like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + movieName + "%");
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				getMovies(rs, movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return movie;
	}

	/**
	 * ģ����ѯ�ؼ��ֵ�Ӱ������Ϣ
	 * 
	 * @param movieName
	 * @return
	 */
	public List<Movies> selectNameLike(String movieName) {
		List<Movies> list = new ArrayList<Movies>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movies movie = new Movies();
		String sql = "select * from Movies where movieName like ?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + movieName + "%");
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				getMovies(rs, movie);
				list.add(movie);
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
	 * ���ݵ�Ӱ���Ͳ鿴��Ӱ
	 * 
	 * @param movieType
	 * @return
	 */
	public List<Movies> seleteType(String movieType) {
		String sql = "select * from movies where movieType=?";
		List<Object> params = new ArrayList<Object>();
		params.add(movieType);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movies movie = null;
		List<Movies> list = new ArrayList<Movies>();
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				movie = new Movies();
				getMovies(rs, movie);
				list.add(movie);
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
	 * ���ɵ�һ������ ���ش�result���еõ��ĵ�Ӱ�ĸ�����Ϣ
	 * 
	 * @param rs
	 * @param movie
	 * @throws SQLException
	 */
	private void getMovies(ResultSet rs, Movies movie) throws SQLException {
		movie.setId(rs.getInt(1));
		movie.setMovieName(rs.getString(2));
		movie.setLead(rs.getString(3));
		movie.setTime(rs.getString(4));
		movie.setMovieDescribe(rs.getString(5));
		movie.setMovieType(rs.getString(6));
		movie.setFlag(rs.getInt(7));
		movie.setDuration(rs.getInt(8));
	}

	/**
	 * ��ѯ���е�Ӱ��Ϣ
	 * 
	 * @return list
	 */
	public List<Movies> selectAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movies movie = null;
		List<Movies> list = new ArrayList<Movies>();
		String sql = "select * from Movies ";
		rs = sendBackWithQuery(sql, null, conn, ps);
		try {
			while (rs.next()) {
				movie = new Movies();
				getMovies(rs, movie);
				list.add(movie);
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
