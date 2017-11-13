package com.iotek.ht.entity;

/**
 * Ӱ����
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReview {
	private int id;// Ӱ��id
	private int userId;// �û�id
	private String userName;// �û���
	private int movieId;// ��Ӱid
	private String movieName;// ��Ӱ��
	private String movieReview;// Ӱ��
	private double movieScore;// ����

	public MovieReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieReview(int userId, int movieId, String movieReview,
			double movieScore) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.movieReview = movieReview;
		this.movieScore = movieScore;
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

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieReview() {
		return movieReview;
	}

	public void setMovieReview(String movieReview) {
		this.movieReview = movieReview;
	}

	public double getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(double movieScore) {
		this.movieScore = movieScore;
	}

	@Override
	public String toString() {
		return "�û�:"+userName + "\t\t\t����:��" + movieName
				+ "��\t\t˵:" + movieReview + "\t\t\t����:" + movieScore+"��";
	}

}
