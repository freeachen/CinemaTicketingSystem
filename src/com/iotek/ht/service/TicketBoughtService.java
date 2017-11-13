package com.iotek.ht.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.entity.Tickets;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TicketsBoughtMenu;

/**
 * 购票的实现具体功能块
 * 
 * @author zhangjiaqi
 * 
 */
public class TicketBoughtService {
	private TicketsDao ticketsDao = new TicketsDao();
	private OrderFormDao orderFormDao = new OrderFormDao();
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private static int count = 0;// 定义一个静态变量做支付验证退出用

	/**
	 * 查看订单
	 * 
	 * @param usersId
	 */
	public void showOrderForm(int usersId) {
		List<OrderForm> list = orderFormDao.selectAll(usersId);
		boolean flag = false;// 去支付的判断状态 false 不需要支付 true 需要支付
		System.out.println("您的所有订单:");
		if (list.size() != 0) {// 如果有订单
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
					flag = true;
					System.out.println("********************************");
				} else {
					System.out.println("已支付");
					System.out.println("********************************");
				}
			}
			if (flag) {
				pay(usersId);
			} else {
				System.out.println("您没有需要支付的订单！");
			}
		} else {// 没订单
			System.out.println("对不起！您没有订单");
		}
	}

	/**
	 * 得到未完成的订单中的影票id 选择影票id 进行付款
	 */
	public void pay(int userId) {
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		// ticketsIdList 中存放的是未完成订单的 电影票id
		List<Integer> ticketsIdList = new ArrayList<Integer>();
		// 将从订单表获得的未完成订单的影票id
		for (int i = 0; i < list.size(); i++) {
			ticketsIdList.add(list.get(i).getTicketsId());
		}
		// 根据相应影票的id获得购买的场次信息
		List<Tickets> ticketsList = new ArrayList<Tickets>();
		for (int j = 0; j < ticketsIdList.size(); j++) {
			ticketsList.add(ticketsDao.getTickets(ticketsIdList.get(j)));
		}
		System.out.println();
		System.out.println("==================");
		System.out.println("选择您要支付的场次:");
		boolean flag = false;
		for (int i = 0; i < ticketsList.size(); i++) {
			Tickets tickets = ticketsList.get(i);
			System.out.print((i + 1) + "==>" + tickets.getCinemaName() + "\t");
			System.out.print(tickets.getHall() + "号厅\t");
			System.out.print(tickets.getMovieName() + "\t");
			System.out.print(tickets.getSeatNum() + "\t");
			System.out.print(tickets.getPrice() + "\t");
			if (tickets.getPrintFlag() == 0) {
				System.out.println("未打印");
				flag = true;// 表示需要进行支付操作

			} else {
				// 如果电影票已经被打印 就不需要购买
				System.out.println("已打印");
				System.out.println("无需重复付款！已经付过款！");
			}
		}
		if (flag) {
			System.out.println("0==>取消");
			// 如果电影票没有被打印 就继续购买
			int choice = Tools.getInt(0, ticketsList.size());
			if (choice != 0) {
				// 记录选择序号对应的电影票id 进而根据id去支付
				int ticketsId = ticketsIdList.get(choice - 1);
				double price = ticketsDao.getPrice(ticketsId);
				buyTicket(userId, ticketsId, price);
			} else {
				System.out.println("您已取消支付操作···");
			}
		}
	}

	/**
	 * 付款
	 * 
	 * @param userId
	 * @param sessionId
	 */
	public void buyTicket(int userId, int ticketsId, double price) {
		// 再查下自己的余额
		double remainder = new UserDao().showFromId(userId).getRemainder();
		if (price > 0) {
			// 如果余额不足 去充值
			if (remainder < price) {
				// 提醒去充值
				System.out.println("余额不足，请充值(将跳转至充值中心):");
				System.out.println("跳转中·····");
				Tools.imitateLogin();
				new RechargeCenterService().recharge(userId);
				buyTicket(userId, ticketsId, price);
			} else {
				// 充值成功 打印电影票
				Scanner in = new Scanner(System.in);
				System.out.println("支付验证:");
				System.out.println("请输入用户名:");
				String userName = in.nextLine();
				System.out.println("请输入密码:");
				String pwd = in.nextLine();
				Users user = new Users();
				user.setUserName(userName);
				user.setPwd(pwd);
				if (new UserDao().judgeLogin(user) == userId) {
					System.out.println("正在验证····");

					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("正在支付····");
					Tools.imitateLogin();
					System.out.println("支付成功！");
					System.out.println("正在打印影票····");
					// 根据影票id获得sessionid
					int sessionId = ticketsDao.getSessionId(ticketsId);
					printTicket(userId, sessionId);
					// 打印影票后 将订单的状态改变
					orderFormDao.updateFlag(userId, ticketsId);
					remainder -= price;
					// 更改用户余额
					new UserDao().updateRemainder(userId, remainder);
					// 查看场次的剩余座位数
					int left = movieSessionDao.getRemainder(sessionId);
					// 更改场次座位剩余
					movieSessionDao.updateRemainder(sessionId, left - 1);
					try {// 打印完电影票后写进文件中// 打印完电影票后写进文件中
						new FileinputTicketsBoughtService().fileIn(ticketsId);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("支付验证失败····");
					count++;
					if (count == 4) {// 支付4次就退出支付
						System.out.println("支付次数超过4次···退出购买");
						new TicketsBoughtMenu().buyTicketsShow(userId);
					}
					// 重新购买
					buyTicket(userId, ticketsId, price);
				}

			}
		}
	}

	/**
	 * 打印电影票
	 * 
	 * @param userId
	 * @param sessionId
	 */
	private void printTicket(int userId, int sessionId) {
		boolean printOk = new TicketsDao().updatePrintFlag(userId, sessionId);
		if (printOk) {
			Tools.imitateLogin();
			System.out.println("出票成功！");
			System.out.println("谢谢您的购票！祝您观影愉快^_^");
		} else {
			System.out.println("出票失败！");
		}
	}

}
