package com.iotek.ht.view;

import com.iotek.ht.service.TouristLookThroughMoviesService;
import com.iotek.ht.service.LookThroughSeatsService;
import com.iotek.ht.util.Tools;

/**
 * �ο������Ӱ���ӰԺʱ�鿴��λ��
 * 
 * @author zhangjiaqi
 * 
 */
public class LookThroughSeatMenu {
	public void lookThoughShow(String name, int flag) {
		System.out.println("********************************");
		if (flag == 0) {

			System.out.println("\n\t\t1==>�鿴�õ�Ӱ�ĳ�����λ��<==");
		} else {
			System.out.println("\n\t\t1==>�鿴�õ�ӰԺ�ĳ�����λ��<==");
		}
		System.out.println("\t\t0==>�����ϲ�˵�<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 1);
		new LookThroughSeatsService().lookThrough(name, flag);
	}
}
