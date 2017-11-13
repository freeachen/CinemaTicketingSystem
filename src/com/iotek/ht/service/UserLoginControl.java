package com.iotek.ht.service;

import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.PersonalCenterMenu;
import com.iotek.ht.view.TicketsBoughtMenu;
import com.iotek.ht.view.TouristCinemaLookedOperateMenu;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.UserLogedOperateMenu;
import com.iotek.ht.view.UserLookThroughMoviesReviewMenu;

/**
 * 中转到用户登录后操作的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLoginControl {
	private PersonalCenterMenu personalCenterMenu = new PersonalCenterMenu();

	public void userLoginControl(int choice, int id, int i) {
		switch (choice) {
		case 1:
			// 个人中心
			personalCenterMenu.centerShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 2:
			// 支付宝中心
			new RechargeCenterService().recharge(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 3:
			// 找电影
			new TouristLookThroughMoviesMenu().lookThroughMoviesShow(id, i);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 4:
			// 搜影院
			new CinemaListOperateService().showAll(id, 1);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 5:
			// 读影评
			new UserLookThroughMoviesReviewMenu().userLookThroughMoivesShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 6:
			// 看院评
			new LookThroughCinemaService().userShowAll(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 7:
			// 购票
			new TicketsBoughtMenu().buyTicketsShow(id);
			new UserLogedOperateMenu().userLoginShow(id);
			break;
		case 0:
			// 退出登录时 提醒未完成的订单
			new UserLogoutService().logout(id);
			System.out.println("退出登录");
			new MainMenu().mainShow();
			break;

		default:
			break;
		}

	}
}
