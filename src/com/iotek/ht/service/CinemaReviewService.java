package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.CinemaReviewDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.CinemaReview;
import com.iotek.ht.util.Tools;

/**
 * 影院评论操作的实现方法
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewService {

	private CinemaDao cinemaDao = new CinemaDao();
	private CinemaReviewDao cinemaReviewDao = new CinemaReviewDao();

	// 读影院评
	// 先判断有没有影院评 有影评读影院评
	/**
	 * 读影院评 任何人都可以读
	 * 
	 * @param movieName
	 */
	public void readReview(Cinema cinema) {
		// 根据电影的名字去影评表中找到相应的影评信息 某某.评论内容.打的分数
		List<CinemaReview> list = cinemaReviewDao.seleteAll(cinema);
		// 打印影评信息
		System.out.println("电影院:" + cinema.getCinemaName() + "-"
				+ cinema.getCinemaAddress() + "店" + "--得分:"
				+ cinemaDao.seleteScore(cinema) + "分:");
		System.out
				.println("————————————————————————————————————————————————————");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out
				.println("————————————————————————————————————————————————————");
	}

	/**
	 * 写影评 只能用戶有这个影院的购票记录才能写
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void writeReview(int userId, Cinema cinema) {
		// 先判断是否有在这儿个电影院看过电影 查影票表
		if (new TicketsDao().searchRecorder(userId, cinema)) {
			// 表示有过观影记录 那么可以评论
			judgeWrite(userId, cinema);
		} else {
			System.out.println("对不起！您未来过这个电影院，所以无法评论");
		}
	}

	private void judgeWrite(int userId, Cinema cinema) {
		Scanner in = new Scanner(System.in);
		// 写影评时 先判断有没有写过影评
		String oldReview = cinemaReviewDao.seleteReview(userId, cinema);
		if (oldReview != null) {
			// 如果找到该用户评论过 就当做新增评论
			System.out.println("查到您已评论过此电影院！还想继续追加评论么？");
			System.out.println("1==>追加\n2==>放弃");
			int choice = Tools.getInt(1, 2);
			if (choice == 1) {
				System.out.println("请输入评论内容:");
				String newReview = in.nextLine();
				// 追加到评论表中
				if (cinemaReviewDao.update(userId, cinema, oldReview
						+ "--追加影评--" + newReview)) {
					System.out.println("发表成功！");
				} else {
					System.out.println("发表失败！");
				}

			} else {
				System.out.println("see you");
			}
		} else {
			// 如果该用户以前未评论过 就新增评论
			System.out.println("请输入评论内容:");
			String newReview = in.nextLine();
			System.out.println("请输入评分(1-10):");
			double cinemaScore = Tools.getInt(1, 10);
			CinemaReview cinemaReview = new CinemaReview(userId,
					cinema.getId(), newReview, cinemaScore);
			if (cinemaReviewDao.insert(cinemaReview)) {
				System.out.println("发表成功！");
				// 发表成功后 需要将所有用户对这个电影的评分计算平均分存入电影的分数中
				double avg = cinemaReviewDao.selectAvgScore(cinemaReview);
				// 添加到相应影院的评分中
				cinemaDao.updateAvgScore(avg, cinema.getId());
			} else {
				System.out.println("发表失败！");
			}
		}
	}

	/**
	 * 删影评 只能是在这个电影院有过观影记录的才能删
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void deleteReview(int userId, Cinema cinema) {
		// 先判断是否有在这儿个电影院看过电影 查影票表
		if (new TicketsDao().searchRecorder(userId, cinema)) {
			// 表示有过观影记录 那么可以进行删除操作
			delete(userId, cinema);
		} else {
			System.out.println("对不起！您未来过这个电影院，所以无法评论");
		}
	}

	private void delete(int userId, Cinema cinema) {
		System.out.println("您确定删除么?");
		System.out.println("1==>删除\n2==>放弃");
		int choice = Tools.getInt(1, 2);
		if (choice == 1) {// 确定要删除
			if (cinemaReviewDao.delete(userId, cinema)) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
		} else {// 放弃删除
			System.out.println("see you");
		}
	}

}
