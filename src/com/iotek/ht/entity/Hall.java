package com.iotek.ht.entity;

/**
 * ������
 * 
 * @author zhangjiaqi
 *
 */
public class Hall {
	private int id;// ����id
	private int hallNum;// ������
	private int sessionId;// ����id

	public Hall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hall(int hallNum, int sessionId) {
		super();
		this.hallNum = hallNum;
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "Hall [id=" + id + ", hallNum=" + hallNum + ", sessionId="
				+ sessionId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHallNum() {
		return hallNum;
	}

	public void setHallNum(int hallNum) {
		this.hallNum = hallNum;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

}
