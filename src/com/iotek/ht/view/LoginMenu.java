package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.LoginControl;
import com.iotek.ht.util.Tools;

/**
 * ��¼����
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginMenu {
	public void loginShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>��  ��   ��   ½<==");
		System.out.println("\t\t2==>�� �� Ա �� ½<==");
		System.out.println("\t\t3==>��  ��   ��   ��<==");
		System.out.println("\t\t0==>    ��       ��    <==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 3);
		new LoginControl().loginControl(choice);
	}
}
