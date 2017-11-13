package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;

/**
 * ��¼���ܵ�ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginControl {
	LoginService loginService = new LoginService();

	public void loginControl(int choice) {
		switch (choice) {
		case 1:
			// �û���½
			loginService.userLogin();
			new LoginMenu().loginShow();
			break;
		case 2:
			// ����Ա��½
			loginService.adminLogin();
			new LoginMenu().loginShow();
			break;
		case 3:
			// �������� �һ�����
			new PwdHelpService().findPwd();
			new LoginMenu().loginShow();
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
