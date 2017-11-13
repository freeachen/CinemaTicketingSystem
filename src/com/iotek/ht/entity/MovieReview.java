package com.iotek.ht.entity;

/**
 * 影评类
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReview {
	private int id;// 影评id
	private int userId;// 用户id
	private String userName;// 用户名
	private int movieId;// 电影id
	private String movieName;// 电影名
	private String movieReview;// 影评
	private double movieScore;// 评分

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
		return "用户:"+userName + "\t\t\t评论:《" + movieName
				+ "》\t\t说:" + movieReview + "\t\t\t打了:" + movieScore+"分";
	}

}
