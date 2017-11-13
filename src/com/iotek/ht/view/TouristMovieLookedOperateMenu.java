package com.iotek.ht.view;

import com.iotek.ht.service.TouristMovieLookedOperateControl;
import com.iotek.ht.util.Tools;

/**
 * 游客浏览后的菜单界面 未登陆
 * 
 * @author zhangjiaqi
 *
 */
public class TouristMovieLookedOperateMenu {
	public void touristMovieLookedOperateShow(String movieName,int id,int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>查看座次信息<==");
		System.out.println("\t\t2==>读 电 影 评论<==");
		System.out.println("\t\t0==>返 回 上 一层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 2);
		new TouristMovieLookedOperateControl()
				.touristMovieLookedOperateControl(choice, movieName,id,i);
	}
}
