package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.PersonalCenterControl;
import com.iotek.ht.util.Tools;

/**
 * 个人中心
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterMenu {
	public void centerShow(int id) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>查看个人信息<==");
		System.out.println("\t\t2==>修改密保信息<==");
		System.out.println("\t\t3==>修  改  密  码<==");
		System.out.println("\t\t4==>全  部  订  单<==");
		System.out.println("\t\t0==>返 回 上 一 层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 4);
		new PersonalCenterControl().centerControl(choice, id);
	}
}
