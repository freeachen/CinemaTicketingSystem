package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.PersonalCenterControl;
import com.iotek.ht.util.Tools;

/**
 * ��������
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterMenu {
	public void centerShow(int id) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�鿴������Ϣ<==");
		System.out.println("\t\t2==>�޸��ܱ���Ϣ<==");
		System.out.println("\t\t3==>��  ��  ��  ��<==");
		System.out.println("\t\t4==>ȫ  ��  ��  ��<==");
		System.out.println("\t\t0==>�� �� �� һ ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 4);
		new PersonalCenterControl().centerControl(choice, id);
	}
}
