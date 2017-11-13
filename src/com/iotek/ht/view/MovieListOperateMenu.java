package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MovieListOperateControl;
import com.iotek.ht.util.Tools;

/**
 * 电影列表操作界面
 * 
 * @author zhangjiaqi
 * 
 */

public class MovieListOperateMenu {
	public void movieListShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>新 增 电  影<==");
		System.out.println("\t\t2==>下 架 电  影<==");
		System.out.println("\t\t3==>修  改 电  影<==");
		System.out.println("\t\t4==>查找指定电影<==");
		System.out.println("\t\t5==>查找所有电影<==");
		System.out.println("\t\t0==>返 回上 一 层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 5);
		System.out.println("********************************");
		new MovieListOperateControl().movieListControl(choice);
	}
}
