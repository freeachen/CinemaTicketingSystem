package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.util.Tools;

/**
 * 不管有没有登陆 都可以查看场次的座位信息 实现查看座位信息的功能块
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughSeatsService {
	/**
	 * 根据传来的电影院 或者 电影 查看所有场次的座位信息
	 * 
	 * @param id
	 */
	public void lookThrough(String name, int flag) {
		// 当浏览电影院是 选择电影院 传递过来电影院名字和状态1 进行查询这个电影院的场次
		if (flag == 1) {// 表示这个是电影院
			// 根据影院名 得到影院相应的场次id
			int sessionId = getSessionIdByCinemaName(name);
			if (sessionId > 0) {
				System.out.println("************************");
				System.out.println("场次:");
				printSelectSession(sessionId);
				System.out.print("座位表:");
				// 调用selectSeatService里打印座位表的方法
				new SelectSeatService().showSeatInformation(sessionId, 1);
			} else {
				System.out.println("没有这个影院的场次哦！");
			}
		}
		// 当浏览的是电影 选择电影 传递过来电影名字和状态0 查询放映这个电影的电影院信息进行场次查询
		if (flag == 0) {// 表示这个是电影
			// 根据电影名 得到影院相应的场次id
			int sessionId = getSessionIdByMovieName(name);
			// 判断下有没有这个场次
			if (sessionId > 0) {// 如果有这个场次 就打印座位表
				System.out.println("************************");
				System.out.println("场次:");
				printSelectSession(sessionId);
				System.out.print("座位表:");
				// 调用selectSeatService里打印座位表的方法
				new SelectSeatService().showSeatInformation(sessionId, 0);
			} else {// 如果没有这个场次 输出没有场次
				System.out.println("没有这个电影的场次哦！");
			}
		}
	}

	private void printSelectSession(int sessionId) {
		// 打印所选场次的信息
		List<MovieSession> sessionList = new MovieSessionDao()
				.selectSession(sessionId);
		// 调用场次操作中打印场次信息的方法
		new MovieSessionOperateService().printSession(sessionList);
	}

	/**
	 * 根据电影院名 调用选座selectSeatService里的获得电影列表与时段列表的功能
	 * 
	 * @param cinemaName
	 * @return
	 */
	private int getSessionIdByCinemaName(String cinemaName) {
		String movieName = new SelectSeatService().getMovieName(cinemaName);
		if (movieName != null) {
			System.out.println("选择的电影:" + movieName);
			// 根据这个电影院名 与 电影名去找放映时段
			String time = new SelectSeatService()
					.getTime(cinemaName, movieName);
			System.out.println("选择的时段:" + time);
			int hall = new SelectSeatService().getHall(cinemaName, movieName,
					time);
			System.out.println("选择的场厅:" + hall + "号厅");
			// 再调用场次表操作中的根据上面三条信息查找场次id的方法
			int sessionId = new MovieSessionDao().selectId(movieName,
					cinemaName, time, hall);
			// 获得场次的id后 将用户id 与场次 id传递到选座方法中 进行录入影票表操作
			return sessionId;
		}
		return -1;
	}

	/**
	 * 根据电影院名 调用选座selectSeatService里的获得电影列表与时段列表的功能
	 * 
	 * @param cinemaName
	 * @return
	 */
	private int getSessionIdByMovieName(String movieName) {
		// 根据电影名获得关于这个电影的场次列表
		List<MovieSession> list = new MovieSessionDao()
				.selectSessionFromMovie(movieName);
		if (list != null && list.size() != 0) {
			new MovieSessionOperateService().printSession(list);
			// 选择对应的序号 进行选择场次查看座位信息
			int choice = Tools.getInt(1, list.size());
			int sessionId = list.get(choice - 1).getId();// 接收相应序号对应场次对象的场次id
			return sessionId;
		}
		return -1;// 如果没找到场次信息 返回-1；
	}
}
