package com.iotek.ht.service;

import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;

/**
 * ��ת�� �ο��������Ӱ��Ĳ��� δ��½ʱ ����ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristMovieLookedOperateControl {
	private TouristMovieLookedOperateService tmlos = new TouristMovieLookedOperateService();

	public void touristMovieLookedOperateControl(int choice, String movieName,
			int id, int i) {
		switch (choice) {
		case 1:
			// �鿴������Ϣ
			tmlos.lookThrough(movieName, 0);// ״̬0��ʾ�鿴���ǵ�Ӱ
			new TouristMovieLookedOperateMenu().touristMovieLookedOperateShow(
					movieName, id, i);
			break;
		case 2:
			// ����Ӱ����
			tmlos.readMovieReview(movieName);
			new TouristMovieLookedOperateMenu().touristMovieLookedOperateShow(
					movieName, id, i);
			break;
		case 0:
			// ������һ��
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			break;
		default:
			break;
		}

	}
}
