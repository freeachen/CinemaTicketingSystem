package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.CinemaListOperateControl;
import com.iotek.ht.util.Tools;

/**
 * 电影院列表操作界面
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateMenu {
	public void cinemaListShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>新增电影院<==");
		System.out.println("\t\t2==>删除电影院<==");
		System.out.println("\t\t3==>修改电影电影院<==");
		System.out.println("\t\t4==>查找指定电影院<==");
		System.out.println("\t\t5==>查看所有电影院<==");
		System.out.println("\t\t0==>返回上一层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 5);
		new CinemaListOperateControl().cinemaListControl(choice);
	}
}
