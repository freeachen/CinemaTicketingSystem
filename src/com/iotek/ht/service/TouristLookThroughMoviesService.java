package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.LookThroughSeatMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;

/**
 * �ο������Ӱ�ľ���ʵ�ֹ��ܿ�
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesService {
	private TouristMovieLookedOperateMenu tmlom = new TouristMovieLookedOperateMenu();
	private MoviesDao moviesDao = new MoviesDao();

	/**
	 * �鿴ָ����Ӱ������Ϣ
	 */
	public void lookMoviesByName(int id, int i) {
		Movies movies = generateMovies();
		if (movies != null) {
			// ������һ��˵� ������λ�����۵Ľ���
			tmlom.touristMovieLookedOperateShow(movies.getMovieName(), id, i);
			System.out.println("********************************");
		} else {
			System.out.println("�Ѿ�������");
			System.out.println("********************************");
		}
	}

	/**
	 * �������Ӱ�����ҵ�Ӱ���ز��ҵ��ĵ�Ӱ��Ϣ
	 * 
	 * @return
	 */
	public Movies generateMovies() {
		System.out.println("********************************");
		System.out.println("������Ҫ���ҵĵ�Ӱ��(�ؼ������):");
		Scanner in = new Scanner(System.in);
		String movieName = in.next();
		System.out.println("********************************");
		Movies movies = moviesDao.selectName(movieName);
		if (movies.getId() == 0) {// ͨ�����صĵ�Ӱ��id�ж�����
			System.out.println("δ�ҵ������Ӱ-_-");
			System.out.println("********************************");
			return null;
		} else {
			System.out.println("��Ӱ��ϢΪ:");
			System.out.println(movies);
			System.out.println();
		}
		return movies;
	}

	/**
	 * �鿴���е�Ӱ��Ϣ
	 */
	public void lookAllMovies(int id, int i) {
		List<Movies> list = generateAllMovies();
		// ������һ��˵� ������λ�����۵Ľ���
		tmlom.touristMovieLookedOperateShow(movieName(list), id, i);
		System.out.println("********************************");
	}

	public List<Movies> generateAllMovies() {
		List<Movies> list = moviesDao.selectAll();
		System.out.println("���е�Ӱ��Ϣ:");
		int count = 0;
		for (Movies movies : list) {
			count++;
			System.out.println(count + "==>" + movies);
		}
		return list;
	}

	/**
	 * �鿴ָ�����͵ĵ�Ӱ
	 */
	public void lookMoviesByType(int id, int i) {
		List<Movies> list = generateCertainTypeMovies();
		if (list != null) {
			// ������һ��˵� ������λ�����۵Ľ���
			tmlom.touristMovieLookedOperateShow(movieName(list), id, i);
			System.out.println("********************************");
		} else {
			System.out.println("�Ѿ�������");
			System.out.println("********************************");
		}

	}

	/**
	 * ��ò�ѯ���͵�Ӱ�ļ���
	 * 
	 * @return
	 */
	public List<Movies> generateCertainTypeMovies() {
		new MovieTypeListMenu().movieTypeListMenu();
		String movieType = new MovieTypeListService().movieTypeListControl();
		List<Movies> list = moviesDao.seleteType(movieType);
		int count = 0;
		if (list.size() > 0) {
			System.out.println("********************************");
			System.out.println("��������Ϊ:" + movieType + "�ĵ�Ӱ:");
			for (Movies movies : list) {
				count++;
				System.out.println(count + "==>" + movies);
			}
			return list;
		} else {
			System.out.println("δ�ҵ������͵ĵ�Ӱ-_-");
			System.out.println("********************************");
		}
		return null;
	}

	/**
	 * ���������ҵĵ�Ӱ���ļ��Ͻ���ѡ��Ҫ��ѯ����λ��Ϣ
	 * 
	 * @param movieName
	 * @param list
	 * @return
	 */
	public String movieName(List<Movies> list) {
		System.out.println("\n\n*************************");
		System.out.println("��ʾ:");
		System.out.println("1--" + list.size() + "==>�鿴��λ��Ϣ������");
		System.out.println("0==>����");
		System.out.println("*************************");
		int choice = Tools.getInt(0, list.size());
		if (choice == 0) {
			new MainMenu().mainShow();
		}
		return list.get(choice - 1).getMovieName();
	}
}
