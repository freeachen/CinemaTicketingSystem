package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;

/**
 * 管理员操作电影列表方法的具体实现模块
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieListOperateService {
	private MoviesDao moviesDao = new MoviesDao();

	/**
	 * 新增电影
	 */
	public void insertMovie() {
		if (moviesDao.insert(insertShow())) {
			System.out.println("新增成功！");
		} else {
			System.out.println("新增失败！");
		}
	}

	/**
	 * 新增电影时的开始提示
	 * 
	 * @return
	 */
	public Movies insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("您现在正在增加电影列表:");
		System.out.println("请输入电影的名字:");
		String movieName = in.nextLine();
		System.out.println("请输入电影的主演:");
		String lead = in.nextLine();
		System.out.println("请输入电影的上映时间:");
		String time = in.nextLine();
		System.out.println("请输入电影的电影描述:");
		String movieDescribe = in.nextLine();
		System.out.println("请输入电影的播放时长:");
		int duration = in.nextInt();
		System.out.println("请输入电影类型");
		System.out.println("请从下列中选择:");
		String movieType = getMovieType();
		System.out.println("电影类型选择：" + movieType);
		// flag始终为1 新增电影表示上映
		return new Movies(movieName, lead, time, movieDescribe, movieType, 1,
				duration);

	}

	/**
	 * 获得电影类型
	 * 
	 * @return
	 */
	private String getMovieType() {
		new MovieTypeListMenu().movieTypeListMenu();
		return new MovieTypeListService().movieTypeListControl();

	}

	/**
	 * 删除电影 只是将其上映状态设置成0
	 */
	public void deleteMovie() {
		int id = getMovieName();
		System.out.println("确定删除?");
		System.out.println("1==>放弃");
		System.out.println("2==>确定");
		int select = Tools.getInt(1, 2);
		if (select == 2) {
			moviesDao.delete(id);
			System.out.println("下架成功！");
		} else {
			System.out.println("您放弃了删除····");
		}
	}

	/**
	 * 获得要删除电影的名字
	 * 
	 * @return
	 */
	private int getMovieName() {
		System.out.println("请选择电影的名字:");
		List<Movies> list = moviesDao.selectAll();
		int count = 0;
		for (Movies movies : list) {
			count++;
			System.out.println(count + "==>" + movies);
		}
		int choice = Tools.getInt(1, list.size());
		return list.get(choice - 1).getId();// 返回选择的电影的id进行操作
	}

	/**
	 * 修改电影信息
	 */
	public void updateMovie() {
		Movies movie = updateShow();
		if (movie != null) {
			// 执行修改操作
			if (moviesDao.update(movie)) {
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！");
			}
		}
	}

	private Movies updateShow() {
		Scanner in = new Scanner(System.in);
		int id = getMovieName();
		Movies movie = null;
		System.out.println("确定修改么？");
		System.out.println("1==>放弃");
		System.out.println("2==>继续");
		if (Tools.getInt(1, 2) == 2) {// 如果选择继续
			System.out.println("请输入新的电影主演:");
			String lead = in.next();
			System.out.println("请输入新的电影上映时间:");
			String time = in.next();
			System.out.println("请输入新的电影描述:");
			String movieDescribe = in.next();
			System.out.println("请输入新的电影类型:");
			String movieType = new MovieListOperateService().getMovieType();
			System.out.println("请输入要更改的电影放映时长:");
			int duration = in.nextInt();
			movie = new Movies(null, lead, time, movieDescribe, movieType, 1,
					duration);
			movie.setId(id);
		} else {
			System.out.println("您已放弃····");
		}
		return movie;
	}

	/**
	 * 查看电影名对应电影信息
	 */
	public void showFromName() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入要查看的电影名:");
		String movieName = in.next();
		Movies movie = moviesDao.selectName(movieName);
		if (movie.getId() != 0) {// 表示找到了这个电影
			System.out.println(movie);
		} else {
			System.out.println("没有这部电影思密达~~~");
		}
	}

	/**
	 * 查看所有电影
	 */
	public void showAll() {
		List<Movies> list = moviesDao.selectAll();
		for (Movies movies : list) {
			System.out.println(movies);
		}
	}
}
