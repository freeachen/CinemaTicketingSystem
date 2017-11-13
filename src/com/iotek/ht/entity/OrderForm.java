package com.iotek.ht.entity;

/**
 * ������
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderForm {
	private int id;// ����id
	private int userId;// �û�id
	private int ticketsId;// ӰƱid
	private int orderFlag;// ����״̬
	private String cinemaName;//��ӰԺ����
	private String movieName;//��Ӱ��
	private double price;//�۸�
	private int hall;//������
	private String address;//ӰԺ��ַ
	private String time;//����ʱ��
	private int seat;//��λ��

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderForm(int userId, int ticketsId, int orderFlag) {
		super();
		this.userId = userId;
		this.ticketsId = ticketsId;
		this.orderFlag = orderFlag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTicketsId() {
		return ticketsId;
	}

	public void setTicketsId(int ticketsId) {
		this.ticketsId = ticketsId;
	}

	public int getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(int orderFlag) {
		this.orderFlag = orderFlag;
	}

	public int getHall() {
		return hall;
	}

	public void setHall(int hall) {
		this.hall = hall;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

}
