package com.iotek.ht.view;

import com.iotek.ht.service.OrderFormControl;
import com.iotek.ht.util.Tools;

/**
 * �����˵�
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormMenu {
	public void orderFormShow(int userId) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>�鿴���й�Ʊ��¼<==");
		System.out.println("\t\t2==>�� �� δ ��ɶ���<==");
		System.out.println("\t\t3==>ȡ �� �� Ʊ ��  ��<==");
		System.out.println("\t\t0==>��  ��  ��   һ  ��<==\n");
		System.out.println("********************************");
		System.out.println("********************************");
		int choice = Tools.getInt(0, 3);
		new OrderFormControl().orderFormControl(choice, userId);
	}
}
