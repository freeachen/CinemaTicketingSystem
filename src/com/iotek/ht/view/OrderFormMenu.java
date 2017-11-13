package com.iotek.ht.view;

import com.iotek.ht.service.OrderFormControl;
import com.iotek.ht.util.Tools;

/**
 * 订单菜单
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormMenu {
	public void orderFormShow(int userId) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>查看所有购票记录<==");
		System.out.println("\t\t2==>查 看 未 完成订单<==");
		System.out.println("\t\t3==>取 消 购 票 订  单<==");
		System.out.println("\t\t0==>返  回  上   一  层<==\n");
		System.out.println("********************************");
		System.out.println("********************************");
		int choice = Tools.getInt(0, 3);
		new OrderFormControl().orderFormControl(choice, userId);
	}
}
