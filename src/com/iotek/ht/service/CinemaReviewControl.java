package com.iotek.ht.service;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.view.CinemaReviewMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * ӰԺ���۹��ܵ���ת
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewControl {
	private CinemaReviewService crs = new CinemaReviewService();

	public void cinemaReviewControl(int choice, int userId, Cinema cinema) {
		switch (choice) {
		case 1:
			// ��ӰԺ����
			crs.readReview(cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 2:
			// дӰԺ����
			crs.writeReview(userId, cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 3:
			// ɾӰԺ����
			crs.deleteReview(userId, cinema);
			new CinemaReviewMenu().cinemaJudgeShow(userId, cinema);
			break;
		case 0:
			// ������һ��
			new UserLogedOperateMenu().userLoginShow(userId);
			break;

		default:
			break;
		}

	}
}
