package com.iotek.ht.entity;

/**
 * Ժ����
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

	private int id;// Ժ��id
	private int userId;// �û�id
	private String userName;// �û���
	private int cinemaId;// ��ӰԺid
	private String cinemaName;// ��ӰԺ��
	private String cinemaReview;// ӰԺ����
	private double cinemaScore;// ����
	private String cinemaAddress;// ��ӰԺ��ַ

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
		return "�û�:" + userName + "\t\t����:<" + cinemaName + ">����<"
				+ cinemaAddress + "��>" + "\t\t˵:" + cinemaReview + "\t\t����:"
				+ cinemaScore + "��";
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
