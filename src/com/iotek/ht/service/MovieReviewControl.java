package com.iotek.ht.service;

import com.iotek.ht.view.MovieReviewsMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

/**
 * 中转到影评功能块的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieReviewControl {
	public void movieReviewControl(int choice, int userId, String movieName) {
		MovieReviewService mrs = new MovieReviewService();
		switch (choice) {
		case 1:
			// 写影评
			mrs.writeReview(userId, movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 2:
			// 删影评
			mrs.deleteReview(userId, movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 3:
			// 读影评
			mrs.readReview(movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 0:
			// 返回上一层
			new UserLookThroughMoviesReviewMenu()
					.userLookThroughMoivesShow(userId);
			break;

		default:
			break;
		}
	}
}
