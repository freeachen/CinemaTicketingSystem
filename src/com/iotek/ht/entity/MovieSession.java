package com.iotek.ht.entity;

import java.util.Date;

/**
 * ������
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

	private int id;// ���α��
	private int movieId;// ��Ӱ���
	private String time;// ����ʱ�������
	private int cinemaId;// ӰԺ���
	private int container;// ��������
	private int remainder;// ʣ����λ
	private double price;// Ʊ��
	private String cinemaName;//��ӰԺ��
	private String movieName;//��Ӱ��
	private String address;//ӰԺ��ַ
	private int hall;//������
	private int lasting;//ʱ��

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
