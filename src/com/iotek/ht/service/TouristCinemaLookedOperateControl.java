package com.iotek.ht.service;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.TouristCinemaLookedOperateMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 中转 到 游客 查看过电影院后操作 的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristCinemaLookedOperateControl {
	private TouristCinemaLookedOperateService tclos = new TouristCinemaLookedOperateService();

	public void touristCinemaLookedOperateControl(int choice, Cinema cinema,
			int id, int i) {
		switch (choice) {
		case 1:
			// 查看座次信息
			tclos.lookThrough(cinema.getCinemaName(), 1);
			new TouristCinemaLookedOperateMenu()
					.touristCinemaLookedOperateShow(cinema, id, i);
			break;
		case 2:
			// 读电影院评论
			new CinemaReviewService().readReview(cinema);
			new TouristCinemaLookedOperateMenu()
					.touristCinemaLookedOperateShow(cinema, id, i);
			break;
		case 0:
			if (i == 0) {
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
