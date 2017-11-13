package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MainControl;
import com.iotek.ht.util.Tools;

/**
 * 主界面
 * 
 * @author zhangjiaqi
 * 
 */
public class MainMenu {
	static {
		System.out.println("\t\t\t\t*****************************");
		System.out.println("\t\t\t\t\t*电影选票系统*");
		System.out.println("\t\t\t\t*****************************");

	}

	public void mainShow() {
		System.out.println("********************************");
		System.out.println();
		System.out.println("\t\t1==>注       册<==");
		System.out.println("\t\t2==>登       陆<==");
		System.out.println("\t\t3==>浏 览电 影<==");
		System.out.println("\t\t4==>浏 览影 院<==");
		System.out.println("\t\t0==>退       出<==");
		System.out.println();
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 4);
		new MainControl().mainControl(choice);
	}
}
