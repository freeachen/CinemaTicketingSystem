package com.iotek.ht.view;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.service.TouristCinemaLookedOperateControl;
import com.iotek.ht.util.Tools;

/**
 * �ο��������ӰԺ��Ľ���
 * 
 * @author zhangjiaqi
 * 
 */
public class TouristCinemaLookedOperateMenu {
	public void touristCinemaLookedOperateShow(Cinema cinema, int id, int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�鿴������Ϣ<==");
		System.out.println("\t\t2==>����ӰԺ����<==");
		System.out.println("\t\t0==>�� �� ��һ ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 2);
		new TouristCinemaLookedOperateControl()
				.touristCinemaLookedOperateControl(choice, cinema, id, i);
	}
}
