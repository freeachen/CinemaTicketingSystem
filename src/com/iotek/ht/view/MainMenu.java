package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MainControl;
import com.iotek.ht.util.Tools;

/**
 * ������
 * 
 * @author zhangjiaqi
 * 
 */
public class MainMenu {
	static {
		System.out.println("\t\t\t\t*****************************");
		System.out.println("\t\t\t\t\t*��ӰѡƱϵͳ*");
		System.out.println("\t\t\t\t*****************************");

	}

	public void mainShow() {
		System.out.println("********************************");
		System.out.println();
		System.out.println("\t\t1==>ע       ��<==");
		System.out.println("\t\t2==>��       ½<==");
		System.out.println("\t\t3==>� ���� Ӱ<==");
		System.out.println("\t\t4==>� ��Ӱ Ժ<==");
		System.out.println("\t\t0==>��       ��<==");
		System.out.println();
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 4);
		new MainControl().mainControl(choice);
	}
}
