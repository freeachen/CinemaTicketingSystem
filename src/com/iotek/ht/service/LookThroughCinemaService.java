package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.CinemaReviewMenu;

/**
 * 用户浏览电影院列表后会进入到相应的评论界面 中转
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughCinemaService {
	private CinemaDao cinemaDao = new CinemaDao();

	/**
	 * 用户查看所有电影院
	 */
	public void userShowAll(int userId) {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "店");
			}
			System.out.println("*********************\n");
			// 用户查看电影院可以进行评论
			new CinemaReviewMenu().cinemaJudgeShow(userId, getCinemaName(list));
		} else {
			System.out.println("没有电影院");
		}
	}

	/**
	 * 游客查看电影院列表
	 * 
	 * @param userId
	 */
	public void touristShowAll() {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "店");
			}
			System.out.println("*********************\n");
			// 游客只能查看电影院的评论
			new CinemaReviewService().readReview(getCinemaName(list));
		} else {
			System.out.println("没有电影院");
		}
	}

	private Cinema getCinemaName(List<Cinema> list) {
		System.out.println("选择相应电影院查看影评:");
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1);
	}
}
