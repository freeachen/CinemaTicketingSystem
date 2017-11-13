package com.iotek.ht.entity;

/**
 * 影院类
 * 
 * @author zhangjiaqi
 *
 */
public class Cinema {

	private int id;// 影院编号
	private String cinemaName;// 影院名称
	private String cinemaAddress;// 影院地址
	private String cinemaReview;// 影院评论
	private int halls;//影院的场厅数

	public Cinema() {
	}

	public Cinema(String cinemaName, String cinemaAddress, int halls) {
		this.cinemaName = cinemaName;
		this.cinemaAddress = cinemaAddress;
		this.halls=halls;
	}

	public int getHalls() {
		return halls;
	}

	public void setHalls(int halls) {
		this.halls = halls;
	}

	@Override
	public String toString() {
		return  cinemaName + "\t\t" + cinemaAddress+"店";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getCinemaAddress() {
		return cinemaAddress;
	}

	public void setCinemaAddress(String cinemaAddress) {
		this.cinemaAddress = cinemaAddress;
	}

	public String getCinemaReview() {
		return cinemaReview;
	}

	public void setCinemaReview(String cinemaReview) {
		this.cinemaReview = cinemaReview;
	}

}
