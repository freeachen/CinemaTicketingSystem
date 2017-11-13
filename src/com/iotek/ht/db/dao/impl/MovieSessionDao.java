package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import javax.naming.spi.DirStateFactory.Result;

import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.Movies;

/**
 * 对电影场次类数据库层面的操作
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionDao extends BaseDao {

	/**
	 * 新增场次
	 * 
	 * @param movieSession
	 * @return
	 */
	public boolean insert(MovieSession movieSession) {
		String sql = "insert into MovieSession(movieId,time,cinemaId,container,remainder,price,hall) values(?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		fillParams(movieSession, params);
		return sendBackWithNums(sql, params);
	}

	private void fillParams(MovieSession movieSession, List<Object> params) {
		params.add(movieSession.getMovieId());
		params.add(movieSession.getTime());
		params.add(movieSession.getCinemaId());
		params.add(movieSession.getContainer());
		params.add(movieSession.getRemainder());
		params.add(movieSession.getPrice());
		params.add(movieSession.getHall());
	}

	/**
	 * 删除场次
	 * 
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "delete from movieSession where movieSession.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * 修改场次的信息
	 * 
	 * @param movieSession
	 * @return
	 */
	public boolean update(MovieSession movieSession, int id) {
		String sql = "update movieSession set movieId=?,time=?,price=? where movieSession.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(movieSession.getMovieId());
		params.add(movieSession.getTime());
		params.add(movieSession.getPrice());
		params.add(id);
		return sendBackWithNums(sql, params);

	}

	/**
	 * 根据场次编号 修改剩余座位
	 * 
	 * @param
	 * @return
	 */
	public boolean updateRemainder(int id, int remainder) {
		String sql = "update movieSession set remainder=? where movieSession.id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(remainder);
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * 根据电影名 影院名 播映时间 查找指定场次的id
	 * 
	 * @param movieName
	 * @param cinemaName
	 * @param time
	 * @return
	 */
	public int selectId(String movieName, String cinemaName, String time,
			int hall) {
		String sql = "select movieSession.id from movieSession,cinema,movies where movieName=? and cinemaName=? and movieSession.time=? and movieSession.hall=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId";
		return getNum(movieName, cinemaName, time, hall, sql);

	}

	private int getNum(String movieName, String cinemaName, String time,
			int hall, String sql) {
		List<Object> params = new ArrayList<Object>();
		params.add(movieName);
		params.add(cinemaName);
		params.add(time);
		if (hall != 0) {
			params.add(hall);
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return id;
	}

	/**
	 * 根据电影名 影院名 播映时间 查找对应场厅的集合
	 * 
	 * @param movieName
	 * @param cinemaName
	 * @param time
	 * @return
	 */
	public List<Integer> selectHall(String movieName, String cinemaName,
			String time) {

		String sql = "select movieSession.hall from movieSession,cinema,movies where movieName=? and cinemaName=? and movieSession.time=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId";
		List<Object> params = new ArrayList<Object>();
		params.add(movieName);
		params.add(cinemaName);
		params.add(time);
		List<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				list.add(rs.getInt(1));
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
	 * 根据场次id获得场次的信息
	 * 
	 * @param id
	 * @return
	 */
	public List<MovieSession> selectSession(int id) {
		String sql = "select cinemaName,movieName,price,duration,movieSession.time,movieSession.id,cinemaAddress,hall from cinema,movies,movieSession where movieSession.id=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		List<Object> params = new ArrayList<Object>();
		List<MovieSession> list = new ArrayList<MovieSession>();
		MovieSession movieSession = null;
		params.add(id);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				movieSession = new MovieSession();
				movieSession.setId(rs.getInt(6));
				movieSession.setMovieName(rs.getString(2));
				movieSession.setCinemaName(rs.getString(1));
				movieSession.setAddress(rs.getString(7));
				movieSession.setHall(rs.getInt(8));
				movieSession.setTime(rs.getString(5));
				movieSession.setLasting(rs.getInt(4));
				movieSession.setPrice(rs.getDouble(3));
				list.add(movieSession);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return list;
	}

	/**
	 * 根据电影id 影院id 查找指定场次的id
	 * 
	 * @param movieSession
	 * @return
	 */
	public int selectId(int cinemaId, int movieId) {
		String sql = "select movieSession.id from movieSession,cinema,movies where cinemaId=? and movieId=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId";
		List<Object> params = new ArrayList<Object>();
		params.add(cinemaId);
		params.add(movieId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return id;

	}

	/**
	 * 根据电影名查看场次信息
	 * 
	 * @param movieName
	 */
	public List<MovieSession> selectSessionFromMovie(String movieName) {
		// 查找到的信息：++影城 ++号厅 ++电影 价格 时长 放映时间
		String sql = "select cinemaName,movieName,price,duration,movieSession.time,movieSession.id,cinemaAddress,hall from cinema,movies,movieSession where movieName like ? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		return getInformation("%" + movieName + "%", sql);
	}

	/**
	 * 根据电影院名查看场次信息
	 * 
	 * @param cinemaName
	 */
	public List<MovieSession> selectSessionFromCinema(String cinemaName) {
		// 查找到的信息：++影城 ++号厅++电影 价格 时长 放映时间
		String sql = "select cinemaName,movieName,price,duration,movieSession.time,movieSession.id,cinemaAddress,hall from cinema,movies,movieSession where cinemaName=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		return getInformation(cinemaName, sql);
	}

	/**
	 * 查看所有场次的信息
	 * 
	 * @return list
	 */
	public List<MovieSession> selectAll() {
		String sql = "select movieSession.id,cinemaName,cinemaAddress,hall,movieName,movieSession.time,duration,price from movieSession,cinema,movies where movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		List<MovieSession> list = new ArrayList<MovieSession>();
		MovieSession movieSession = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, null, conn, ps);
		try {
			while (rs.next()) {
				movieSession = new MovieSession();
				movieSession.setId(rs.getInt(1));
				movieSession.setCinemaName(rs.getString(2));
				movieSession.setAddress(rs.getString(3));
				movieSession.setHall(rs.getInt(4));
				movieSession.setMovieName(rs.getString(5));
				movieSession.setTime(rs.getString(6));
				movieSession.setLasting(rs.getInt(7));
				movieSession.setPrice(rs.getDouble(8));
				list.add(movieSession);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 根据场次的影院名查 所有这个影院放的电影
	 * 
	 * @param cinemaName
	 * @return
	 */
	public List<String> getMovies(String cinemaName) {
		String sql = "select movies.movieName from movies,cinema,movieSession where cinema.cinemaName=? and movies.id=moviesession.movieId and cinema.id=movieSession.cinemaId";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		List<String> list = new ArrayList<String>();
		params.add(cinemaName);
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				String movie = rs.getString(1);
				// 修改部分 修改了不放重复的电影
				if (!list.contains(movie)) {
					list.add(movie);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 根据场次的影院名和电影名查 所有这个场次的时间
	 * 
	 * @param cinemaName
	 *            movieName
	 * @return
	 */
	public List<String> getTime(String cinemaName, String movieName) {
		String sql = "select movieSession.time from movies,cinema,movieSession where cinema.cinemaName=? and movies.movieName=? and movies.id=moviesession.movieId and cinema.id=movieSession.cinemaId";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		List<String> list = new ArrayList<String>();
		params.add(cinemaName);
		params.add(movieName);
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				String time = rs.getString(1);
				if (!list.contains(time)) {
					list.add(time);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		Collections.sort(list);
		return list;
	}

	private List<MovieSession> getInformation(String movieName, String sql) {
		List<Object> params = new ArrayList<Object>();
		List<MovieSession> list = new ArrayList<MovieSession>();
		MovieSession movieSession = null;
		params.add(movieName);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				movieSession = new MovieSession();
				movieSession.setId(rs.getInt(6));
				movieSession.setMovieName(rs.getString(2));
				movieSession.setCinemaName(rs.getString(1));
				movieSession.setAddress(rs.getString(7));
				movieSession.setHall(rs.getInt(8));
				movieSession.setTime(rs.getString(5));
				movieSession.setLasting(rs.getInt(4));
				movieSession.setPrice(rs.getDouble(3));
				list.add(movieSession);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 获得场次里所有的电影名 不重复
	 * 
	 * @return
	 */
	public List<String> getMovieName() {
		String sql = "select movieName from movieSession,cinema,movies where movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		return getNames(sql);
	}

	/**
	 * 获得场次里所有的时间段 不重复
	 * 
	 * @return
	 */
	public List<String> getSessionTime() {
		String sql = "select movieSession.time from movieSession,cinema,movies where movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		return getNames(sql);
	}

	private List<String> getNames(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, null, conn, ps);
		// 存放所有的名字与地址的键值对
		List<String> list = new ArrayList<String>();
		try {
			while (rs.next()) {
				String name = rs.getString(1);
				// 保证存入容器的内容没有重复
				if (!list.contains(name)) {
					list.add(name);
				}
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

	/**
	 * 获得场次里所有的影院 不重复
	 * 
	 * @return
	 */
	public TreeMap<String, String> getCinemaName() {
		String sql = "select cinemaName,cinemaAddress from movieSession,cinema,movies where movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, null, conn, ps);
		// 存放所有的名字与地址的键值对
		TreeMap<String, String> map = new TreeMap<String, String>();
		try {
			while (rs.next()) {
				map.put(rs.getString(2), rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return map;

	}

	/**
	 * 获得场次里在 特定电影院特定场厅放映的电影的所有时段与电影id信息
	 * 
	 * @return list
	 */
	public List<String> getAllTime(int cinemaId, int hall) {
		String sql = "select movieSession.time from movieSession,cinema,movies where movieSession.cinemaId=? and movieSession.hall=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(cinemaId);
		params.add(hall);
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		// 存放所有的名字与地址的键值对
		List<String> list = new ArrayList<String>();
		try {
			while (rs.next()) {
				String time = rs.getString(1);
				list.add(time);
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

	/**
	 * 根据影院 电影 时段 来获得放这些场次的场厅
	 * 
	 * @return
	 */
	public List<Integer> getHall(String movieName, String cinemaName,
			String time) {
		String sql = "select movieSession.hall from movieSession,cinema,movies where movies.movieName=? and cinema.cinemaName=? and movieSession.time=? and movies.id=movieSession.movieId and cinema.id=movieSession.cinemaId ";
		List<Object> params = new ArrayList<Object>();
		List<Integer> list = new ArrayList<Integer>();
		params.add(movieName);
		params.add(cinemaName);
		params.add(time);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				list.add(rs.getInt(1));
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

	/**
	 * 根据场次id 获得对应场次的座位数
	 * 
	 * @param id
	 * @return
	 */
	public int getContainer(int id) {
		String sql = "select container from movieSession where movieSession.id=?";
		return getSeatNumbers(id, sql);
	}

	/**
	 * 根据场次 获得对应场次的剩余座位
	 * 
	 * @param id
	 * @return
	 */
	public int getRemainder(int id) {
		String sql = "select remainder from movieSession,tickets where tickets.id=? and tickets.sessionid=moviesession.id";
		return getSeatNumbers(id, sql);
	}

	private int getSeatNumbers(int id, String sql) {
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
		int container = -1;
		try {
			while (rs.next()) {
				container = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return container;
	}

}
