package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.MovieListOperateMenu;

/**
 * 管理员对电影列表的操作 的中转层 转到相应实现方法
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieListOperateControl {
	MovieListOperateService mlos = new MovieListOperateService();

	public void movieListControl(int choice) {
		switch (choice) {
		case 1:
			// 新增电影
			mlos.insertMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 2:
			// 删除电影
			mlos.deleteMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 3:
			// 修改电影
			mlos.updateMovie();
			new MovieListOperateMenu().movieListShow();
			break;
		case 4:
			// 查看指定电影
			mlos.showFromName();
			new MovieListOperateMenu().movieListShow();
			break;
		case 5:
			// 查看所有电影
			mlos.showAll();
			new MovieListOperateMenu().movieListShow();
			break;
		case 0:
			System.out.println("返回上一层");
			new AdminLoginOperateMenu().adminLoginShow();
			break;

		default:
			break;
		}

	}
}
