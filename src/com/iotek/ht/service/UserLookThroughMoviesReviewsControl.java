package com.iotek.ht.service;

import com.iotek.ht.view.UserLogedOperateMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

public class UserLookThroughMoviesReviewsControl {
	private UserLookThroughMoviesReviewService ultms=new UserLookThroughMoviesReviewService();
	public void userLookThroughMoviesControl(int choice,int userId){
		switch (choice) {
		case 1:
			//����������Ӱ����Ӱ������
			ultms.lookMoviesByName(userId);
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(userId);
			break;
		case 2:
			//���밴����������Ӱ����Ӱ������
			ultms.lookMoviesByType(userId);
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(userId);
			break;
		case 3:
			//����ȫ����Ӱ����Ӱ������
			ultms.lookAll(userId);
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(userId);
			break;
		case 0:
			new UserLogedOperateMenu().userLoginShow(userId);
			break;

		default:
			break;
		}
		
	}
}
