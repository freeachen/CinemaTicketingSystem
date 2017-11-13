package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.TouristLookThroughMoviesControl;
import com.iotek.ht.util.Tools;

/**
 * 用户浏览电影的界面
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesMenu {
	public void lookThroughMoviesShow(int id, int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>按电影名浏览<==");
		System.out.println("\t\t2==>按 类 型 浏览<==");
		System.out.println("\t\t3==>浏览全部电影<==");
		System.out.println("\t\t0==>    退    出   <==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 3);
		new TouristLookThroughMoviesControl().lookMoviesControl(choice, id, i);
	}
}
