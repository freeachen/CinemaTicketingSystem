package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.CinemaReviewMenu;
import com.iotek.ht.view.LookThroughSeatMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;
import com.iotek.ht.view.TouristCinemaLookedOperateMenu;

/**
 * 管理员对影院的操作 的实现方法
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateService {
	private CinemaDao cinemaDao = new CinemaDao();

	/**
	 * 新增电影院
	 */
	public void insertCinema() {
		Cinema cinema = insertShow();
		if (cinema == null) {
			System.out.println("这个电影院已经存在！无需添加！");
		} else if (cinemaDao.insert(cinema)) {
			System.out.println("新增成功！");
		} else {
			System.out.println("新增失败！");
		}
	}

	/**
	 * 新增电影院时的开始提示
	 * 
	 * @return
	 */
	public Cinema insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("您现在正在增加电影院列表:");
		System.out.println("请输入电影院的名字:");
		String cinemaName = in.nextLine();
		System.out.println("请输入电影院的地址:");
		String cinemaAddress = in.nextLine();
		System.out.println("请输入场厅的数量:");
		int halls = in.nextInt();
		// 设置一个状态记录是否存在这个电影院
		boolean isTrue = cinemaDao.judgeExist(cinemaName, cinemaAddress);
		if (isTrue) {
			return null;
		}
		return new Cinema(cinemaName, cinemaAddress, halls);

	}

	/**
	 * 删除电影院
	 */
	public void deleteCinema() {
		String cinemaName = deleteShow();
		// 判断有没有这个电影院的id
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// 找到信息了
			System.out.println("找到的信息:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
			if (cinemaList.size() != 1) {// 找到多条信息的情况 需要选择删除
				System.out.println("选择您要查找的电影:");
				int choice = Tools.getInt(1, cinemaList.size());
				int id = cinemaList.get(choice - 1).getId();
				deleteCinema(id);
			} else {
				// 如果只有一条
				deleteCinema(cinemaList.get(0).getId());
			}
		} else {
			System.out.println("未找到相应信息！");
		}
	}

	private void deleteCinema(int id) {
		System.out.println("确定要删除么？");
		System.out.println("1==>放弃");
		System.out.println("0==>继续");
		if (Tools.getInt(0, 1) == 0) {
			// 如果有的话
			if (cinemaDao.delete(id)) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
		} else {
			System.out.println("您已放弃删除····");
		}
	}

	/**
	 * 获得要删除电影院的名字
	 * 
	 * @return
	 */
	private String deleteShow() {
		System.out.println("请输入要删除的电影院的名字:");
		Scanner in = new Scanner(System.in);
		String cinemaName = in.next();
		return cinemaName;
	}

	/**
	 * 修改电影信息
	 */
	public void updateCinema() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入要改的电影院的名字:");
		String cinemaName = in.next();
		// 根据电影院名去遍历所有符合这个名字的电影院 返回一个电影院集合
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// 找到信息了
			System.out.println("找到的信息:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
			if (cinemaList.size() != 1) {// 找到多条信息的情况 需要选择删除
				System.out.println("选择您要查找的电影:");
				int choice = Tools.getInt(1, cinemaList.size());
				int id = cinemaList.get(choice - 1).getId();// 根据id去修改信息
				insertNewCinema(id);
			} else {
				// 如果只有一条
				insertNewCinema(cinemaList.get(0).getId());
			}
		} else {
			System.out.println("未找到相应信息！");
		}

	}

	/**
	 * 选择要修改的影院后的操作
	 * 
	 * @param id
	 */
	private void insertNewCinema(int id) {
		System.out.println("确定要修改？");
		System.out.println("1==>放弃");
		System.out.println("0==>继续");
		if (Tools.getInt(0, 1) == 0) {// 继续
			Scanner in = new Scanner(System.in);
			System.out.println("请输入新的电影院名字:");
			String newCinemaName = in.next();
			System.out.println("请输入新的电影院上地址:");
			String newCinemaAddress = in.next();
			System.out.println("请输入新的场厅数量:");
			int newHalls = in.nextInt();
			// 表示存在该电影院 进而修改
			if (cinemaDao.update(id, new Cinema(newCinemaName,
					newCinemaAddress, newHalls))) {
				// 修改成功
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！");
			}
		} else {
			System.out.println("您已放弃修改···");
		}

	}

	/**
	 * 查看电影名对应电影信息
	 */
	public void showFromName() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入要查看的电影院名:");
		String cinemaName = in.next();
		// 根据电影院名去遍历所有符合这个名字的电影院 返回一个电影院集合
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// 找到信息了
			System.out.println("找到的信息:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
		}else {
			System.out.println("未找到相关信息");
		}
	}

	/**
	 * 用户与游客查看所有电影院
	 */
	public void showAll(int id,int flag) {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "店");
			}
			// 调用selectSeatService里打印座位表的方法
			new TouristCinemaLookedOperateMenu()
					.touristCinemaLookedOperateShow(cinemaName(list),id,flag);
		} else {
			System.out.println("没有这个影院的场次座位表哦");
		}
	}

	/**
	 * 管理员查看所有电影院
	 */
	public void adminShowAll() {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "店");
			}
			System.out.println("*********************\n");
		} else {
			System.out.println("没有这个影院的场次座位表哦");
		}
	}

	/**
	 * 传进来查找的电影院名的集合进行选择要查询的座位信息
	 * 
	 * @param list
	 * @return
	 */
	public Cinema cinemaName(List<Cinema> list) {
		System.out.println("\n\n*************************");
		System.out.println("提示:\n选择相应电影院查看座位表与影评\n选择0==>返回");
		System.out.println("*************************");
		int choice = Tools.getInt(0, list.size());
		if (choice == 0) {
			new MainMenu().mainShow();
		}
		return list.get(choice - 1);
	}
}
