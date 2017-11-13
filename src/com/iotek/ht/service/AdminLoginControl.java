package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.CinemaListOperateMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieListOperateMenu;
import com.iotek.ht.view.MovieSessionMenu;
/**
 * 管理员登录操作
 * @author Administrator
 *
 */
public class AdminLoginControl {
	public void adminLoginControl(int choice) {
		switch (choice) {
		case 1:
			// 编辑电影列表
			new MovieListOperateMenu().movieListShow();
			new AdminLoginOperateMenu().adminLoginShow();
			break;
		case 2:
			// 编辑影院列表
			new CinemaListOperateMenu().cinemaListShow();
			new AdminLoginOperateMenu().adminLoginShow();
			break;
		case 3:
			// 编辑场次列表
			new MovieSessionMenu().movieSessionShow();
			new AdminLoginOperateMenu().adminLoginShow();
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
