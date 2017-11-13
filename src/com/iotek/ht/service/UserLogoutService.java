package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TicketsBoughtMenu;

/**
 * 用户退出时的操作 需要提醒未完成的订单
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLogoutService {
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * 用户退出时 提醒未完成的订单 如果不操作直接退出 就取消订单
	 * 
	 * @param id
	 */
	public void logout(int id) {
		// 查看是否有未完成的订单
		List<OrderForm> list = orderFormDao.selectUndone(id);
		if (list.size() != 0) {
			// 如果有未完成的订单 给予提醒
			System.out.println("您还有未完成的订单:");
			for (int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t");
				System.out.print(list.get(i).getMovieName() + "\t");
				System.out.print(list.get(i).getPrice() + "\t");
				System.out.println("未支付");
			}
			System.out.println("您是否想去处理(请选择):");
			System.out.println("1==>处理<==");
			System.out.println("2==>直接去取消<==");
			int choice = Tools.getInt(1, 2);
			if (choice == 1) {
				// 去处理未完成的订单 转到购买那一页
				new TicketsBoughtMenu().buyTicketsShow(id);
			} else {
				new OrderFormService().deleteUndoneOrder(id);
				System.out.println("退出中·····");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
