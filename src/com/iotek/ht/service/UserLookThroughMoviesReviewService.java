package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MovieReviewsMenu;

/**
 * �û� ����Ӱ������
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLookThroughMoviesReviewService {
	private TouristLookThroughMoviesService tltms = new TouristLookThroughMoviesService();
	private MovieReviewsMenu movieReviewsMenu = new MovieReviewsMenu();

	/**
	 * �û��鿴ָ����Ӱ����Ϣ �����ж�Ӱ���Ĳ���
	 * 
	 * @param userId
	 */
	public void lookMoviesByName(int userId) {
		// �����ο������Ӱ�в�����Ӱ�б�ķ���
		Movies movie = tltms.generateMovies();
		if (movie != null) {// ���ҵ�ָ����Ӱ�������
			// Ȼ������û�Ӱ������
			movieReviewsMenu.movieJudgeShow(userId, movie.getMovieName());
		} else {
			System.out.println("�Ѿ�������");
		}
	}

	/**
	 * �û��鿴ָ�����͵�Ӱ����Ϣ �����ж�Ӱ���Ĳ���
	 * 
	 * @param userId
	 */
	public void lookMoviesByType(int userId) {
		List<Movies> list = tltms.generateCertainTypeMovies();
		if (list != null && list.size() != 0) {
			movieReviewsMenu.movieJudgeShow(userId, getMovieName(list));
		} else {
			System.out.println("�Ѿ�������");
		}
	}

	/**
	 * �û��鿴���е�Ӱ����Ϣ �����ж�Ӱ���Ĳ���
	 * 
	 * @param userId
	 */
	public void lookAll(int userId) {
		List<Movies> list = tltms.generateAllMovies();
		if (list != null && list.size() != 0) {
			movieReviewsMenu.movieJudgeShow(userId, getMovieName(list));
		} else {
			System.out.println("�Ѿ�������");
		}
	}

	private String getMovieName(List<Movies> list) {
		System.out.println("\n*************************");
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1).getMovieName();
	}
}
