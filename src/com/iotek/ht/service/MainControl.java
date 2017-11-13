package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.MainMenu;

/**
 * �����˵���ѡ�������ת����Ӧ����
 * 
 * @author zhangjiaqi
 *
 */
public class MainControl {
	public void mainControl(int choice) {
		switch (choice) {
		case 1:
			// ע��
			new UserRegisterService().regist();
			new MainMenu().mainShow();
			break;
		case 2:
			// ��½
			new LoginMenu().loginShow();
			new MainMenu().mainShow();
			break;
		case 3:
			// �����Ӱ
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(0,0);
			new MainMenu().mainShow();
			break;
		case 4:
			// ���ӰԺ
			new CinemaListOperateService().showAll(0,0);
			new MainMenu().mainShow();
			break;
		case 0:
			System.out.println("��ӭʹ��ӰԺѡƱϵͳ^_^");
			System.exit(0);
			break;

		default:
			break;
		}
		
	}
}
