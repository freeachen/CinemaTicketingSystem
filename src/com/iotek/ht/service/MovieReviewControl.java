package com.iotek.ht.service;

import com.iotek.ht.view.MovieReviewsMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

/**
 * ��ת��Ӱ�����ܿ����ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieReviewControl {
	public void movieReviewControl(int choice, int userId, String movieName) {
		MovieReviewService mrs = new MovieReviewService();
		switch (choice) {
		case 1:
			// дӰ��
			mrs.writeReview(userId, movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 2:
			// ɾӰ��
			mrs.deleteReview(userId, movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 3:
			// ��Ӱ��
			mrs.readReview(movieName);
			new MovieReviewsMenu().movieJudgeShow(userId, movieName);
			break;
		case 0:
			// ������һ��
			new UserLookThroughMoviesReviewMenu()
					.userLookThroughMoivesShow(userId);
			break;

		default:
			break;
		}
	}
}
