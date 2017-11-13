package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.CinemaReviewDao;
import com.iotek.ht.db.dao.impl.MovieReviewDao;
import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.CinemaReview;
import com.iotek.ht.entity.MovieReview;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.util.Tools;

/**
 * 游客浏览过电影后的操作 查看座位与影评信息
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristMovieLookedOperateService {
	private MovieReviewDao movieReviewDao = new MovieReviewDao();

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
	 * 读影评 任何人都可以读
	 * 
	 * @param movieName
	 */
	public void readMovieReview(String movieName) {
		// 根据电影的名字去影评表中找到相应的影评信息 某某.评论内容.打的分数
		List<MovieReview> list = movieReviewDao.seleteAll(movieName);
		// 打印影评信息
		System.out.println("电影:《" + movieName + "》--得分:"
				+ new MoviesDao().seleteScore(movieName) + "分:");
		System.out.println("————————————————————————————————————————————————");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("————————————————————————————————————————————————");
	}

}
