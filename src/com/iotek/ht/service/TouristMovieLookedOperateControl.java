package com.iotek.ht.service;

import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;

/**
 * 中转到 游客浏览过电影后的操作 未登陆时 的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristMovieLookedOperateControl {
	private TouristMovieLookedOperateService tmlos = new TouristMovieLookedOperateService();

	public void touristMovieLookedOperateControl(int choice, String movieName,
			int id, int i) {
		switch (choice) {
		case 1:
			// 查看座次信息
			tmlos.lookThrough(movieName, 0);// 状态0表示查看的是电影
			new TouristMovieLookedOperateMenu().touristMovieLookedOperateShow(
					movieName, id, i);
			break;
		case 2:
			// 读电影评论
			tmlos.readMovieReview(movieName);
			new TouristMovieLookedOperateMenu().touristMovieLookedOperateShow(
					movieName, id, i);
			break;
		case 0:
			// 返回上一层
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		default:
			break;
		}

	}
}
