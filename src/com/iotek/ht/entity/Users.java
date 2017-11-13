package com.iotek.ht.entity;

/**
 * �û���
 * 
 * @author zhangjiaqi
 * 
 */
public class Users {
	private int id;// �û����
	private String userName;// �û���
	private String pwd;// �û�����
	private String question;// Ϊ�һ��������õ��ܱ�����
	private String answer;// Ϊ�һ��������õ��ܱ���
	private double remainder;// ���
	private int power;// Ȩ�� 0��ʾ�û� 1��ʾ����Ա

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
		return "id:\t" + id + "\n����:\t" + userName + "\n����:\t" + pwd
				+ "\n�ܱ�����:\t" + question + "\n�ܱ���:\t" + answer
				+ "\n���:\t" + remainder ;
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
