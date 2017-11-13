package com.iotek.ht.service;

import com.iotek.ht.view.OrderFormMenu;
import com.iotek.ht.view.PersonalCenterMenu;

/**
 * 中转到对订单操作的 中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormControl {
	private OrderFormService ofs = new OrderFormService();

	public void orderFormControl(int choice, int userId) {
		switch (choice) {
		case 1:
			// 查看所有订单
			ofs.showAllOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 2:
			// 查看未完成订单
			ofs.showUndoneOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 3:
			// 取消未完成订单
			ofs.deleteUndoneOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 0:
			// 返回上一层
			new PersonalCenterMenu().centerShow(userId);
			break;

		default:
			break;
		}

	}
}
