package com.iotek.ht.view;

import com.iotek.ht.service.TouristMovieLookedOperateControl;
import com.iotek.ht.util.Tools;

/**
 * �ο������Ĳ˵����� δ��½
 * 
 * @author zhangjiaqi
 *
 */
public class TouristMovieLookedOperateMenu {
	public void touristMovieLookedOperateShow(String movieName,int id,int i) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�鿴������Ϣ<==");
		System.out.println("\t\t2==>�� �� Ӱ ����<==");
		System.out.println("\t\t0==>�� �� �� һ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 2);
		new TouristMovieLookedOperateControl()
				.touristMovieLookedOperateControl(choice, movieName,id,i);
	}
}
