package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.MovieListOperateControl;
import com.iotek.ht.util.Tools;

/**
 * ��Ӱ�б��������
 * 
 * @author zhangjiaqi
 * 
 */

public class MovieListOperateMenu {
	public void movieListShow() {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�� �� ��  Ӱ<==");
		System.out.println("\t\t2==>�� �� ��  Ӱ<==");
		System.out.println("\t\t3==>��  �� ��  Ӱ<==");
		System.out.println("\t\t4==>����ָ����Ӱ<==");
		System.out.println("\t\t5==>�������е�Ӱ<==");
		System.out.println("\t\t0==>�� ���� һ ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 5);
		System.out.println("********************************");
		new MovieListOperateControl().movieListControl(choice);
	}
}
