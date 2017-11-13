package com.iotek.ht.entity;

/**
 * 用户类
 * 
 * @author zhangjiaqi
 * 
 */
public class Users {
	private int id;// 用户编号
	private String userName;// 用户名
	private String pwd;// 用户密码
	private String question;// 为找回密码设置的密保问题
	private String answer;// 为找回密码设置的密保答案
	private double remainder;// 余额
	private int power;// 权限 0表示用户 1表示管理员

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String userName, String pwd, String question, String answer,
			double remainder, int power) {
		this.userName = userName;
		this.pwd = pwd;
		this.question = question;
		this.answer = answer;
		this.remainder = remainder;
		this.power = power;
	}

	@Override
	public String toString() {
		return "id:\t" + id + "\n姓名:\t" + userName + "\n密码:\t" + pwd
				+ "\n密保问题:\t" + question + "\n密保答案:\t" + answer
				+ "\n余额:\t" + remainder ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public double getRemainder() {
		return remainder;
	}

	public void setRemainder(double remainder) {
		this.remainder = remainder;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
