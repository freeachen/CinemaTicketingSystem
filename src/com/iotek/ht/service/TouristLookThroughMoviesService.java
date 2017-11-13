package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TouristLookThroughMoviesMenu;
import com.iotek.ht.view.LookThroughSeatMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;
import com.iotek.ht.view.TouristMovieLookedOperateMenu;

/**
 * 游客浏览电影的具体实现功能块
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesService {
	private TouristMovieLookedOperateMenu tmlom = new TouristMovieLookedOperateMenu();
	private MoviesDao moviesDao = new MoviesDao();

	/**
	 * 查看指定电影名的信息
	 */
	public void lookMoviesByName(int id, int i) {
		Movies movies = generateMovies();
		if (movies != null) {
			// 进入下一层菜单 进行座位与评论的界面
			tmlom.touristMovieLookedOperateShow(movies.getMovieName(), id, i);
			System.out.println("********************************");
		} else {
			System.out.println("已经尽力了");
			System.out.println("********************************");
		}
	}

	/**
	 * 按输入电影名查找电影返回查找到的电影信息
	 * 
	 * @return
	 */
	public Movies generateMovies() {
		System.out.println("********************************");
		System.out.println("请输入要查找的电影名(关键字亦可):");
		Scanner in = new Scanner(System.in);
		String movieName = in.next();
		System.out.println("********************************");
		Movies movies = moviesDao.selectName(movieName);
		if (movies.getId() == 0) {// 通过返回的电影的id判断有无
			System.out.println("未找到这个电影-_-");
			System.out.println("********************************");
			return null;
		} else {
			System.out.println("电影信息为:");
			System.out.println(movies);
			System.out.println();
		}
		return movies;
	}

	/**
	 * 查看所有电影信息
	 */
	public void lookAllMovies(int id, int i) {
		List<Movies> list = generateAllMovies();
		// 进入下一层菜单 进行座位与评论的界面
		tmlom.touristMovieLookedOperateShow(movieName(list), id, i);
		System.out.println("********************************");
	}

	public List<Movies> generateAllMovies() {
		List<Movies> list = moviesDao.selectAll();
		System.out.println("所有电影信息:");
		int count = 0;
		for (Movies movies : list) {
			count++;
			System.out.println(count + "==>" + movies);
		}
		return list;
	}

	/**
	 * 查看指定类型的电影
	 */
	public void lookMoviesByType(int id, int i) {
		List<Movies> list = generateCertainTypeMovies();
		if (list != null) {
			// 进入下一层菜单 进行座位与评论的界面
			tmlom.touristMovieLookedOperateShow(movieName(list), id, i);
			System.out.println("********************************");
		} else {
			System.out.println("已经尽力了");
			System.out.println("********************************");
		}

	}

	/**
	 * 获得查询类型电影的集合
	 * 
	 * @return
	 */
	public List<Movies> generateCertainTypeMovies() {
		new MovieTypeListMenu().movieTypeListMenu();
		String movieType = new MovieTypeListService().movieTypeListControl();
		List<Movies> list = moviesDao.seleteType(movieType);
		int count = 0;
		if (list.size() > 0) {
			System.out.println("********************************");
			System.out.println("所有类型为:" + movieType + "的电影:");
			for (Movies movies : list) {
				count++;
				System.out.println(count + "==>" + movies);
			}
			return list;
		} else {
			System.out.println("未找到此类型的电影-_-");
			System.out.println("********************************");
		}
		return null;
	}

	/**
	 * 传进来查找的电影名的集合进行选择要查询的座位信息
	 * 
	 * @param movieName
	 * @param list
	 * @return
	 */
	public String movieName(List<Movies> list) {
		System.out.println("\n\n*************************");
		System.out.println("提示:");
		System.out.println("1--" + list.size() + "==>查看座位信息与评论");
		System.out.println("0==>返回");
		System.out.println("*************************");
		int choice = Tools.getInt(0, list.size());
		if (choice == 0) {
			new MainMenu().mainShow();
		}
		return list.get(choice - 1).getMovieName();
	}
}
