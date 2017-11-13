package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.util.Tools;

/**
 * ������û�е�½ �����Բ鿴���ε���λ��Ϣ ʵ�ֲ鿴��λ��Ϣ�Ĺ��ܿ�
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughSeatsService {
	/**
	 * ���ݴ����ĵ�ӰԺ ���� ��Ӱ �鿴���г��ε���λ��Ϣ
	 * 
	 * @param id
	 */
	public void lookThrough(String name, int flag) {
		// �������ӰԺ�� ѡ���ӰԺ ���ݹ�����ӰԺ���ֺ�״̬1 ���в�ѯ�����ӰԺ�ĳ���
		if (flag == 1) {// ��ʾ����ǵ�ӰԺ
			// ����ӰԺ�� �õ�ӰԺ��Ӧ�ĳ���id
			int sessionId = getSessionIdByCinemaName(name);
			if (sessionId > 0) {
				System.out.println("************************");
				System.out.println("����:");
				printSelectSession(sessionId);
				System.out.print("��λ��:");
				// ����selectSeatService���ӡ��λ��ķ���
				new SelectSeatService().showSeatInformation(sessionId, 1);
			} else {
				System.out.println("û�����ӰԺ�ĳ���Ŷ��");
			}
		}
		// ��������ǵ�Ӱ ѡ���Ӱ ���ݹ�����Ӱ���ֺ�״̬0 ��ѯ��ӳ�����Ӱ�ĵ�ӰԺ��Ϣ���г��β�ѯ
		if (flag == 0) {// ��ʾ����ǵ�Ӱ
			// ���ݵ�Ӱ�� �õ�ӰԺ��Ӧ�ĳ���id
			int sessionId = getSessionIdByMovieName(name);
			// �ж�����û���������
			if (sessionId > 0) {// ������������ �ʹ�ӡ��λ��
				System.out.println("************************");
				System.out.println("����:");
				printSelectSession(sessionId);
				System.out.print("��λ��:");
				// ����selectSeatService���ӡ��λ��ķ���
				new SelectSeatService().showSeatInformation(sessionId, 0);
			} else {// ���û��������� ���û�г���
				System.out.println("û�������Ӱ�ĳ���Ŷ��");
			}
		}
	}

	private void printSelectSession(int sessionId) {
		// ��ӡ��ѡ���ε���Ϣ
		List<MovieSession> sessionList = new MovieSessionDao()
				.selectSession(sessionId);
		// ���ó��β����д�ӡ������Ϣ�ķ���
		new MovieSessionOperateService().printSession(sessionList);
	}

	/**
	 * ���ݵ�ӰԺ�� ����ѡ��selectSeatService��Ļ�õ�Ӱ�б���ʱ���б�Ĺ���
	 * 
	 * @param cinemaName
	 * @return
	 */
	private int getSessionIdByCinemaName(String cinemaName) {
		String movieName = new SelectSeatService().getMovieName(cinemaName);
		if (movieName != null) {
			System.out.println("ѡ��ĵ�Ӱ:" + movieName);
			// ���������ӰԺ�� �� ��Ӱ��ȥ�ҷ�ӳʱ��
			String time = new SelectSeatService()
					.getTime(cinemaName, movieName);
			System.out.println("ѡ���ʱ��:" + time);
			int hall = new SelectSeatService().getHall(cinemaName, movieName,
					time);
			System.out.println("ѡ��ĳ���:" + hall + "����");
			// �ٵ��ó��α�����еĸ�������������Ϣ���ҳ���id�ķ���
			int sessionId = new MovieSessionDao().selectId(movieName,
					cinemaName, time, hall);
			// ��ó��ε�id�� ���û�id �볡�� id���ݵ�ѡ�������� ����¼��ӰƱ�����
			return sessionId;
		}
		return -1;
	}

	/**
	 * ���ݵ�ӰԺ�� ����ѡ��selectSeatService��Ļ�õ�Ӱ�б���ʱ���б�Ĺ���
	 * 
	 * @param cinemaName
	 * @return
	 */
	private int getSessionIdByMovieName(String movieName) {
		// ���ݵ�Ӱ����ù��������Ӱ�ĳ����б�
		List<MovieSession> list = new MovieSessionDao()
				.selectSessionFromMovie(movieName);
		if (list != null && list.size() != 0) {
			new MovieSessionOperateService().printSession(list);
			// ѡ���Ӧ����� ����ѡ�񳡴β鿴��λ��Ϣ
			int choice = Tools.getInt(1, list.size());
			int sessionId = list.get(choice - 1).getId();// ������Ӧ��Ŷ�Ӧ���ζ���ĳ���id
			return sessionId;
		}
		return -1;// ���û�ҵ�������Ϣ ����-1��
	}
}
