package com.iotek.ht.entity;

/**
 * 院评类
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReview implements Comparable<CinemaReview> {
	@Override
	public int compareTo(CinemaReview o) {
		// TODO Auto-generated method stub
		return this.cinemaName.compareTo(o.cinemaName);
	}

	private int id;// 院评id
	private int userId;// 用户id
	private String userName;// 用户名
	private int cinemaId;// 电影院id
	private String cinemaName;// 电影院名
	private String cinemaReview;// 影院评论
	private double cinemaScore;// 评分
	private String cinemaAddress;// 电影院地址

	public CinemaReview() {
	}

	public CinemaReview(int userId, int cinemaId, String cinemaReview,
			double cinemaScore) {
		this.userId = userId;
		this.cinemaId = cinemaId;
		this.cinemaReview = cinemaReview;
		this.cinemaScore = cinemaScore;
	}

	@Override
	public String toString() {
		return "用户:" + userName + "\t\t评论:<" + cinemaName + ">——<"
				+ cinemaAddress + "店>" + "\t\t说:" + cinemaReview + "\t\t打了:"
				+ cinemaScore + "分";
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getCinemaReview() {
		return cinemaReview;
	}

	public void setCinemaReview(String cinemaReview) {
		this.cinemaReview = cinemaReview;
	}

	public double getCinemaScore() {
		return cinemaScore;
	}

	public void setCinemaScore(double cinemaScore) {
		this.cinemaScore = cinemaScore;
	}

	public String getCinemaAddress() {
		return cinemaAddress;
	}

	public void setCinemaAddress(String cinemaAddress) {
		this.cinemaAddress = cinemaAddress;
	}

}
