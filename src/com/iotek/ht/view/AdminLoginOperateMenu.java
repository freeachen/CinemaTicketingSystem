package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.AdminLoginControl;
import com.iotek.ht.util.Tools;

/**
 * ����Ա��¼��Ĳ�������
 * 
 * @author zhangjiaqi
 * 
 */
public class AdminLoginOperateMenu {
	public void adminLoginShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�༭��Ӱ�б�<==");
		System.out.println("\t\t2==>�༭ӰԺ�б�<==");
		System.out.println("\t\t3==>�༭�����б�<==");
		System.out.println("\t\t0==>�˳�����Ա��½<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");	
		int choice=Tools.getInt(0,3);
		new AdminLoginControl().adminLoginControl(choice);
	}
}
