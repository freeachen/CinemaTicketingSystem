package com.iotek.ht.service;

import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.PersonalCenterMenu;
import com.iotek.ht.view.TicketsBoughtMenu;
import com.iotek.ht.view.TouristCinemaLookedOperateMenu;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.UserLogedOperateMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

/**
 * ��ת���û���¼���������ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLoginControl {
	private PersonalCenterMenu personalCenterMenu = new PersonalCenterMenu();

	public void userLoginControl(int choice, int id, int i) {
		switch (choice) {
		case 1:
			// ��������
			personalCenterMenu.centerShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 2:
			// ֧��������
			new RechargeCenterService().recharge(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 3:
			// �ҵ�Ӱ
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 4:
			// ��ӰԺ
			new CinemaListOperateService().showAll(id, 1);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 5:
			// ��Ӱ��
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 6:
			// ��Ժ��
			new LookThroughCinemaService().userShowAll(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 7:
			// ��Ʊ
			new TicketsBoughtMenu().buyTicketsShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 0:
			// �˳���¼ʱ ����δ��ɵĶ���
			new UserLogoutService().logout(id);
			System.out.println("�˳���¼");
			new MainMenu().mainShow();
			break;

		default:
			break;
		}

	}
}
