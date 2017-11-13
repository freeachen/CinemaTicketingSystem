package com.iotek.ht.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.entity.Tickets;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.TicketsBoughtMenu;

/**
 * ��Ʊ��ʵ�־��幦�ܿ�
 * 
 * @author zhangjiaqi
 * 
 */
public class TicketBoughtService {
	private TicketsDao ticketsDao = new TicketsDao();
	private OrderFormDao orderFormDao = new OrderFormDao();
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private static int count = 0;// ����һ����̬������֧����֤�˳���

	/**
	 * �鿴����
	 * 
	 * @param usersId
	 */
	public void showOrderForm(int usersId) {
		List<OrderForm> list = orderFormDao.selectAll(usersId);
		boolean flag = false;// ȥ֧�����ж�״̬ false ����Ҫ֧�� true ��Ҫ֧��
		System.out.println("�������ж���:");
		if (list.size() != 0) {// ����ж���
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
					flag = true;
					System.out.println("********************************");
				} else {
					System.out.println("��֧��");
					System.out.println("********************************");
				}
			}
			if (flag) {
				pay(usersId);
			} else {
				System.out.println("��û����Ҫ֧���Ķ�����");
			}
		} else {// û����
			System.out.println("�Բ�����û�ж���");
		}
	}

	/**
	 * �õ�δ��ɵĶ����е�ӰƱid ѡ��ӰƱid ���и���
	 */
	public void pay(int userId) {
		List<OrderForm> list = orderFormDao.selectUndone(userId);
		// ticketsIdList �д�ŵ���δ��ɶ����� ��ӰƱid
		List<Integer> ticketsIdList = new ArrayList<Integer>();
		// ���Ӷ������õ�δ��ɶ�����ӰƱid
		for (int i = 0; i < list.size(); i++) {
			ticketsIdList.add(list.get(i).getTicketsId());
		}
		// ������ӦӰƱ��id��ù���ĳ�����Ϣ
		List<Tickets> ticketsList = new ArrayList<Tickets>();
		for (int j = 0; j < ticketsIdList.size(); j++) {
			ticketsList.add(ticketsDao.getTickets(ticketsIdList.get(j)));
		}
		System.out.println();
		System.out.println("==================");
		System.out.println("ѡ����Ҫ֧���ĳ���:");
		boolean flag = false;
		for (int i = 0; i < ticketsList.size(); i++) {
			Tickets tickets = ticketsList.get(i);
			System.out.print((i + 1) + "==>" + tickets.getCinemaName() + "\t");
			System.out.print(tickets.getHall() + "����\t");
			System.out.print(tickets.getMovieName() + "\t");
			System.out.print(tickets.getSeatNum() + "\t");
			System.out.print(tickets.getPrice() + "\t");
			if (tickets.getPrintFlag() == 0) {
				System.out.println("δ��ӡ");
				flag = true;// ��ʾ��Ҫ����֧������

			} else {
				// �����ӰƱ�Ѿ�����ӡ �Ͳ���Ҫ����
				System.out.println("�Ѵ�ӡ");
				System.out.println("�����ظ�����Ѿ������");
			}
		}
		if (flag) {
			System.out.println("0==>ȡ��");
			// �����ӰƱû�б���ӡ �ͼ�������
			int choice = Tools.getInt(0, ticketsList.size());
			if (choice != 0) {
				// ��¼ѡ����Ŷ�Ӧ�ĵ�ӰƱid ��������idȥ֧��
				int ticketsId = ticketsIdList.get(choice - 1);
				double price = ticketsDao.getPrice(ticketsId);
				buyTicket(userId, ticketsId, price);
			} else {
				System.out.println("����ȡ��֧������������");
			}
		}
	}

	/**
	 * ����
	 * 
	 * @param userId
	 * @param sessionId
	 */
	public void buyTicket(int userId, int ticketsId, double price) {
		// �ٲ����Լ������
		double remainder = new UserDao().showFromId(userId).getRemainder();
		if (price > 0) {
			// ������� ȥ��ֵ
			if (remainder < price) {
				// ����ȥ��ֵ
				System.out.println("���㣬���ֵ(����ת����ֵ����):");
				System.out.println("��ת�С���������");
				Tools.imitateLogin();
				new RechargeCenterService().recharge(userId);
				buyTicket(userId, ticketsId, price);
			} else {
				// ��ֵ�ɹ� ��ӡ��ӰƱ
				Scanner in = new Scanner(System.in);
				System.out.println("֧����֤:");
				System.out.println("�������û���:");
				String userName = in.nextLine();
				System.out.println("����������:");
				String pwd = in.nextLine();
				Users user = new Users();
				user.setUserName(userName);
				user.setPwd(pwd);
				if (new UserDao().judgeLogin(user) == userId) {
					System.out.println("������֤��������");

					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("����֧����������");
					Tools.imitateLogin();
					System.out.println("֧���ɹ���");
					System.out.println("���ڴ�ӡӰƱ��������");
					// ����ӰƱid���sessionid
					int sessionId = ticketsDao.getSessionId(ticketsId);
					printTicket(userId, sessionId);
					// ��ӡӰƱ�� ��������״̬�ı�
					orderFormDao.updateFlag(userId, ticketsId);
					remainder -= price;
					// �����û����
					new UserDao().updateRemainder(userId, remainder);
					// �鿴���ε�ʣ����λ��
					int left = movieSessionDao.getRemainder(sessionId);
					// ���ĳ�����λʣ��
					movieSessionDao.updateRemainder(sessionId, left - 1);
					try {// ��ӡ���ӰƱ��д���ļ���// ��ӡ���ӰƱ��д���ļ���
						new FileinputTicketsBoughtService().fileIn(ticketsId);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("֧����֤ʧ�ܡ�������");
					count++;
					if (count == 4) {// ֧��4�ξ��˳�֧��
						System.out.println("֧����������4�Ρ������˳�����");
						new TicketsBoughtMenu().buyTicketsShow(userId);
					}
					// ���¹���
					buyTicket(userId, ticketsId, price);
				}

			}
		}
	}

	/**
	 * ��ӡ��ӰƱ
	 * 
	 * @param userId
	 * @param sessionId
	 */
	private void printTicket(int userId, int sessionId) {
		boolean printOk = new TicketsDao().updatePrintFlag(userId, sessionId);
		if (printOk) {
			Tools.imitateLogin();
			System.out.println("��Ʊ�ɹ���");
			System.out.println("лл���Ĺ�Ʊ��ף����Ӱ���^_^");
		} else {
			System.out.println("��Ʊʧ�ܣ�");
		}
	}

}
