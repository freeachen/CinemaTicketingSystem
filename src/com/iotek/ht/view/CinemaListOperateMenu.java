package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.CinemaListOperateControl;
import com.iotek.ht.util.Tools;

/**
 * ��ӰԺ�б��������
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateMenu {
	public void cinemaListShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>������ӰԺ<==");
		System.out.println("\t\t2==>ɾ����ӰԺ<==");
		System.out.println("\t\t3==>�޸ĵ�Ӱ��ӰԺ<==");
		System.out.println("\t\t4==>����ָ����ӰԺ<==");
		System.out.println("\t\t5==>�鿴���е�ӰԺ<==");
		System.out.println("\t\t0==>������һ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 5);
		new CinemaListOperateControl().cinemaListControl(choice);
	}
}
