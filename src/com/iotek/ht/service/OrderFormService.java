package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.util.Tools;

/**
 * 操作订单的具体实现
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormService {
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * 查看订单
	 * 
	 * @param usersId
	 */
	public void showAllOrder(int usersId) {
		System.out.println("********************************");
		System.out.println("您的所有订单:");
		List<OrderForm> list = printOrderForm(usersId);
		if (list == null || list.size() == 0) {
			System.out.println("您没有购票记录");
			System.out.println("********************************");
		}
	}

	/**
	 * 查看未完成订单
	 * 
	 * @param userId
	 */
	public void showUndoneOrder(int userId) {
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		if (list != null && list.size() != 0) {
			getUnOrder(list, 0);
		} else {
			System.out.println("您没有未完成的订单！");
		}
		System.out.println("********************************");

	}

	/**
	 * 取消订单
	 * 
	 * @param userId
	 */
	public void deleteUndoneOrder(int userId) {
		// 获取未完成的订单
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		int count = 0;// 统计多少未完成的订单
		// 如果有未完成的订单 进行选择取消
		if (list.size() != 0) {
			count = getUnOrder(list, count);
			System.out.println("********************************");
			// 进行选择未支付的订单序号 进而进行选择取消
			int choice = Tools.getInt(1, count);
			int orderId = list.get(choice - 1).getId();// 记录选择选项对应的订单id
			int ticketsId = list.get(choice - 1).getTicketsId();// 记录选择选项对应的影票id
			System.out.println("确定取消订单么?");
			System.out.println("1==>放弃");
			System.out.println("2==>确定");
			int select = Tools.getInt(1, 2);
			if (select == 2) {
				// 然后取消订单表 取消选座表
				// 取消订单表
				deleteOrder(orderId);
				// 取消选座
				deleteSeatSelected(ticketsId);
			} else {
				System.out.println("您已取消订单···");
			}
		} else {
			System.out.println("您没有未完成的订单哦");
		}

	}

	/**
	 * 获得未完成订单的个数 并打印出未完成订单
	 * 
	 * @param list
	 * @param count
	 * @return
	 */
	private int getUnOrder(List<OrderForm> list, int count) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOrderFlag() == 0) {
				count++;
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t");
				System.out.print(list.get(i).getMovieName() + "\t");
				System.out.print(list.get(i).getPrice() + "\t");
				System.out.println("未支付");

			}
		}
		return count;
	}

	/**
	 * 取消选座 退电影票
	 * 
	 * @param ticketsId
	 */
	public void deleteSeatSelected(int ticketsId) {
		if (new TicketsDao().deleteSeat(ticketsId)) {
			System.out.println("选座取消成功！");
		} else {
			System.out.println("选座取消失败！");
		}
	}

	/**
	 * 取消订单
	 * 
	 * @param orderId
	 */
	public void deleteOrder(int orderId) {
		if (orderFormDao.delete(orderId)) {
			System.out.println("订单取消成功!");
		} else {
			System.out.println("订单取消失败!");
		}
	}

	private List<OrderForm> printOrderForm(int usersId) {
		List<OrderForm> list = orderFormDao.selectAll(usersId);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t" + list.get(i).getHall() + "号厅" + "\t");
				System.out.print(list.get(i).getAddress() + "店\t\t《");
				System.out.print(list.get(i).getMovieName() + "》\t\t"
						+ list.get(i).getSeat() + "号座\t\t单价:");
				System.out.print(list.get(i).getPrice() + "\t播放时间:");
				String time = list.get(i).getTime();
				new MovieSessionOperateService().printTime(time);
				if (list.get(i).getOrderFlag() == 0) {
					System.out.println("未支付");
					System.out.println("********************************");
				} else {
					System.out.println("已支付");
					System.out.println("********************************");
				}
			}
			return list;
		}
		return null;
	}

}
