package com.iotek.ht.view;

import java.util.Scanner;

import com.iotek.ht.service.TicketsBoughtControl;
import com.iotek.ht.util.Tools;

/**
 * ��Ʊ����
 * @author zhangjaiqi
 *
 */
public class TicketsBoughtMenu {
	public void buyTicketsShow(int usersId){
		System.out.println("********************************");
		System.out.println("\n\t\t1==>ѡ �� �� λ<==");
		System.out.println("\t\t2==>ȷ �� ֧ ��<==");
		System.out.println("\t\t0==>������һ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");	
		int choice=Tools.getInt(0,2);
		new TicketsBoughtControl().ticketsBoughtControl(choice, usersId);
	}
}
