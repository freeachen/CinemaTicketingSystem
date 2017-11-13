package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.CinemaListOperateMenu;

/**
 * ����Ա�Ե�ӰԺ�Ĳ�����ת
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateControl {
	CinemaListOperateService clos = new CinemaListOperateService();

	public void cinemaListControl(int choice) {
		switch (choice) {
		case 1:
			// ������ӰԺ
			clos.insertCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 2:
			// �¼ܵ�ӰԺ
			clos.deleteCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 3:
			// �޸ĵ�ӰԺ
			clos.updateCinema();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 4:
			// �鿴ָ����ӰԺ
			clos.showFromName();
			new CinemaListOperateMenu().cinemaListShow();
			break;
		case 5:
			// �鿴���е�ӰԺ
			clos.adminShowAll();
			new CinemaListOperateMenu().cinemaListShow();
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
