package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MovieSessionOperateControl;
import com.iotek.ht.util.Tools;

/**
 * 场次操作界面
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionMenu {
	public void movieSessionShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>新     增     场    次<==");
		System.out.println("\t\t2==>删     除     场    次<==");
		System.out.println("\t\t3==>修     改     场    次<==");
		System.out.println("\t\t4==>查找指定电影的场次<==");
		System.out.println("\t\t5==>查找指定电影院场次<==");
		System.out.println("\t\t6==>查  看  所  有  场 次<==");
		System.out.println("\t\t0==>返    回    上  一  层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 6);
		new MovieSessionOperateControl().movieSessionControl(choice);
	}
}
