package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.MainMenu;

/**
 * 对主菜单的选择进行中转到相应操作
 * 
 * @author zhangjiaqi
 *
 */
public class MainControl {
	public void mainControl(int choice) {
		switch (choice) {
		case 1:
			// 注册
			new UserRegisterService().regist();
			new MainMenu().mainShow();
			break;
		case 2:
			// 登陆
			new LoginMenu().loginShow();
			new MainMenu().mainShow();
			break;
		case 3:
			// 浏览电影
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(0,0);
			new MainMenu().mainShow();
			break;
		case 4:
			// 浏览影院
			new CinemaListOperateService().showAll(0,0);
			new MainMenu().mainShow();
			break;
		case 0:
			System.out.println("欢迎使用影院选票系统^_^");
			System.exit(0);
			break;

		default:
			break;
		}
		
	}
}
