package com.iotek.ht.service;

import com.iotek.ht.view.UserLogedOperateMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

public class UserLookThroughMoviesReviewsControl {
	private UserLookThroughMoviesReviewService ultms=new UserLookThroughMoviesReviewService();
	public void userLookThroughMoviesControl(int choice,int userId){
		switch (choice) {
		case 1:
			//进入搜索电影操作影评界面
			ultms.lookMoviesByName(userId);
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(userId);
			break;
		case 2:
			//进入按类型搜索电影操作影评界面
			ultms.lookMoviesByType(userId);
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(userId);
			break;
		case 3:
			//进入全部电影操作影评界面
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
