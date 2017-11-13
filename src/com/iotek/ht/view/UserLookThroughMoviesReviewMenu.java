package com.iotek.ht.view;

import com.iotek.ht.service.UserLookThroughMoviesReviewsControl;
import com.iotek.ht.util.Tools;

/**
 * 用户浏览电影 进行影评操作
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLookThroughMoviesReviewMenu {
	public void userLookThroughMoivesShow(int userId) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>按电影名进入影评<==");
		System.out.println("\t\t2==>按 类 型 进入影评<==");
		System.out.println("\t\t3==>全部电影进入影评<==");
		System.out.println("\t\t0==>       退      出       <==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 3);
		new UserLookThroughMoviesReviewsControl().userLookThroughMoviesControl(
				choice, userId);
	}
}
