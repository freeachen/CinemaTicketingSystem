package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.MovieSessionMenu;

/**
 * 中转到场次操作功能块的 中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionOperateControl {
	private MovieSessionOperateService msos = new MovieSessionOperateService();

	public void movieSessionControl(int choice) {
		switch (choice) {
		case 1:
			// 新增场次
			msos.insertSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 2:
			// 删除场次
			msos.deleteSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 3:
			// 修改场次
			msos.updateSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 4:
			// 查看指定电影场次
			msos.showSessionFromMovie();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 5:
			// 查看指定电影院场次
			msos.showSessionFromCinnema();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 6:
			// 查看所有场次
			msos.showAllSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 0:
			System.out.println("返回上一层菜单");
			new AdminLoginOperateMenu().adminLoginShow();
			break;

		default:
			break;
		}

	}
}
