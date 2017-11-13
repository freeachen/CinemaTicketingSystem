package com.iotek.ht.view;

import com.iotek.ht.service.TouristLookThroughMoviesService;
import com.iotek.ht.service.LookThroughSeatsService;
import com.iotek.ht.util.Tools;

/**
 * 游客浏览电影与电影院时查看座位表
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughSeatMenu {
	public void lookThoughShow(String name, int flag) {
		System.out.println("********************************");
		if (flag == 0) {

			System.out.println("\n\t\t1==>查看该电影的场次座位表<==");
		} else {
			System.out.println("\n\t\t1==>查看该电影院的场次座位表<==");
		}
		System.out.println("\t\t0==>返回上层菜单<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 1);
		new LookThroughSeatsService().lookThrough(name, flag);
	}
}
