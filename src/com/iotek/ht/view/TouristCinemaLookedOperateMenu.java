package com.iotek.ht.view;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.service.TouristCinemaLookedOperateControl;
import com.iotek.ht.util.Tools;

/**
 * 游客浏览过电影院后的界面
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristCinemaLookedOperateMenu {
	public void touristCinemaLookedOperateShow(Cinema cinema, int id, int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>查看座次信息<==");
		System.out.println("\t\t2==>读电影院评论<==");
		System.out.println("\t\t0==>返 回 上一 层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 2);
		new TouristCinemaLookedOperateControl()
				.touristCinemaLookedOperateControl(choice, cinema, id, i);
	}
}
