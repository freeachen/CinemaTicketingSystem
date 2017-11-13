package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.AdminLoginControl;
import com.iotek.ht.util.Tools;

/**
 * 管理员登录后的操作界面
 * 
 * @author zhangjiaqi
 * 
 */
public class AdminLoginOperateMenu {
	public void adminLoginShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>编辑电影列表<==");
		System.out.println("\t\t2==>编辑影院列别<==");
		System.out.println("\t\t3==>编辑场次列别<==");
		System.out.println("\t\t0==>退出管理员登陆<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");	
		int choice=Tools.getInt(0,3);
		new AdminLoginControl().adminLoginControl(choice);
	}
}
