package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MovieSessionOperateControl;
import com.iotek.ht.util.Tools;

/**
 * ���β�������
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionMenu {
	public void movieSessionShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>��     ��     ��    ��<==");
		System.out.println("\t\t2==>ɾ     ��     ��    ��<==");
		System.out.println("\t\t3==>��     ��     ��    ��<==");
		System.out.println("\t\t4==>����ָ����Ӱ�ĳ���<==");
		System.out.println("\t\t5==>����ָ����ӰԺ����<==");
		System.out.println("\t\t6==>��  ��  ��  ��  �� ��<==");
		System.out.println("\t\t0==>��    ��    ��  һ  ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 6);
		new MovieSessionOperateControl().movieSessionControl(choice);
	}
}
