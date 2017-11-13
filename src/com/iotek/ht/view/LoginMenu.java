package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.LoginControl;
import com.iotek.ht.util.Tools;

/**
 * 登录界面
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginMenu {
	public void loginShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>用  户   登   陆<==");
		System.out.println("\t\t2==>管 理 员 登 陆<==");
		System.out.println("\t\t3==>找  回   密   码<==");
		System.out.println("\t\t0==>    退       出    <==\n");
		System.out.println("********************************");
		System.out.println("请选择：");
		int choice = Tools.getInt(0, 3);
		new LoginControl().loginControl(choice);
	}
}
