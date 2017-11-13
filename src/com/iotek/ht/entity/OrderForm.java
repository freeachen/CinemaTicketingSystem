package com.iotek.ht.entity;

/**
 * 订单类
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderForm {
	private int id;// 订单id
	private int userId;// 用户id
	private int ticketsId;// 影票id
	private int orderFlag;// 订单状态
	private String cinemaName;//电影院名字
	private String movieName;//电影名
	private double price;//价格
	private int hall;//场厅号
	private String address;//影院地址
	private String time;//场次时间
	private int seat;//座位号

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
