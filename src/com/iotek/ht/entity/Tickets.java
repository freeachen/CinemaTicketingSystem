package com.iotek.ht.entity;

/**
 * 影票类
 * 
 * @author zhangjiaqi
 * 
 */
public class Tickets {

	private int id;// 票的编号
	private int sessionId;// 场次编号
	private int userId;// 用户编号
	private int cinemaId;// 影院编号
	private int seatNum;// 座位号
	private int seatFlag;// 座位状态
	private int printFlag;// 票是否被打印的状态
	private String cinemaName;// 场次名
	private String movieName;// 电影名
	private double price;// 票价
	private String userName;//用户名
	private String time;//时段
	private int hall;//场厅号

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Tickets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tickets(int sessionId, int userId, int cinemaId, int seatNum,
			int seatFlag, int printFlag) {
		super();
		this.sessionId = sessionId;
		this.userId = userId;
		this.cinemaId = cinemaId;
		this.seatNum = seatNum;
		this.seatFlag = seatFlag;
		this.printFlag = printFlag;
	}

	@Override
	public String toString() {
		return "Tickets [id=" + id + ", sessionId=" + sessionId + ", userId="
				+ userId + ", cinemaId=" + cinemaId + ", seatNum=" + seatNum
				+ ", seatFlag=" + seatFlag + ", printFlag=" + printFlag + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getSeatFlag() {
		return seatFlag;
	}

	public void setSeatFlag(int seatFlag) {
		this.seatFlag = seatFlag;
	}

	public int getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(int printFlag) {
		this.printFlag = printFlag;
	}

	public int getHall() {
		return hall;
	}

	public void setHall(int hall) {
		this.hall = hall;
	}

}
