package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.AdminLoginControl;
import com.iotek.ht.service.MainControl;
import com.iotek.ht.service.UserLoginControl;
import com.iotek.ht.util.Tools;

/**
 * �û���¼��Ľ���
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLogedOperateMenu {
	public void userLoginShow(int id) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�� �� �� ��<==");
		System.out.println("\t\t2==>�� ֵ �� ��<==");
		System.out.println("\t\t3==>��   ��   Ӱ<==");
		System.out.println("\t\t4==>��   Ӱ   Ժ<==");
		System.out.println("\t\t5==>��   Ӱ   ��<==");
		System.out.println("\t\t6==>��   Ժ   ��<==");
		System.out.println("\t\t7==>��         Ʊ<==");
		System.out.println("\t\t0==>�� ��  ��¼<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 7);
		new UserLoginControl().userLoginControl(choice, id, 1);
	}
}
