package com.iotek.ht.entity;

/**
 * 评论类
 * 
 * @author zhangjiaqi
 *
 */
public class Judgement {
	private int ticketId; // 影票编号
	private String reviews;// 评论内容
	private int userId;// 用户编号

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
