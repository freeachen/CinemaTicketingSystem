package com.iotek.ht.service;

import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * ��ת���ο������Ӱ���� ����ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesControl {
	private TouristMovieLookedOperateMenu tmlom = new TouristMovieLookedOperateMenu();
	private TouristLookThroughMoviesService ltms = new TouristLookThroughMoviesService();

	public void lookMoviesControl(int choice, int id, int i) {
		switch (choice) {
		case 1:
			// ����Ӱ�����
			ltms.lookMoviesByName(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 2:
			// ���������
			ltms.lookMoviesByType(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 3:
			// ���ȫ����Ӱ
			ltms.lookAllMovies(id, i);
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		case 0:
			if (i == 0) {
				// ������һ��
				new MainMenu().mainShow();
			} else {
				new UserLogedOperateMenu().userLoginShow(id);
			}
			break;

		default:
			break;
		}

	}
}
