package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.TicketsBoughtMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * ��ת����Ʊ ����ģ�� ����ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class TicketsBoughtControl {
	private SelectSeatService tbs = new SelectSeatService();

	public void ticketsBoughtControl(int choice, int usersId) {
		switch (choice) {
		case 1:
			// ѡ�� ѡ����ɺ����ɶ���
			new SelectSeatService().selectSeat(usersId);
			new TicketsBoughtMenu().buyTicketsShow(usersId);
			break;
		case 2:
			// ȷ��֧��
			new TicketBoughtService().showOrderForm(usersId);
			new TicketsBoughtMenu().buyTicketsShow(usersId);
			break;
		case 0:
			System.out.println("������һ��˵�");
			new UserLogedOperateMenu().userLoginShow(usersId);
			break;

		default:
			break;
		}
		// ����

	}
}
