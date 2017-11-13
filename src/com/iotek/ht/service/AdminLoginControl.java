package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.CinemaListOperateMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieListOperateMenu;
import com.iotek.ht.view.MovieSessionMenu;
/**
 * ����Ա��¼����
 * @author Administrator
 *
 */
public class AdminLoginControl {
	public void adminLoginControl(int choice) {
		switch (choice) {
		case 1:
			// �༭��Ӱ�б�
			new MovieListOperateMenu().movieListShow();
			new AdminLoginOperateMenu().adminLoginShow();
			break;
		case 2:
			// �༭ӰԺ�б�
			new CinemaListOperateMenu().cinemaListShow();
			new AdminLoginOperateMenu().adminLoginShow();
			break;
		case 3:
			// �༭�����б�
			new MovieSessionMenu().movieSessionShow();
			new AdminLoginOperateMenu().adminLoginShow();
			break;
		case 0:
			System.out.println("������һ��˵�");
			new MainMenu().mainShow();
			break;

		default:
			break;
		}
		
	}
}
