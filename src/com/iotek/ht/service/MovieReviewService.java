package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import javax.tools.Tool;

import com.iotek.ht.db.dao.impl.MovieReviewDao;
import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.MovieReview;
import com.iotek.ht.util.Tools;

/**
 * ��Ӱ���Ĺ��ܲ���
 * 
 * @author zhangjiaqi
 *
 */
public class MovieReviewService {
	private MoviesDao moviesDao=new MoviesDao();
	private MovieReviewDao movieReviewDao=new MovieReviewDao();
	// ��Ӱ��
	// ���ж���û��Ӱ�� ��Ӱ����Ӱ��
	/**
	 * ��Ӱ�� �κ��˶����Զ�
	 * 
	 * @param movieName
	 */
	public void readReview(String movieName) {
		//���ݵ�Ӱ������ȥӰ�������ҵ���Ӧ��Ӱ����Ϣ ĳĳ.��������.��ķ���
		List<MovieReview> list=movieReviewDao.seleteAll(movieName);
		//��ӡӰ����Ϣ
		System.out.println("��Ӱ:"+movieName+"--�÷�:"+moviesDao.seleteScore(movieName)+"��:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * дӰ�� ֻ��ע���û�����д
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void writeReview(int userId, String movieName) {
		int movieId=moviesDao.selectName(movieName).getId();
		Scanner in=new Scanner(System.in);
		//дӰ��ʱ ���ж���û��д��Ӱ��
		String oldReview = movieReviewDao.seleteReview(userId, movieName);
		if (oldReview!=null) {
			//����ҵ����û����۹�  �͵�����������
			System.out.println("�鵽�������۹���Ƭ���������׷������ô��");
			System.out.println("1==>׷��\n2==>����");
			int choice = Tools.getInt(1,2);
			if (choice==1) {
				System.out.println("��������������:");
				String newReview=in.nextLine();
				//׷�ӵ����۱���
				if (movieReviewDao.update(userId, movieName, oldReview+"--׷��Ӱ��--"+newReview)) {
					System.out.println("����ɹ���");
				}else {
					System.out.println("����ʧ�ܣ�");
				}
				
			}else {
				System.out.println("see you");
			}
		}else {
			//������û���ǰδ���۹� ����������
			System.out.println("��������������:");
			String newReview=in.next();
			System.out.println("����������(1-10):");
			double movieScore = Tools.getInt(1,10);
			MovieReview movieReview=new MovieReview(userId, movieId, newReview, movieScore);
			if (movieReviewDao.insert(movieReview)) {
				System.out.println("����ɹ���");
				//дӰ����ͬʱ Ҫ���������ֵ�ƽ���ַŽ���Ӱ�ķ�����
				double avg=movieReviewDao.selectAvgScore(movieReview);
				//����õ�ƽ����д����Ӱ�ķ�����
				moviesDao.updateScore(movieName, avg);
			}else {
				System.out.println("����ʧ�ܣ�");
			}
		}
	}

	/**
	 * ɾӰ�� ֻ���ǵ�½�û�����ɾ
	 * 
	 * @param userId
	 * @param movieName
	 */
	public void deleteReview(int userId, String movieName) {
		//�h��ǰ��Ҫ�ж����Ӱ���ǲ�������û�д��
		if (movieReviewDao.seleteReview(userId, movieName)==null) {
			//�鲻���û��������Ӱ��������Ϣ ����ɾ��
			System.out.println("�Բ�������������Ӱ�������ߣ�����ɾ����Ӱ����");
		}else {
			System.out.println("��ȷ��ɾ��ô?");
			System.out.println("1==>ɾ��\n2==>����");
			int choice = Tools.getInt(1, 2);
			if (choice==1) {//ȷ��Ҫɾ��
				if (movieReviewDao.delete(userId, movieName)) {
					System.out.println("ɾ���ɹ���");
				}else {
					System.out.println("ɾ��ʧ�ܣ�");
				}
			}else {//����ɾ��
				System.out.println("see you");
			}

		}
	}
}
