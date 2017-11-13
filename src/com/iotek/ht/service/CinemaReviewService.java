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
 * ӰԺ���۲�����ʵ�ַ���
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewService {

	private CinemaDao cinemaDao = new CinemaDao();
	private CinemaReviewDao cinemaReviewDao = new CinemaReviewDao();

	// ��ӰԺ��
	// ���ж���û��ӰԺ�� ��Ӱ����ӰԺ��
	/**
	 * ��ӰԺ�� �κ��˶����Զ�
	 * 
	 * @param movieName
	 */
	public void readReview(Cinema cinema) {
		// ���ݵ�Ӱ������ȥӰ�������ҵ���Ӧ��Ӱ����Ϣ ĳĳ.��������.��ķ���
		List<CinemaReview> list = cinemaReviewDao.seleteAll(cinema);
		// ��ӡӰ����Ϣ
		System.out.println("��ӰԺ:" + cinema.getCinemaName() + "-"
				+ cinema.getCinemaAddress() + "��" + "--�÷�:"
				+ cinemaDao.seleteScore(cinema) + "��:");
		System.out
				.println("��������������������������������������������������������������������������������������������������������");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out
				.println("��������������������������������������������������������������������������������������������������������");
	}

	/**
	 * дӰ�� ֻ���Ñ������ӰԺ�Ĺ�Ʊ��¼����д
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void writeReview(int userId, Cinema cinema) {
		// ���ж��Ƿ������������ӰԺ������Ӱ ��ӰƱ��
		if (new TicketsDao().searchRecorder(userId, cinema)) {
			// ��ʾ�й���Ӱ��¼ ��ô��������
			judgeWrite(userId, cinema);
		} else {
			System.out.println("�Բ�����δ���������ӰԺ�������޷�����");
		}
	}

	private void judgeWrite(int userId, Cinema cinema) {
		Scanner in = new Scanner(System.in);
		// дӰ��ʱ ���ж���û��д��Ӱ��
		String oldReview = cinemaReviewDao.seleteReview(userId, cinema);
		if (oldReview != null) {
			// ����ҵ����û����۹� �͵�����������
			System.out.println("�鵽�������۹��˵�ӰԺ���������׷������ô��");
			System.out.println("1==>׷��\n2==>����");
			int choice = Tools.getInt(1, 2);
			if (choice == 1) {
				System.out.println("��������������:");
				String newReview = in.nextLine();
				// ׷�ӵ����۱���
				if (cinemaReviewDao.update(userId, cinema, oldReview
						+ "--׷��Ӱ��--" + newReview)) {
					System.out.println("����ɹ���");
				} else {
					System.out.println("����ʧ�ܣ�");
				}

			} else {
				System.out.println("see you");
			}
		} else {
			// ������û���ǰδ���۹� ����������
			System.out.println("��������������:");
			String newReview = in.nextLine();
			System.out.println("����������(1-10):");
			double cinemaScore = Tools.getInt(1, 10);
			CinemaReview cinemaReview = new CinemaReview(userId,
					cinema.getId(), newReview, cinemaScore);
			if (cinemaReviewDao.insert(cinemaReview)) {
				System.out.println("����ɹ���");
				// ����ɹ��� ��Ҫ�������û��������Ӱ�����ּ���ƽ���ִ����Ӱ�ķ�����
				double avg = cinemaReviewDao.selectAvgScore(cinemaReview);
				// ��ӵ���ӦӰԺ��������
				cinemaDao.updateAvgScore(avg, cinema.getId());
			} else {
				System.out.println("����ʧ�ܣ�");
			}
		}
	}

	/**
	 * ɾӰ�� ֻ�����������ӰԺ�й���Ӱ��¼�Ĳ���ɾ
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void deleteReview(int userId, Cinema cinema) {
		// ���ж��Ƿ������������ӰԺ������Ӱ ��ӰƱ��
		if (new TicketsDao().searchRecorder(userId, cinema)) {
			// ��ʾ�й���Ӱ��¼ ��ô���Խ���ɾ������
			delete(userId, cinema);
		} else {
			System.out.println("�Բ�����δ���������ӰԺ�������޷�����");
		}
	}

	private void delete(int userId, Cinema cinema) {
		System.out.println("��ȷ��ɾ��ô?");
		System.out.println("1==>ɾ��\n2==>����");
		int choice = Tools.getInt(1, 2);
		if (choice == 1) {// ȷ��Ҫɾ��
			if (cinemaReviewDao.delete(userId, cinema)) {
				System.out.println("ɾ���ɹ���");
			} else {
				System.out.println("ɾ��ʧ�ܣ�");
			}
		} else {// ����ɾ��
			System.out.println("see you");
		}
	}

}
