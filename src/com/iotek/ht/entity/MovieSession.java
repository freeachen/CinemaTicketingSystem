package com.iotek.ht.entity;

import java.util.Date;

/**
 * 场次类
 * 
 * @author zhangjiaqi
 *
 */
public class MovieSession implements Comparable<MovieSession>{
	@Override
	public int compareTo(MovieSession o) {
		if (this.cinemaName.compareTo(o.cinemaName)==0) {
			return this.hall-o.hall;
		} else {
			return this.cinemaName.compareTo(o.cinemaName);
		}
	}

	private int id;// 场次编号
	private int movieId;// 电影编号
	private String time;// 播放时间和日期
	private int cinemaId;// 影院编号
	private int container;// 容纳人数
	private int remainder;// 剩余座位
	private double price;// 票价
	private String cinemaName;//电影院名
	private String movieName;//电影名
	private String address;//影院地址
	private int hall;//场厅号
	private int lasting;//时长

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public MovieSession(int movieId, String time, int cinemaId, int container,
			int remainder, double price,int hall) {
		super();
		this.movieId = movieId;
		this.time = time;
		this.cinemaId = cinemaId;
		this.container = container;
		this.remainder = remainder;
		this.price = price;
		this.hall=hall;
	}

	public MovieSession() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getContainer() {
		return container;
	}

	public void setContainer(int container) {
		this.container = container;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MovieSession [id=" + id + ", movieId=" + movieId + ", time="
				+ time + ", cinemaId=" + cinemaId + ", container=" + container
				+ ", remainder=" + remainder + ", price=" + price + "]";
	}

	public int getRemainder() {
		return remainder;
	}

	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	public int getHall() {
		return hall;
	}

	public void setHall(int hall) {
		this.hall = hall;
	}

	public int getLasting() {
		return lasting;
	}

	public void setLasting(int lasting) {
		this.lasting = lasting;
	}

	

}
