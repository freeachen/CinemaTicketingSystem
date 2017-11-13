package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;

/**
 * 登录功能的转换
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginControl {
	LoginService loginService = new LoginService();

	public void loginControl(int choice) {
		switch (choice) {
		case 1:
			// 用户登陆
			loginService.userLogin();
			new LoginMenu().loginShow();
			break;
		case 2:
			// 管理员登陆
			loginService.adminLogin();
			new LoginMenu().loginShow();
			break;
		case 3:
			// 忘记密码 找回密码
			new PwdHelpService().findPwd();
			new LoginMenu().loginShow();
			break;
		case 0:
			System.out.println("返回上一层菜单");
			new MainMenu().mainShow();
			break;

		default:
			break;
		}

	}
}
