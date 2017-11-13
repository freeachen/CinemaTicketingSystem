package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TicketsBoughtMenu;

/**
 * �û��˳�ʱ�Ĳ��� ��Ҫ����δ��ɵĶ���
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLogoutService {
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * �û��˳�ʱ ����δ��ɵĶ��� ���������ֱ���˳� ��ȡ������
	 * 
	 * @param id
	 */
	public void logout(int id) {
		// �鿴�Ƿ���δ��ɵĶ���
		List<OrderForm> list = orderFormDao.selectUndone(id);
		if (list.size() != 0) {
			// �����δ��ɵĶ��� ��������
			System.out.println("������δ��ɵĶ���:");
			for (int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t");
				System.out.print(list.get(i).getMovieName() + "\t");
				System.out.print(list.get(i).getPrice() + "\t");
				System.out.println("δ֧��");
			}
			System.out.println("���Ƿ���ȥ����(��ѡ��):");
			System.out.println("1==>����<==");
			System.out.println("2==>ֱ��ȥȡ��<==");
			int choice = Tools.getInt(1, 2);
			if (choice == 1) {
				// ȥ����δ��ɵĶ��� ת��������һҳ
				new TicketsBoughtMenu().buyTicketsShow(id);
			} else {
				new OrderFormService().deleteUndoneOrder(id);
				System.out.println("�˳��С���������");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
