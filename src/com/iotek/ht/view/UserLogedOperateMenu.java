package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.AdminLoginControl;
import com.iotek.ht.service.MainControl;
import com.iotek.ht.service.UserLoginControl;
import com.iotek.ht.util.Tools;

/**
 * 用户登录后的界面
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLogedOperateMenu {
	public void userLoginShow(int id) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>个 人 中 心<==");
		System.out.println("\t\t2==>充 值 中 心<==");
		System.out.println("\t\t3==>搜   电   影<==");
		System.out.println("\t\t4==>查   影   院<==");
		System.out.println("\t\t5==>读   影   评<==");
		System.out.println("\t\t6==>看   院   评<==");
		System.out.println("\t\t7==>购         票<==");
		System.out.println("\t\t0==>退 出  登录<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 7);
		new UserLoginControl().userLoginControl(choice, id, 1);
	}
}
