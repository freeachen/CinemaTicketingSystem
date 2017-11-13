package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.MovieListOperateMenu;

/**
 * ����Ա�Ե�Ӱ�б�Ĳ��� ����ת�� ת����Ӧʵ�ַ���
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieListOperateControl {
	MovieListOperateService mlos = new MovieListOperateService();

	public void movieListControl(int choice) {
		switch (choice) {
		case 1:
			// ������Ӱ
			mlos.insertMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 2:
			// ɾ����Ӱ
			mlos.deleteMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 3:
			// �޸ĵ�Ӱ
			mlos.updateMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 4:
			// �鿴ָ����Ӱ
			mlos.showFromName();
			new MovieListOperateMenu().movieListShow();
			break;
		case 5:
			// �鿴���е�Ӱ
			mlos.showAll();
			new MovieListOperateMenu().movieListShow();
			break;
		case 0:
			System.out.println("������һ��");
			new AdminLoginOperateMenu().adminLoginShow();
			break;

		default:
			break;
		}

	}
}
