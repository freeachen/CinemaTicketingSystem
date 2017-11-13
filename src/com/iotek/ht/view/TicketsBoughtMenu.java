package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.TicketsBoughtControl;
import com.iotek.ht.util.Tools;

/**
 * 购票界面
 * @author zhangjaiqi
 *
 */
public class TicketsBoughtMenu {
	public void buyTicketsShow(int usersId){
		System.out.println("********************************");
		System.out.println("\n\t\t1==>选 择 座 位<==");
		System.out.println("\t\t2==>确 认 支 付<==");
		System.out.println("\t\t0==>返回上一层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");	
		int choice=Tools.getInt(0,2);
		new TicketsBoughtControl().ticketsBoughtControl(choice, usersId);
	}
}
