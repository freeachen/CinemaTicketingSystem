package com.iotek.ht.entity;

/**
 * ������
 * 
 * @author zhangjiaqi
 *
 */
public class Judgement {
	private int ticketId; // ӰƱ���
	private String reviews;// ��������
	private int userId;// �û����

	public Judgement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Judgement(String reviews) {
		super();
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Review [ticketId=" + ticketId + ", reviews=" + reviews
				+ ", userId=" + userId + "]";
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
