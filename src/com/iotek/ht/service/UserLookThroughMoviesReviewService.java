package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MovieReviewsMenu;

/**
 * 用户 进入影评界面
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLookThroughMoviesReviewService {
	private TouristLookThroughMoviesService tltms = new TouristLookThroughMoviesService();
	private MovieReviewsMenu movieReviewsMenu = new MovieReviewsMenu();

	/**
	 * 用户查看指定电影的信息 并进行对影评的操作
	 * 
	 * @param userId
	 */
	public void lookMoviesByName(int userId) {
		// 调用游客浏览电影中产生电影列表的方法
		Movies movie = tltms.generateMovies();
		if (movie != null) {// 查找到指定电影进入界面
			// 然后进入用户影评界面
			movieReviewsMenu.movieJudgeShow(userId, movie.getMovieName());
		} else {
			System.out.println("已经尽力了");
		}
	}

	/**
	 * 用户查看指定类型电影的信息 并进行对影评的操作
	 * 
	 * @param userId
	 */
	public void lookMoviesByType(int userId) {
		List<Movies> list = tltms.generateCertainTypeMovies();
		if (list != null && list.size() != 0) {
			movieReviewsMenu.movieJudgeShow(userId, getMovieName(list));
		} else {
			System.out.println("已经尽力了");
		}
	}

	/**
	 * 用户查看所有电影的信息 并进行对影评的操作
	 * 
	 * @param userId
	 */
	public void lookAll(int userId) {
		List<Movies> list = tltms.generateAllMovies();
		if (list != null && list.size() != 0) {
			movieReviewsMenu.movieJudgeShow(userId, getMovieName(list));
		} else {
			System.out.println("已经尽力了");
		}
	}

	private String getMovieName(List<Movies> list) {
		System.out.println("\n*************************");
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1).getMovieName();
	}
}
