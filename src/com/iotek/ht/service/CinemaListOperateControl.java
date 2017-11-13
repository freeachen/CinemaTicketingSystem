package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.CinemaListOperateMenu;

/**
 * 管理员对电影院的操作跳转
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateControl {
	CinemaListOperateService clos = new CinemaListOperateService();

	public void cinemaListControl(int choice) {
		switch (choice) {
		case 1:
			// 新增电影院
			clos.insertCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 2:
			// 下架电影院
			clos.deleteCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 3:
			// 修改电影院
			clos.updateCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 4:
			// 查看指定电影院
			clos.showFromName();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 5:
			// 查看所有电影院
			clos.adminShowAll();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 0:
			System.out.println("返回上一层");
			new AdminLoginOperateMenu().adminLoginShow();
			break;

		default:
			break;
		}

	}
}
