package com.iotek.ht.entity;

/**
 * ӰԺ��
 * 
 * @author zhangjiaqi
 *
 */
public class Cinema {

	private int id;// ӰԺ���
	private String cinemaName;// ӰԺ����
	private String cinemaAddress;// ӰԺ��ַ
	private String cinemaReview;// ӰԺ����
	private int halls;//ӰԺ�ĳ�����

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
		return  cinemaName + "\t\t" + cinemaAddress+"��";
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
