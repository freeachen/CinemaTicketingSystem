package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.CinemaReviewMenu;

/**
 * �û������ӰԺ�б�����뵽��Ӧ�����۽��� ��ת
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughCinemaService {
	private CinemaDao cinemaDao = new CinemaDao();

	/**
	 * �û��鿴���е�ӰԺ
	 */
	public void userShowAll(int userId) {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "��");
			}
			System.out.println("*********************\n");
			// �û��鿴��ӰԺ���Խ�������
			new CinemaReviewMenu().cinemaJudgeShow(userId, getCinemaName(list));
		} else {
			System.out.println("û�е�ӰԺ");
		}
	}

	/**
	 * �οͲ鿴��ӰԺ�б�
	 * 
	 * @param userId
	 */
	public void touristShowAll() {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "��");
			}
			System.out.println("*********************\n");
			// �ο�ֻ�ܲ鿴��ӰԺ������
			new CinemaReviewService().readReview(getCinemaName(list));
		} else {
			System.out.println("û�е�ӰԺ");
		}
	}

	private Cinema getCinemaName(List<Cinema> list) {
		System.out.println("ѡ����Ӧ��ӰԺ�鿴Ӱ��:");
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1);
	}
}
