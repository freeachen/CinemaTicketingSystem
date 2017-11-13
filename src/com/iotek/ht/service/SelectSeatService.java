package com.iotek.ht.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.entity.Tickets;
import com.iotek.ht.util.Tools;

/**
 * 选座的实现功能块
 * 
 * @author zhangjiaqi
 * 
 */
public class SelectSeatService {
	// 用到场次表的数据库操作
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private MovieSessionOperateService msos = new MovieSessionOperateService();
	// 需要操作票表
	private TicketsDao ticketsDao = new TicketsDao();
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * 选座
	 * 
	 * @param userId
	 */
	public void selectSeat(int userId) {
		int sessionId = seleteSession(userId);
		if (sessionId > 0) {
			// 判断是否已经被选完座位
			if (movieSessionDao.getRemainder(sessionId) == 0) {
				System.out.println("********************************");
				System.out.println("满场！谢谢光临！");
				System.out.println("********************************");
			} else {
				chooseSeatNumber(userId, sessionId);
			}
		} else {
			System.out.println("********************************");
			System.out.println("对不起！没有影票信息！");
			System.out.println("********************************");
		}
	}

	// 选座的具体操作
	private void chooseSeatNumber(int userId, int sessionId) {
		Tickets ticket = new Tickets();// 用来保存信息
		// 同一个用户只能选一个座位
		int id = ticketsDao.selectId(userId, sessionId);
		if (id > 0) {
			System.out.println("********************************");
			System.out.println("您已经买过一张本场次的票！\n一人只能选一个座位哦！");
			System.out.println("********************************");
		} else {
			// 打印座位状态进行选座
			showSeatInformation(sessionId, userId);
			int count = 5;// 连续选5次座位不成功提示退出语句
			while (true) {
				count--;
				if (count == 0) {
					System.out.println("********************************");
					System.out.println("可以输入0退出哦");
					Scanner in = new Scanner(System.in);
					if (in.nextInt() == 0) {
						break;
					}
				}
				int seatNum = insertSelect(userId, sessionId);
				if (seatNum == -1) {// 此座位不可以购买
					System.out.println("********************************");
					System.out.println("这个座位已经被买了！请重新选择：");

				} else if (seatNum > 0) {// 可以购买 未被选过
					ticket.setUserId(userId);
					ticket.setSessionId(sessionId);
					ticket.setSeatNum(seatNum);
					if (ticketsDao.insertTicket(ticket)) {
						System.out.println("这个座位还未被选哦！快去付款吧！");
						System.out.println("选座成功！请去支付！打印影票！");
						System.out.println("********************************");
						// 生成订单
						generateOrderForm(userId, sessionId);
						break;
					} else {
						System.out.println("没有这个座位哦！");
						System.out.println("选票失败！");
						System.out.println("********************************");
					}
				}
				// 选票成功后 意思就是 已经将信息存入票表了 转去购买
			}
		}

	}

	private void generateOrderForm(int userId, int sessionId) {
		// 获得影票id
		int ticketsId = ticketsDao.selectId(userId, sessionId);
		// 默认订单的状态为0 表示未支付
		orderFormDao.insert(new OrderForm(userId, ticketsId, 0));
	}

	/**
	 * 用户选择场次 返回场次的id
	 * 
	 * @param userId
	 * @return
	 */
	public int seleteSession(int userId) {
		System.out.println("********************************");
		System.out.println("请进行以下选择:");
		// 调用操作电影场次类中的选择影院名 电影名 时段的方法
		String cinemaName = getCinemaName();
		if (cinemaName != null) {
			System.out.println("选择的影院:" + cinemaName);
			System.out.println("————————");
			// 根据这个电影院名 去找放映的电影
			String movieName = getMovieName(cinemaName);
			System.out.println("选择的电影:" + movieName);
			System.out.println("————————");
			// 根据这个电影院名 与 电影名去找放映时段
			String time = getTime(cinemaName, movieName);
			System.out.print("选择的时段:");
			new MovieSessionOperateService().printTime(time);
			System.out.println("————————");
			// 根据上面三个去找放映的影厅
			int hall = getHall(cinemaName, movieName, time);
			System.out.println("选择的场厅:" + hall + "号厅");
			System.out.println("————————");
			// 再调用场次表操作中的根据上面四条信息查找场次id的方法
			int sessionId = movieSessionDao.selectId(movieName, cinemaName,
					time, hall);
			// 获得场次的id后 将用户id 与场次 id传递到选座方法中 进行录入影票表操作
			return sessionId;
		}
		// 如果没找到场次的信息
		return -1;
	}

	/**
	 * 获得电影院的名字进行选择
	 * 
	 * @return
	 */
	public String getCinemaName() {
		Scanner in = new Scanner(System.in);
		TreeMap<String, String> map = movieSessionDao.getCinemaName();
		// 找到和找不到的情况需要判断
		if (map.size() != 0) {
			System.out.println("********************************\n");
			System.out.println("请选择电影院:");
			// 如果找到了添加到场次的电影院
			// 打印选项对应的电影院
			Set<Entry<String, String>> entrySet = map.entrySet();
			int count = 0;
			for (Entry<String, String> entry : entrySet) {
				count++;
				System.out.println(count + "===>" + entry.getValue() + "--"
						+ entry.getKey() + "<===");
			}
			System.out.println("\n********************************");
			int choice = Tools.getInt(1, map.size());
			// 记录做出选择对应的电影院
			int location = 0;
			String cinemaName = null;
			for (Entry<String, String> entry : entrySet) {
				location++;
				if (location == choice) {
					cinemaName = entry.getValue();
				}
			}
			return cinemaName;
		}
		// 如果没找到返回空值
		return null;

	}

	/**
	 * 生成电影列表选项 进行选择电影
	 * 
	 * @return 电影名
	 */
	public String getMovieName(String cinemaName) {
		Scanner in = new Scanner(System.in);
		List<String> list = movieSessionDao.getMovies(cinemaName);
		if (list != null && list.size() != 0) {
			System.out.println("********************************\n");
			System.out.println("请选择电影:");
			// 打印选项对应的电影
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "===>" + list.get(i) + "<===");
			}
			System.out.println("\n********************************");
			int choice = Tools.getInt(1, list.size());
			// 记录做出选择对应的电影名
			return list.get(choice - 1);
		}
		return null;
	}

	/**
	 * 生成时间列表 进行选择时间
	 * 
	 * @return
	 */
	public String getTime(String cinemaName, String movieName) {
		Scanner in = new Scanner(System.in);
		List<String> list = movieSessionDao.getTime(cinemaName, movieName);
		System.out.println("********************************\n");
		System.out.println("请选择时间段:");
		// 打印选项对应的时段
		for (int i = 0; i < list.size(); i++) {
			String time = list.get(i);
			System.out.print((i + 1) + "===>");
			new MovieSessionOperateService().printTime(time);
		}
		System.out.println("\n********************************");
		int choice = Tools.getInt(1, list.size());
		// 记录做出选择对应的时段
		return list.get(choice - 1);
	}

	/**
	 * 生成场厅列别进行选择场厅
	 * 
	 * @param cinemaName
	 * @param movieName
	 * @param time
	 * @return
	 */
	public int getHall(String cinemaName, String movieName, String time) {
		List<Integer> list = movieSessionDao.selectHall(movieName, cinemaName,
				time);
		System.out.println("********************************\n");
		System.out.println("请选择场厅:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "===>" + list.get(i) + "号厅" + "<===");
		}
		System.out.println("\n********************************");
		int choice = Tools.getInt(1, list.size());// 从场厅中选择场厅号
		return list.get(choice - 1);
	}

	/**
	 * 输入选座信息 并判断座位是否可选 返回选的座位
	 * 
	 * @param userId
	 * @param sessionId
	 * @return
	 */
	private int insertSelect(int userId, int sessionId) {
		System.out.println("********************************\n");
		System.out.println("请输入要选的座位号：");
		int seatNum = Tools.getInt(1, getContainer(sessionId));
		// 判断输入的座位号是否为可选
		int flag = -2;
		Tickets tickets = ticketsDao.selectSeatFlag(sessionId, seatNum);
		if (tickets.getSeatFlag() == 1) {
			flag = -1;// 表示這個座位已经被选
		} else if (tickets.getSeatFlag() == 0) {
			flag = seatNum;// 表示这个座位未被选择过 可以选择
		}
		return flag;
	}

	/**
	 * 根据场次id 获得电影票的总座位数
	 * 
	 * @param sessionId
	 * @return
	 */
	private int getContainer(int sessionId) {
		return movieSessionDao.getContainer(sessionId);
	}

	/**
	 * 根据场次id 获得场次电影票的剩下座位
	 * 
	 * @param sessionId
	 * @return
	 */
	private int getRemainder(int sessionId) {
		return movieSessionDao.getRemainder(sessionId);
	}

	/**
	 * 打印座位状态信息， 不可选座位 可选座位
	 */
	public void showSeatInformation(int sessionId, int userId) {

		TreeMap<Integer, String> map1 = new TreeMap<Integer, String>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		List<Integer> list = ticketsDao.getSeatNum(sessionId);
		// 将可被选的座位号 放进一个TreeMap中 格式为：座位号(不可选)
		for (int i = 0; i < list.size(); i++) {
			map1.put(list.get(i), "(可选)");
		}
		// 将不可被选的座位号 放进另外一个TreeMap中 格式为：座位号(不可选)
		// 获得该场次的容量
		int container = movieSessionDao.getContainer(sessionId);
		for (int i = 1; i <= container; i++) {
			boolean isIn = false;// 设置一个状态 过滤掉包含在map1中的数字
			// 遍历不可被选的座位号 过滤掉 根据状态
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == i) {
					isIn = true;// 如果i是不可被选的座位号 就过滤掉 将isIn置为true
				}
			}
			if (!isIn) {
				map2.put(i, "(不可选)");
			}
		}
		// 然后将两个数组合并 重新存到一个map容器中 存进去的时候会按 数字自然排序
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		// 遍历两个map容器 取出键值对存到新的map中
		// 遍历不可选座位号的容器
		Set<Entry<Integer, String>> set1 = map1.entrySet();
		for (Entry<Integer, String> entry : set1) {
			map.put(entry.getKey(), entry.getValue());
		}
		// 遍历可选的座位号的容器
		Set<Entry<Integer, String>> set2 = map2.entrySet();
		for (Entry<Integer, String> entry : set2) {
			map.put(entry.getKey(), entry.getValue());
		}
		// 打印座位状态信息
		int count = 6;// 打印6个数字 换一行
		Set<Entry<Integer, String>> set = map.entrySet();
		System.out.println("\n");
		for (Entry<Integer, String> entry : set) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + "\t");
			count--;
			if (count == 0) {
				count = 6;
				System.out.println();
			}
		}
		System.out.println("\n");
	}
}
