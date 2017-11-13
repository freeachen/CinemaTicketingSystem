package com.iotek.ht.view;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.service.CinemaReviewControl;
import com.iotek.ht.util.Tools;

/**
 * 只有登陆的可以评电影院 游客只能看 而且只有在电影院看过电影的才能评论
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewMenu {

	public void cinemaJudgeShow(int userId, Cinema cinema) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>读影院评论<==");
		System.out.println("\t\t2==>写影院评论<==");
		System.out.println("\t\t3==>删影院评论<==");
		System.out.println("\t\t0==>返回上一层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 3);
		new CinemaReviewControl().cinemaReviewControl(choice, userId, cinema);
	}
}
