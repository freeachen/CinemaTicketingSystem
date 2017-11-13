package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import javax.tools.Tool;

import com.iotek.ht.db.dao.impl.MovieReviewDao;
import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.MovieReview;
import com.iotek.ht.util.Tools;

/**
 * 对影评的功能操作
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReviewService {
	private MoviesDao moviesDao=new MoviesDao();
	private MovieReviewDao movieReviewDao=new MovieReviewDao();
	// 读影评
	// 先判断有没有影评 有影评读影评
	/**
	 * 读影评 任何人都可以读
	 * 
	 * @param movieName
	 */
	public void readReview(String movieName) {
		//根据电影的名字去影评表中找到相应的影评信息 某某.评论内容.打的分数
		List<MovieReview> list=movieReviewDao.seleteAll(movieName);
		//打印影评信息
		System.out.println("电影:"+movieName+"--得分:"+moviesDao.seleteScore(movieName)+"分:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * 写影评 只能注册用户才能写
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void writeReview(int userId, String movieName) {
		int movieId=moviesDao.selectName(movieName).getId();
		Scanner in=new Scanner(System.in);
		//写影评时 先判断有没有写过影评
		String oldReview = movieReviewDao.seleteReview(userId, movieName);
		if (oldReview!=null) {
			//如果找到该用户评论过  就当做新增评论
			System.out.println("查到您已评论过此片！还想继续追加评论么？");
			System.out.println("1==>追加\n2==>放弃");
			int choice = Tools.getInt(1,2);
			if (choice==1) {
				System.out.println("请输入评论内容:");
				String newReview=in.nextLine();
				//追加到评论表中
				if (movieReviewDao.update(userId, movieName, oldReview+"--追加影评--"+newReview)) {
					System.out.println("发表成功！");
				}else {
					System.out.println("发表失败！");
				}
				
			}else {
				System.out.println("see you");
			}
		}else {
			//如果该用户以前未评论过 就新增评论
			System.out.println("请输入评论内容:");
			String newReview=in.next();
			System.out.println("请输入评分(1-10):");
			double movieScore = Tools.getInt(1,10);
			MovieReview movieReview=new MovieReview(userId, movieId, newReview, movieScore);
			if (movieReviewDao.insert(movieReview)) {
				System.out.println("发表成功！");
				//写影评的同时 要将所有评分的平均分放进电影的分数中
				double avg=movieReviewDao.selectAvgScore(movieReview);
				//將获得的平均分写进电影的分数中
				moviesDao.updateScore(movieName, avg);
			}else {
				System.out.println("发表失败！");
			}
		}
	}

	/**
	 * 删影评 只能是登陆用户才能删
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void deleteReview(int userId, String movieName) {
		//刪除前需要判断这个影评是不是这个用户写的
		if (movieReviewDao.seleteReview(userId, movieName)==null) {
			//查不到用户对这个电影的评论信息 则不能删除
			System.out.println("对不起！您不是这条影评的作者！不能删除此影评！");
		}else {
			System.out.println("您确定删除么?");
			System.out.println("1==>删除\n2==>放弃");
			int choice = Tools.getInt(1, 2);
			if (choice==1) {//确定要删除
				if (movieReviewDao.delete(userId, movieName)) {
					System.out.println("删除成功！");
				}else {
					System.out.println("删除失败！");
				}
			}else {//放弃删除
				System.out.println("see you");
			}

		}
	}
}
