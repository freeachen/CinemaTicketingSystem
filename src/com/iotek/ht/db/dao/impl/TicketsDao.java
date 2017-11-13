package com.iotek.ht.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.Tickets;

/**
 * ��ӰƱ������ݿ����Ĳ���
 * 
 * @author zhangjiaqi
 * 
 */
public class TicketsDao extends BaseDao {

	/**
	 * ����Ʊ ���źó��κ� ��������Ʊ �ճ��û�id�ֶβ���
	 * 
	 * @param movieSession
	 */
	public void generateTickets(MovieSession movieSession) {
		// ��������������ô���Ʊ
		for (int i = 0; i < movieSession.getContainer(); i++) {
			String sql = "insert into Tickets (sessionId,setNum,seatFlag,printFlag) values(?,?,0,0)";
			List<Object> params = new ArrayList<Object>();
			params.add(movieSession.getId());
			params.add(i + 1);
			sendBackWithNums(sql, params);
		}
	}

	/**
	 * ѡ���� ���û�id������ӦƱ�� ����Ʊ ¼��ӰƱ��
	 * 
	 * @param ticket
	 * @return
	 */
	public boolean insertTicket(Tickets ticket) {
		String sql = "update tickets set userId=?,seatFlag=1 where sessionId=? and setNum=?";
		List<Object> params = new ArrayList<Object>();
		params.add(ticket.getUserId());
		params.add(ticket.getSessionId());
		params.add(ticket.getSeatNum());
		return sendBackWithNums(sql, params);
	}

	/**
	 * ȡ��ѡ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSeat(int id) {
		String sql = "update tickets set userId=0,seatFlag=0,printFlag=0 where tickets.id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ɾ����Ӧ���ε�Ʊ
	 * 
	 * @param sessionId
	 */
	public void deleteTickets(int sessionId) {
		String sql = "delete from tickets where tickets.sessionId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(sessionId);
		sendBackWithNums(sql, params);
	}

	/**
	 * �޸Ĵ�ӡ״̬
	 * 
	 * @param userId
	 * @param sessionId
	 * @return
	 */
	public boolean updatePrintFlag(int userId, int sessionId) {
		String sql = "update tickets set printFlag=1 where userId=? and sessionId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(sessionId);
		return sendBackWithNums(sql, params);
	}

	/**
	 * ���ݵ�ӰƱid��� ӰԺ����Ӱ����λ���۸񣬴�ӡ״̬��Ϣ ���ص���һ������
	 * 
	 * @param ticketsId
	 * @return tickets
	 */
	public Tickets getTickets(int ticketsId) {
		String sql = "select cinemaName,movieName,setNum,price,printFlag,userName,movieSession.time,hall from orderform,tickets,moviesession,cinema,movies,users where tickets.id=? and orderform.userid=users.id and orderform.ticketsid=tickets.id and tickets.userid=users.id and tickets.sessionid=moviesession.id and moviesession.movieid=movies.id and moviesession.cinemaid=cinema.id";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tickets tickets = new Tickets();
		List<Object> params = new ArrayList<Object>();
		params.add(ticketsId);
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				tickets.setCinemaName(rs.getString(1));
				tickets.setMovieName(rs.getString(2));
				tickets.setSeatNum(rs.getInt(3));
				tickets.setPrice(rs.getDouble(4));
				tickets.setPrintFlag(rs.getInt(5));
				tickets.setUserName(rs.getString(6));
				tickets.setTime(rs.getString(7));
				tickets.setHall(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return tickets;
	}

	/**
	 * �鿴�û��Ƿ��������ӰԺ������Ӱ
	 * 
	 * @param userId
	 * @param cinemaName
	 * @return true ���û�����
	 */
	public boolean searchRecorder(int userId, Cinema cinema) {
		String sql = "select tickets.id from tickets,movieSession,cinema where tickets.userId=? and cinema.id=? and printFlag=1 and tickets.sessionId=moviesession.id and movieSession.cinemaId=cinema.id";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(cinema.getId());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = sendBackWithQuery(sql, params, conn, ps);
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
		return id > 0;
	}

	/**
	 * ���ݵ�ӰƱid��ó��εļ۸�
	 * 
	 * @param ticketsId
	 * @return
	 */
	public double getPrice(int ticketsId) {
		String sql = "select price from movieSession,tickets where tickets.id=? and tickets.sessionid=moviesession.id";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(ticketsId);
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		double price = -1;
		try {
			while (rs.next()) {
				price = rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return price;
	}

	/**
	 * ���ݵ�ӰƱid��ó���id
	 * 
	 * @param ticketsId
	 * @return
	 */
	public int getSessionId(int ticketsId) {
		String sql = "select sessionId from tickets where tickets.id=? ";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(ticketsId);
		ResultSet rs = null;
		rs = sendBackWithQuery(sql, params, conn, ps);
		int sessionId = -1;
		try {
			while (rs.next()) {
				sessionId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}
		return sessionId;
	}

	/**
	 * �û�����ѡ������ ѡ��Ʊ��id
	 * 
	 * @param userId
	 * @param sessionId
	 * @return
	 */
	public int selectId(int userId, int sessionId) {
		String sql = "select tickets.id from tickets where tickets.userId=? and tickets.sessionId=?";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(sessionId);
		ResultSet rs = null;
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
		return id;
	}

	/**
	 * ������λ�Ų�����λ״̬ �� ��ӡ״̬
	 * 
	 * @param seatNum
	 * @return
	 */
	public Tickets selectSeatFlag(int sessionId, int seatNum) {
		String sql = "select seatFlag,printFlag from Tickets where tickets.sessionId=? and setNum=?";
		Connection conn = null;
		PreparedStatement ps = null;
		List<Object> params = new ArrayList<Object>();
		params.add(sessionId);
		params.add(seatNum);
		ResultSet rs = null;
		Tickets tickets = new Tickets();
		rs = sendBackWithQuery(sql, params, conn, ps);
		try {
			while (rs.next()) {
				tickets.setSeatFlag(rs.getInt(1));
				tickets.setPrintFlag(rs.getInt(2));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}

		return tickets;
	}

	/**
	 * ��ѯ������ε� ����������λ�ŵļ���
	 * 
	 * @param userId
	 * @param CinemaId
	 * @return
	 */
	public List<Integer> getSeatNum(int sessionId) {
		// ��ѯ��λ״̬Ϊ0�� Ҳ����û����������λ
		String sql = "select setNum from tickets,movieSession where tickets.sessionId=? and tickets.seatFlag=0";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(sessionId);
		List<Integer> list = new ArrayList<Integer>();
		rs = sendBackWithQuery(sql, params, conn, ps);
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
}
