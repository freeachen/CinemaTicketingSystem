package com.iotek.ht.service;

import java.util.List;

import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.util.Tools;

/**
 * ���������ľ���ʵ��
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormService {
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * �鿴����
	 * 
	 * @param usersId
	 */
	public void showAllOrder(int usersId) {
		System.out.println("********************************");
		System.out.println("�������ж���:");
		List<OrderForm> list = printOrderForm(usersId);
		if (list == null || list.size() == 0) {
			System.out.println("��û�й�Ʊ��¼");
			System.out.println("********************************");
		}
	}

	/**
	 * �鿴δ��ɶ���
	 * 
	 * @param userId
	 */
	public void showUndoneOrder(int userId) {
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		if (list != null && list.size() != 0) {
			getUnOrder(list, 0);
		} else {
			System.out.println("��û��δ��ɵĶ�����");
		}
		System.out.println("********************************");

	}

	/**
	 * ȡ������
	 * 
	 * @param userId
	 */
	public void deleteUndoneOrder(int userId) {
		// ��ȡδ��ɵĶ���
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		int count = 0;// ͳ�ƶ���δ��ɵĶ���
		// �����δ��ɵĶ��� ����ѡ��ȡ��
		if (list.size() != 0) {
			count = getUnOrder(list, count);
			System.out.println("********************************");
			// ����ѡ��δ֧���Ķ������ ��������ѡ��ȡ��
			int choice = Tools.getInt(1, count);
			int orderId = list.get(choice - 1).getId();// ��¼ѡ��ѡ���Ӧ�Ķ���id
			int ticketsId = list.get(choice - 1).getTicketsId();// ��¼ѡ��ѡ���Ӧ��ӰƱid
			System.out.println("ȷ��ȡ������ô?");
			System.out.println("1==>����");
			System.out.println("2==>ȷ��");
			int select = Tools.getInt(1, 2);
			if (select == 2) {
				// Ȼ��ȡ�������� ȡ��ѡ����
				// ȡ��������
				deleteOrder(orderId);
				// ȡ��ѡ��
				deleteSeatSelected(ticketsId);
			} else {
				System.out.println("����ȡ������������");
			}
		} else {
			System.out.println("��û��δ��ɵĶ���Ŷ");
		}

	}

	/**
	 * ���δ��ɶ����ĸ��� ����ӡ��δ��ɶ���
	 * 
	 * @param list
	 * @param count
	 * @return
	 */
	private int getUnOrder(List<OrderForm> list, int count) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOrderFlag() == 0) {
				count++;
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t");
				System.out.print(list.get(i).getMovieName() + "\t");
				System.out.print(list.get(i).getPrice() + "\t");
				System.out.println("δ֧��");

			}
		}
		return count;
	}

	/**
	 * ȡ��ѡ�� �˵�ӰƱ
	 * 
	 * @param ticketsId
	 */
	public void deleteSeatSelected(int ticketsId) {
		if (new TicketsDao().deleteSeat(ticketsId)) {
			System.out.println("ѡ��ȡ���ɹ���");
		} else {
			System.out.println("ѡ��ȡ��ʧ�ܣ�");
		}
	}

	/**
	 * ȡ������
	 * 
	 * @param orderId
	 */
	public void deleteOrder(int orderId) {
		if (orderFormDao.delete(orderId)) {
			System.out.println("����ȡ���ɹ�!");
		} else {
			System.out.println("����ȡ��ʧ��!");
		}
	}

	private List<OrderForm> printOrderForm(int usersId) {
		List<OrderForm> list = orderFormDao.selectAll(usersId);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
						+ "\t" + list.get(i).getHall() + "����" + "\t");
				System.out.print(list.get(i).getAddress() + "��\t\t��");
				System.out.print(list.get(i).getMovieName() + "��\t\t"
						+ list.get(i).getSeat() + "����\t\t����:");
				System.out.print(list.get(i).getPrice() + "\t����ʱ��:");
				String time = list.get(i).getTime();
				new MovieSessionOperateService().printTime(time);
				if (list.get(i).getOrderFlag() == 0) {
					System.out.println("δ֧��");
					System.out.println("********************************");
				} else {
					System.out.println("��֧��");
					System.out.println("********************************");
				}
			}
			return list;
		}
		return null;
	}

}
