package com.iotek.ht.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MovieSessionMenu;

/**
 * 对场次的操作 具体功能的实现
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionOperateService {
	// 会用到电影表 与 电影院表的数据
	private MoviesDao moviesDao = new MoviesDao();
	private CinemaDao cinemaDao = new CinemaDao();
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private static int count = 0;// 设置一个跳出状态

	/**
	 * 新增场次
	 */
	public void insertSession() {
		MovieSession movieSession = insertSessionShow();
		if (movieSessionDao.insert(movieSession)) {
			System.out.println("添加成功！");
			int id = movieSessionDao.selectId(movieSession.getCinemaId(),
					movieSession.getMovieId());
			movieSession.setId(id);
			// 新增一个场次 就产生相应的票
			new TicketsDao().generateTickets(movieSession);
		} else {
			System.out.println("添加失败！");
		}
	}

	/**
	 * 删除场次
	 */
	public void deleteSession() {
		// 显示所有场次 进行选择序号删除
		List<MovieSession> list = movieSessionDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			// 打印所有场次信息
			System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
					+ "\t");
			System.out.print(list.get(i).getAddress() + "店" + "\t");
			System.out.print(list.get(i).getHall() + "号厅" + "\t\t");
			System.out.print(list.get(i).getMovieName() + "\t\t");
			// System.out.println(list.get(i).getTime());
			String time = list.get(i).getTime();// 取出时间
			// 以固定格式输出
			printTime(time);
		}
		int choice = Tools.getInt(1, list.size());
		int id = list.get(choice - 1).getId();
		System.out.println("确定删除?");
		System.out.println("1==>放弃");
		System.out.println("2==>确定");
		int select = Tools.getInt(1, 2);
		if (select == 2) {
			// 然后根据id删除场次
			if (movieSessionDao.delete(id)) {
				System.out.println("删除成功！");
				// 删除场次时 删除产生的电影票
				new TicketsDao().deleteTickets(id);
			} else {
				System.out.println("删除失败！");
			}
		} else {
			System.out.println("您已放弃删除····");
		}
	}

	public void printTime(String time) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
		String[] str = time.split("——");
		Date begin;
		Date end;
		try {
			begin = sdf1.parse(str[0]);
			end = sdf1.parse(str[1]);
			System.out.print(sdf2.format(begin) + "——");
			System.out.println(sdf3.format(end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改场次
	 */
	public void updateSession() {
		Scanner in = new Scanner(System.in);
		// 首先打印所有场次 选择要修改的场次
		List<MovieSession> list = movieSessionDao.selectAll();
		if (list.size() != 0) {
			printSession(list);// 打印所有场次信息
			// 选择要修改的场次
			System.out.println("选择您要修改的场次:");
			int choice = Tools.getInt(1, list.size());
			int sessionId = list.get(choice - 1).getId();// 记录要修改的场次的id
			int hall = list.get(choice - 1).getHall();// 记录场厅
			int cinemaId = list.get(choice - 1).getCinemaId();// 影院id
			System.out.println("确定要修改？");
			System.out.println("1==>放弃");
			System.out.println("2==>继续");
			if (Tools.getInt(1, 2) == 2) {
				Movies movie = null;
				System.out.println("请重新录入要放映的电影:");
				System.out.println("请从中选择:");
				movie = generateMovieList();
				int movieID = movie.getId();// 作为场次表的一个外键字段存入场次表
				System.out.println("请录入放映时段:");
				// 当选择场厅后 进行时间的安排时 就要判断下
				String time = null;
				try {
					time = generateTime(cinemaId, movieID, hall);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("请输入票价:");
				double price = in.nextDouble();
				MovieSession movieSession = new MovieSession(movieID, time, 0,
						0, 0, price, 0);
				// 修改
				if (movieSessionDao.update(movieSession, sessionId)) {
					System.out.println("修改成功！");
				} else {
					System.out.println("修改失败！");
				}
			} else {
				System.out.println("您已放弃修改···");
			}
		}
	}

	/**
	 * 查看指定电影的场次
	 */
	public void showSessionFromMovie() {
		System.out.println("请输入电影名:");
		Scanner in = new Scanner(System.in);
		String movieName = in.next();
		List<MovieSession> list = movieSessionDao
				.selectSessionFromMovie(movieName);
		printSession(list);

	}

	/**
	 * 将获得的场次列表打印输出
	 * 
	 * @param list
	 */
	public void printSession(List<MovieSession> list) {
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				MovieSession m = list.get(i);
				System.out.print((i + 1) + "\t==>" + m.getCinemaName() + "\t\t"
						+ m.getHall() + "号厅" + "\t\t");
				System.out.print(m.getAddress() + "店\t《");
				System.out.print(m.getMovieName() + "》\t\t\t单价:");
				System.out.print(m.getPrice() + "元\t\t片长:");
				System.out.print(m.getLasting() + "分钟\t\t放映时间:");
				String time = m.getTime();
				printTime(time);
			}
			System.out.println();
		} else {
			System.out.println("未找到相关场次");
		}
	}

	/**
	 * 查看指定电影院的场次
	 */
	public void showSessionFromCinnema() {
		System.out.println("请选择电影院名:");
		String cinemaName = getCinemaList();
		List<MovieSession> list = movieSessionDao
				.selectSessionFromCinema(cinemaName);
		printSession(list);
	}

	/**
	 * 查看所有场次
	 */
	public void showAllSession() {
		List<MovieSession> list = movieSessionDao.selectAll();
		printSession(list);
	}

	/**
	 * 生成电影院列表 只是场次表中的电影院 进行选择电影院用
	 * 
	 * @return 电影院名
	 */
	public String getCinemaList() {
		Scanner in = new Scanner(System.in);
		TreeMap<String, String> map = movieSessionDao.getCinemaName();
		// 找到和找不到的情况需要判断
		if (map.size() != 0) {
			System.out.println("********************************");
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
			System.out.println("********************************");
			int choice = Tools.getInt(1, map.size());
			System.out.println("********************************");
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
	 * 输入场次的信息 返回场次的信息做为 新增用
	 * 
	 * @return
	 */
	private MovieSession insertSessionShow() {
		Scanner in = new Scanner(System.in);
		Movies movie = new Movies();
		Cinema cinema = new Cinema();
		System.out.println("请录入要放映的影院:");
		System.out.println("请从中选择:");
		cinema = generateCinemaList();
		int cinemaID = cinema.getId();// 作为场次表的一个外键字段存入场次表
		System.out.println("请录入要放映的电影:");
		System.out.println("请从中选择:");
		movie = generateMovieList();
		int movieID = movie.getId();// 作为场次表的一个外键字段存入场次表
		System.out.println("请录入要放映影院的场厅号:");
		// 根据影院去找对应的场厅数量 然后进行选择
		int hall = generateHallsList(cinemaID);// 记录选择的放映场厅
		System.out.println("请录入放映时段:");
		// 当选择场厅后 进行时间的安排时 就要判断下
		String time = null;
		try {
			time = generateTime(cinemaID, movieID, hall);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 这个电影院的这个场厅这个时段是否有冲突
		System.out.println("请录入场厅容纳人数:");
		int container = Integer.parseInt(in.next());
		System.out.println("请输入票价:");
		double price = in.nextDouble();
		// 初始化时 将剩余座位设置成容纳人数 表示未卖出座位的状态
		return new MovieSession(movieID, time, cinemaID, container, container,
				price, hall);

	}

	/**
	 * 安排场次时 解决场次冲突 控制时间段的选择
	 * 
	 * @param cinemaId
	 * @param movieId
	 * @param hall
	 * @return
	 * @throws ParseException
	 */
	private String generateTime(int cinemaId, int movieId, int hall)
			throws ParseException {
		Scanner in = new Scanner(System.in);
		// 根据movieid获得对应的时长
		int lasting = moviesDao.selectTime(movieId);
		// 先找到这个电影院这个厅放的电影的所有时间段
		List<String> allTime = movieSessionDao.getAllTime(cinemaId, hall);
		// 系统直接提示录入的月、日、小时、分钟字段
		System.out.println("******************");
		System.out.println("请录入起始时间:");
		System.out.println("\t\t请输入年份:");
		int year = in.nextInt();
		System.out.println("\t\t请选择月份:");
		int month = Tools.getInt(1, 12);
		System.out.println("\t\t请选择天次:");
		// 这里要有一个判断月份对应的天数的选择
		int day = Tools.getInt(1, getDays(year, month));
		System.out.println("\t\t选择小时数:");
		int hour = Tools.getInt(0, 23);
		System.out.println("\t\t选择分钟数:");
		int minute = Tools.getInt(0, 59);
		String beginTime = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute;
		// 由场次的开始时间与场次的时长来获得场次的结束时间
		String endTime = getEndTime(lasting, beginTime);
		if (allTime != null && allTime.size() != 0) {// 查到这个厅有安排过时段的情况 需要判断有无冲突
			// 遍历所得到的时间段以”——“拆开
			List<String> begins = new ArrayList<String>();// 存放开始时间
			List<String> ends = new ArrayList<String>();// 存放结束时间
			for (int i = 0; i < allTime.size(); i++) {
				begins.add(allTime.get(i).split("——")[0]);// 前部分是开始时间
				ends.add(allTime.get(i).split("——")[1]);// 后部分是结束时间
			}
			// 设置模板格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			boolean flag = true;// 表示无冲突
			Date beginDate = sdf.parse(beginTime);
			Date endDate = sdf.parse(endTime);
			for (int i = 0; i < allTime.size(); i++) {
				Date begin = sdf.parse(begins.get(i));// 获得的所有时间段的开始时间
				Date end = sdf.parse(ends.get(i));// 获得的所有时间段的结束时间
				/*
				 * 录入的开始时间在其他时间段结束时间之后 或者 录入的结束时间在其他时间段开始之前 这样就不会有冲突 否则就是有冲突的情况
				 * 那么久将 状态置为false 用来标记有冲突
				 */
				// if (!(beginDate.after(end) || endDate.before(begin))) {
				// flag = false;// 有冲突
				// break;// 只要找到冲突就跳出循环
				// }
				// 这是有冲突的情况
				if (((beginDate.after(begin)) && (endDate.before(end)))
						|| ((beginDate.before(begin)) && (endDate.after(begin)))
						|| ((beginDate.before(end)) && (endDate.after(end)))
						|| ((beginDate.before(begin)) && (endDate.after(end)))) {
					flag = false;
					break;
				}
			}
			if (flag) {// 无冲突
				count = 0;// 如果没有冲突将计数器也还原
				return beginTime + "——" + endTime;// 返回的时间以”——“分隔
			} else {
				count++;
				if (count == 2) {// 如果冲突两次就退出
					count = 0;// 同时计数器还原
					System.out.println("时间段冲突!请重新排时间：");
					System.out.println("不好意思您添加2次都失败！\n现在系统自动退出！");
					// 跳出循环
					new MovieSessionMenu().movieSessionShow();
				}
				// 有冲突循环选择2次就退出循环
				System.out.println("时间段冲突!请重新排时间：");
				generateTime(cinemaId, movieId, hall);
			}

		} // 如果发现选择的时段是没有安排过场次的 就可以直接添加 无需判断冲突
		return beginTime + "——" + endTime;// 返回的时间以”——“分隔

	}

	/**
	 * 由场次的开始时间与场次的时长来获得场次的结束时间
	 * 
	 * @param lasting
	 * @param beginTime
	 * @return
	 */
	private String getEndTime(int lasting, String beginTime) {
		long time = 0L;// 接收1970年标准时间以来到当前开始时间的毫秒数
		Date beginDate = null;
		String endTime = null;
		// 设置模板格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			beginDate = sdf.parse(beginTime);
			time = beginDate.getTime();
			// 结束时间
			beginDate.setTime((long) (time + lasting * 60 * 1000));
			endTime = sdf.format(beginDate);// 结束时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endTime;
	}

	/**
	 * 根据输入的年份与月份 返回当月天数 判断
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDays(int year, int month) {
		boolean isyear = false;
		int day = 0;
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			isyear = true;// 标志是闰年
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			if (isyear) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		default:
			break;
		}
		return day;
	}

	private int generateHallsList(int cinemaID) {
		int halls = cinemaDao.selectById(cinemaID).getHalls();
		for (int i = 0; i < halls; i++) {
			System.out.println((i + 1) + "===>" + (i + 1) + "号厅" + "<===");
		}
		int choice = Tools.getInt(1, halls);// 从场厅中选择场厅号
		System.out.println("您选择的场厅是:" + choice);
		return choice;
	}

	/**
	 * 生成电影列表进行选择 并返回所选择的电影及电影id的对象信息
	 * 
	 * @return movie
	 */
	private Movies generateMovieList() {
		// 接收选择的电影名与电影id
		Movies movie = new Movies();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();
		List<Movies> moviesList = moviesDao.selectAll();
		for (int i = 0; i < moviesList.size(); i++) {
			// 将电影表中的id 与 电影名 放进treemap中 作为键值对 一一对应
			// 这里将电影名做键 id做值 为后面通过电影名来找到id
			list.put(moviesList.get(i).getMovieName(), moviesList.get(i)
					.getId());
		}
		// 存所有电影名
		List<String> movieNameList = new ArrayList<String>();
		for (int i = 0; i < moviesList.size(); i++) {
			movieNameList.add(moviesList.get(i).getMovieName());
		}
		// 打印选项对应的电影
		for (int i = 0; i < movieNameList.size(); i++) {
			System.out
					.println((i + 1) + "===>" + movieNameList.get(i) + "<===");
		}
		int choice = Tools.getInt(1, movieNameList.size());
		// 记录做出选择对应的电影名
		String movieName = movieNameList.get(choice - 1);
		System.out.println("您选择的电影是:" + movieName);
		int id = list.get(movieName);// 根据电影名去找键 记录选择的电影名对应的电影id
		movie.setId(id);
		movie.setMovieName(movieName);
		return movie;

	}

	/**
	 * 生成电影院列表进行选择 并返回所选择的电影院及电影院id的对象信息
	 * 
	 * @return
	 */
	private Cinema generateCinemaList() {
		// 接收选择的电影院名与电影院id与电影院地址
		Cinema cinema = new Cinema();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();
		List<Cinema> cinemaList = cinemaDao.selectAll();
		for (int i = 0; i < cinemaList.size(); i++) {
			// 将电影院表中的id 与 电影院名 放进treemap中 作为键值对 一一对应
			// 这里将电影院名做键 id做值 为后面通过电影院名来找到id
			list.put(cinemaList.get(i).getCinemaName(), cinemaList.get(i)
					.getId());
		}
		// 存所有电影院名
		List<String> cinemaNameList = new ArrayList<String>();
		for (int i = 0; i < cinemaList.size(); i++) {
			cinemaNameList.add(cinemaList.get(i).getCinemaName());
		}
		// 打印选项对应的电影院
		for (int i = 0; i < cinemaNameList.size(); i++) {
			String name = cinemaNameList.get(i);
			// 根据名字 去找键值对对应的id
			int id = list.get(name);
			System.out.println((i + 1) + "===>" + name + "--"
					+ cinemaDao.selectAddress(id) + "店" + "<===");
		}
		int choice = Tools.getInt(1, cinemaNameList.size());
		// 记录做出选择对应的电影院名
		String cinemaName = cinemaNameList.get(choice - 1);
		int id = list.get(cinemaName);// 记录选择的电影院名对应的电影院id
		System.out.println("选择的电影院是:" + cinemaName + "--"
				+ cinemaDao.selectAddress(id) + "店");
		cinema.setId(id);
		cinema.setCinemaName(cinemaName);
		return cinema;

	}
}
