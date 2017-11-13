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
 * �ο��������Ӱ��Ĳ��� �鿴��λ��Ӱ����Ϣ
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristMovieLookedOperateService {
	private MovieReviewDao movieReviewDao = new MovieReviewDao();

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
	 * ��Ӱ�� �κ��˶����Զ�
	 * 
	 * @param movieName
	 */
	public void readMovieReview(String movieName) {
		// ���ݵ�Ӱ������ȥӰ�������ҵ���Ӧ��Ӱ����Ϣ ĳĳ.��������.��ķ���
		List<MovieReview> list = movieReviewDao.seleteAll(movieName);
		// ��ӡӰ����Ϣ
		System.out.println("��Ӱ:��" + movieName + "��--�÷�:"
				+ new MoviesDao().seleteScore(movieName) + "��:");
		System.out.println("������������������������������������������������������������������������������������������������");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("������������������������������������������������������������������������������������������������");
	}

}
