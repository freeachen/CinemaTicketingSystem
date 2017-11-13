package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.TicketsBoughtMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 中转到购票 功能模块 的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class TicketsBoughtControl {
	private SelectSeatService tbs = new SelectSeatService();

	public void ticketsBoughtControl(int choice, int usersId) {
		switch (choice) {
		case 1:
			// 选座 选座完成后生成订单
			new SelectSeatService().selectSeat(usersId);
			new TicketsBoughtMenu().buyTicketsShow(usersId);
			break;
		case 2:
			// 确认支付
			new TicketBoughtService().showOrderForm(usersId);
			new TicketsBoughtMenu().buyTicketsShow(usersId);
			break;
		case 0:
			System.out.println("返回上一层菜单");
			new UserLogedOperateMenu().userLoginShow(usersId);
			break;

		default:
			break;
		}
		// 返回

	}
}
