package com.iotek.ht.service;

import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 中转到游客浏览电影功能 的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesControl {
	private TouristMovieLookedOperateMenu tmlom = new TouristMovieLookedOperateMenu();
	private TouristLookThroughMoviesService ltms = new TouristLookThroughMoviesService();

	public void lookMoviesControl(int choice, int id, int i) {
		switch (choice) {
		case 1:
			// 按电影名浏览
			ltms.lookMoviesByName(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 2:
			// 按类型浏览
			ltms.lookMoviesByType(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 3:
			// 浏览全部电影
			ltms.lookAllMovies(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 0:
			if (i == 0) {
				// 返回上一层
				new MainMenu().mainShow();
			} else {
				new UserLogedOperateMenu().userLoginShow(id);
			}
			break;

		default:
			break;
		}

	}
}
