package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.CinemaReviewDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.CinemaReview;

/**
 * 实现 游客浏览好电影院后的操作 具体功能块 查看座位信息 与评论
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristCinemaLookedOperateService {
	private CinemaReviewDao cinemaReviewDao = new CinemaReviewDao();

	/**
	 * 根据传来的电影院 或者 电影 查看所有场次的座位信息
	 * 
	 * @param id
	 */
	public void lookThrough(String name, int flag) {
		// 调用查看座位表的方法
		new LookThroughSeatsService().lookThrough(name, flag);
	}

	/**
	 * 读影院评 任何人都可以读
	 * 
	 * @param movieName
	 */
	public void readCinemaReview(Cinema cinema) {
		// 根据电影的名字去影评表中找到相应的影评信息 某某.评论内容.打的分数
		List<CinemaReview> list = cinemaReviewDao.seleteAll(cinema);
		// 打印影评信息
		System.out.println("电影院:" + cinema.getCinemaName() + "-"
				+ cinema.getCinemaAddress() + "店" + "--得分:"
				+ new CinemaDao().seleteScore(cinema) + "分:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
