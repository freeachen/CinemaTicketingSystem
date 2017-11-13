package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.CinemaReviewDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.CinemaReview;

/**
 * ʵ�� �ο�����õ�ӰԺ��Ĳ��� ���幦�ܿ� �鿴��λ��Ϣ ������
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristCinemaLookedOperateService {
	private CinemaReviewDao cinemaReviewDao = new CinemaReviewDao();

	/**
	 * ���ݴ����ĵ�ӰԺ ���� ��Ӱ �鿴���г��ε���λ��Ϣ
	 * 
	 * @param id
	 */
	public void lookThrough(String name, int flag) {
		// ���ò鿴��λ��ķ���
		new LookThroughSeatsService().lookThrough(name, flag);
	}

	/**
	 * ��ӰԺ�� �κ��˶����Զ�
	 * 
	 * @param movieName
	 */
	public void readCinemaReview(Cinema cinema) {
		// ���ݵ�Ӱ������ȥӰ�������ҵ���Ӧ��Ӱ����Ϣ ĳĳ.��������.��ķ���
		List<CinemaReview> list = cinemaReviewDao.seleteAll(cinema);
		// ��ӡӰ����Ϣ
		System.out.println("��ӰԺ:" + cinema.getCinemaName() + "-"
				+ cinema.getCinemaAddress() + "��" + "--�÷�:"
				+ new CinemaDao().seleteScore(cinema) + "��:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
