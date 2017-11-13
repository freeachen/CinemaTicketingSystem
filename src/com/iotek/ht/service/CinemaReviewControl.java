package com.iotek.ht.service;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.view.CinemaReviewMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 影院评论功能的跳转
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewControl {
	private CinemaReviewService crs = new CinemaReviewService();

	public void cinemaReviewControl(int choice, int userId, Cinema cinema) {
		switch (choice) {
		case 1:
			// 读影院评论
			crs.readReview(cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 2:
			// 写影院评论
			crs.writeReview(userId, cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 3:
			// 删影院评论
			crs.deleteReview(userId, cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 0:
			// 返回上一层
			new UserLogedOperateMenu().userLoginShow(userId);
			break;

		default:
			break;
		}

	}
}
