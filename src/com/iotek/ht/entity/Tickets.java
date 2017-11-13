package com.iotek.ht.entity;

/**
 * ӰƱ��
 * 
 * @author zhangjiaqi
 * 
 */
public class Tickets {

	private int id;// Ʊ�ı��
	private int sessionId;// ���α��
	private int userId;// �û����
	private int cinemaId;// ӰԺ���
	private int seatNum;// ��λ��
	private int seatFlag;// ��λ״̬
	private int printFlag;// Ʊ�Ƿ񱻴�ӡ��״̬
	private String cinemaName;// ������
	private String movieName;// ��Ӱ��
	private double price;// Ʊ��
	private String userName;//�û���
	private String time;//ʱ��
	private int hall;//������

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
