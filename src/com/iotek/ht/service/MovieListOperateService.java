package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;

/**
 * ����Ա������Ӱ�б����ľ���ʵ��ģ��
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieListOperateService {
	private MoviesDao moviesDao = new MoviesDao();

	/**
	 * ������Ӱ
	 */
	public void insertMovie() {
		if (moviesDao.insert(insertShow())) {
			System.out.println("�����ɹ���");
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * ������Ӱʱ�Ŀ�ʼ��ʾ
	 * 
	 * @return
	 */
	public Movies insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("�������������ӵ�Ӱ�б�:");
		System.out.println("�������Ӱ������:");
		String movieName = in.nextLine();
		System.out.println("�������Ӱ������:");
		String lead = in.nextLine();
		System.out.println("�������Ӱ����ӳʱ��:");
		String time = in.nextLine();
		System.out.println("�������Ӱ�ĵ�Ӱ����:");
		String movieDescribe = in.nextLine();
		System.out.println("�������Ӱ�Ĳ���ʱ��:");
		int duration = in.nextInt();
		System.out.println("�������Ӱ����");
		System.out.println("���������ѡ��:");
		String movieType = getMovieType();
		System.out.println("��Ӱ����ѡ��" + movieType);
		// flagʼ��Ϊ1 ������Ӱ��ʾ��ӳ
		return new Movies(movieName, lead, time, movieDescribe, movieType, 1,
				duration);

	}

	/**
	 * ��õ�Ӱ����
	 * 
	 * @return
	 */
	private String getMovieType() {
		new MovieTypeListMenu().movieTypeListMenu();
		return new MovieTypeListService().movieTypeListControl();

	}

	/**
	 * ɾ����Ӱ ֻ�ǽ�����ӳ״̬���ó�0
	 */
	public void deleteMovie() {
		int id = getMovieName();
		System.out.println("ȷ��ɾ��?");
		System.out.println("1==>����");
		System.out.println("2==>ȷ��");
		int select = Tools.getInt(1, 2);
		if (select == 2) {
			moviesDao.delete(id);
			System.out.println("�¼ܳɹ���");
		} else {
			System.out.println("��������ɾ����������");
		}
	}

	/**
	 * ���Ҫɾ����Ӱ������
	 * 
	 * @return
	 */
	private int getMovieName() {
		System.out.println("��ѡ���Ӱ������:");
		List<Movies> list = moviesDao.selectAll();
		int count = 0;
		for (Movies movies : list) {
			count++;
			System.out.println(count + "==>" + movies);
		}
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1).getId();// ����ѡ��ĵ�Ӱ��id���в���
	}

	/**
	 * �޸ĵ�Ӱ��Ϣ
	 */
	public void updateMovie() {
		Movies movie = updateShow();
		if (movie != null) {
			// ִ���޸Ĳ���
			if (moviesDao.update(movie)) {
				System.out.println("�޸ĳɹ���");
			} else {
				System.out.println("�޸�ʧ�ܣ�");
			}
		}
	}

	private Movies updateShow() {
		Scanner in = new Scanner(System.in);
		int id = getMovieName();
		Movies movie = null;
		System.out.println("ȷ���޸�ô��");
		System.out.println("1==>����");
		System.out.println("2==>����");
		if (Tools.getInt(1, 2) == 2) {// ���ѡ�����
			System.out.println("�������µĵ�Ӱ����:");
			String lead = in.next();
			System.out.println("�������µĵ�Ӱ��ӳʱ��:");
			String time = in.next();
			System.out.println("�������µĵ�Ӱ����:");
			String movieDescribe = in.next();
			System.out.println("�������µĵ�Ӱ����:");
			String movieType = new MovieListOperateService().getMovieType();
			System.out.println("������Ҫ���ĵĵ�Ӱ��ӳʱ��:");
			int duration = in.nextInt();
			movie = new Movies(null, lead, time, movieDescribe, movieType, 1,
					duration);
			movie.setId(id);
		} else {
			System.out.println("���ѷ�����������");
		}
		return movie;
	}

	/**
	 * �鿴��Ӱ����Ӧ��Ӱ��Ϣ
	 */
	public void showFromName() {
		Scanner in = new Scanner(System.in);
		System.out.println("������Ҫ�鿴�ĵ�Ӱ��:");
		String movieName = in.next();
		Movies movie = moviesDao.selectName(movieName);
		if (movie.getId() != 0) {// ��ʾ�ҵ��������Ӱ
			System.out.println(movie);
		} else {
			System.out.println("û���ⲿ��Ӱ˼�ܴ�~~~");
		}
	}

	/**
	 * �鿴���е�Ӱ
	 */
	public void showAll() {
		List<Movies> list = moviesDao.selectAll();
		for (Movies movies : list) {
			System.out.println(movies);
		}
	}
}
