package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.TouristLookThroughMoviesControl;
import com.iotek.ht.util.Tools;

/**
 * �û������Ӱ�Ľ���
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristLookThroughMoviesMenu {
	public void lookThroughMoviesShow(int id, int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>����Ӱ�����<==");
		System.out.println("\t\t2==>�� �� �� ���<==");
		System.out.println("\t\t3==>���ȫ����Ӱ<==");
		System.out.println("\t\t0==>    ��    ��   <==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 3);
		new TouristLookThroughMoviesControl().lookMoviesControl(choice, id, i);
	}
}
