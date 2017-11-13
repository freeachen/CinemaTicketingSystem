package com.iotek.ht.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.OrderFormDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.OrderForm;
import com.iotek.ht.entity.Tickets;
import com.iotek.ht.util.Tools;

/**
 * ѡ����ʵ�ֹ��ܿ�
 * 
 * @author zhangjiaqi
 * 
 */
public class SelectSeatService {
	// �õ����α�����ݿ����
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private MovieSessionOperateService msos = new MovieSessionOperateService();
	// ��Ҫ����Ʊ��
	private TicketsDao ticketsDao = new TicketsDao();
	private OrderFormDao orderFormDao = new OrderFormDao();

	/**
	 * ѡ��
	 * 
	 * @param userId
	 */
	public void selectSeat(int userId) {
		int sessionId = seleteSession(userId);
		if (sessionId > 0) {
			// �ж��Ƿ��Ѿ���ѡ����λ
			if (movieSessionDao.getRemainder(sessionId) == 0) {
				System.out.println("********************************");
				System.out.println("������лл���٣�");
				System.out.println("********************************");
			} else {
				chooseSeatNumber(userId, sessionId);
			}
		} else {
			System.out.println("********************************");
			System.out.println("�Բ���û��ӰƱ��Ϣ��");
			System.out.println("********************************");
		}
	}

	// ѡ���ľ������
	private void chooseSeatNumber(int userId, int sessionId) {
		Tickets ticket = new Tickets();// ����������Ϣ
		// ͬһ���û�ֻ��ѡһ����λ
		int id = ticketsDao.selectId(userId, sessionId);
		if (id > 0) {
			System.out.println("********************************");
			System.out.println("���Ѿ����һ�ű����ε�Ʊ��\nһ��ֻ��ѡһ����λŶ��");
			System.out.println("********************************");
		} else {
			// ��ӡ��λ״̬����ѡ��
			showSeatInformation(sessionId, userId);
			int count = 5;// ����ѡ5����λ���ɹ���ʾ�˳����
			while (true) {
				count--;
				if (count == 0) {
					System.out.println("********************************");
					System.out.println("��������0�˳�Ŷ");
					Scanner in = new Scanner(System.in);
					if (in.nextInt() == 0) {
						break;
					}
				}
				int seatNum = insertSelect(userId, sessionId);
				if (seatNum == -1) {// ����λ�����Թ���
					System.out.println("********************************");
					System.out.println("�����λ�Ѿ������ˣ�������ѡ��");

				} else if (seatNum > 0) {// ���Թ��� δ��ѡ��
					ticket.setUserId(userId);
					ticket.setSessionId(sessionId);
					ticket.setSeatNum(seatNum);
					if (ticketsDao.insertTicket(ticket)) {
						System.out.println("�����λ��δ��ѡŶ����ȥ����ɣ�");
						System.out.println("ѡ���ɹ�����ȥ֧������ӡӰƱ��");
						System.out.println("********************************");
						// ���ɶ���
						generateOrderForm(userId, sessionId);
						break;
					} else {
						System.out.println("û�������λŶ��");
						System.out.println("ѡƱʧ�ܣ�");
						System.out.println("********************************");
					}
				}
				// ѡƱ�ɹ��� ��˼���� �Ѿ�����Ϣ����Ʊ���� תȥ����
			}
		}

	}

	private void generateOrderForm(int userId, int sessionId) {
		// ���ӰƱid
		int ticketsId = ticketsDao.selectId(userId, sessionId);
		// Ĭ�϶�����״̬Ϊ0 ��ʾδ֧��
		orderFormDao.insert(new OrderForm(userId, ticketsId, 0));
	}

	/**
	 * �û�ѡ�񳡴� ���س��ε�id
	 * 
	 * @param userId
	 * @return
	 */
	public int seleteSession(int userId) {
		System.out.println("********************************");
		System.out.println("���������ѡ��:");
		// ���ò�����Ӱ�������е�ѡ��ӰԺ�� ��Ӱ�� ʱ�εķ���
		String cinemaName = getCinemaName();
		if (cinemaName != null) {
			System.out.println("ѡ���ӰԺ:" + cinemaName);
			System.out.println("����������������");
			// ���������ӰԺ�� ȥ�ҷ�ӳ�ĵ�Ӱ
			String movieName = getMovieName(cinemaName);
			System.out.println("ѡ��ĵ�Ӱ:" + movieName);
			System.out.println("����������������");
			// ���������ӰԺ�� �� ��Ӱ��ȥ�ҷ�ӳʱ��
			String time = getTime(cinemaName, movieName);
			System.out.print("ѡ���ʱ��:");
			new MovieSessionOperateService().printTime(time);
			System.out.println("����������������");
			// ������������ȥ�ҷ�ӳ��Ӱ��
			int hall = getHall(cinemaName, movieName, time);
			System.out.println("ѡ��ĳ���:" + hall + "����");
			System.out.println("����������������");
			// �ٵ��ó��α�����еĸ�������������Ϣ���ҳ���id�ķ���
			int sessionId = movieSessionDao.selectId(movieName, cinemaName,
					time, hall);
			// ��ó��ε�id�� ���û�id �볡�� id���ݵ�ѡ�������� ����¼��ӰƱ�����
			return sessionId;
		}
		// ���û�ҵ����ε���Ϣ
		return -1;
	}

	/**
	 * ��õ�ӰԺ�����ֽ���ѡ��
	 * 
	 * @return
	 */
	public String getCinemaName() {
		Scanner in = new Scanner(System.in);
		TreeMap<String, String> map = movieSessionDao.getCinemaName();
		// �ҵ����Ҳ����������Ҫ�ж�
		if (map.size() != 0) {
			System.out.println("********************************\n");
			System.out.println("��ѡ���ӰԺ:");
			// ����ҵ�����ӵ����εĵ�ӰԺ
			// ��ӡѡ���Ӧ�ĵ�ӰԺ
			Set<Entry<String, String>> entrySet = map.entrySet();
			int count = 0;
			for (Entry<String, String> entry : entrySet) {
				count++;
				System.out.println(count + "===>" + entry.getValue() + "--"
						+ entry.getKey() + "<===");
			}
			System.out.println("\n********************************");
			int choice = Tools.getInt(1, map.size());
			// ��¼����ѡ���Ӧ�ĵ�ӰԺ
			int location = 0;
			String cinemaName = null;
			for (Entry<String, String> entry : entrySet) {
				location++;
				if (location == choice) {
					cinemaName = entry.getValue();
				}
			}
			return cinemaName;
		}
		// ���û�ҵ����ؿ�ֵ
		return null;

	}

	/**
	 * ���ɵ�Ӱ�б�ѡ�� ����ѡ���Ӱ
	 * 
	 * @return ��Ӱ��
	 */
	public String getMovieName(String cinemaName) {
		Scanner in = new Scanner(System.in);
		List<String> list = movieSessionDao.getMovies(cinemaName);
		if (list != null && list.size() != 0) {
			System.out.println("********************************\n");
			System.out.println("��ѡ���Ӱ:");
			// ��ӡѡ���Ӧ�ĵ�Ӱ
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "===>" + list.get(i) + "<===");
			}
			System.out.println("\n********************************");
			int choice = Tools.getInt(1, list.size());
			// ��¼����ѡ���Ӧ�ĵ�Ӱ��
			return list.get(choice - 1);
		}
		return null;
	}

	/**
	 * ����ʱ���б� ����ѡ��ʱ��
	 * 
	 * @return
	 */
	public String getTime(String cinemaName, String movieName) {
		Scanner in = new Scanner(System.in);
		List<String> list = movieSessionDao.getTime(cinemaName, movieName);
		System.out.println("********************************\n");
		System.out.println("��ѡ��ʱ���:");
		// ��ӡѡ���Ӧ��ʱ��
		for (int i = 0; i < list.size(); i++) {
			String time = list.get(i);
			System.out.print((i + 1) + "===>");
			new MovieSessionOperateService().printTime(time);
		}
		System.out.println("\n********************************");
		int choice = Tools.getInt(1, list.size());
		// ��¼����ѡ���Ӧ��ʱ��
		return list.get(choice - 1);
	}

	/**
	 * ���ɳ����б����ѡ����
	 * 
	 * @param cinemaName
	 * @param movieName
	 * @param time
	 * @return
	 */
	public int getHall(String cinemaName, String movieName, String time) {
		List<Integer> list = movieSessionDao.selectHall(movieName, cinemaName,
				time);
		System.out.println("********************************\n");
		System.out.println("��ѡ����:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "===>" + list.get(i) + "����" + "<===");
		}
		System.out.println("\n********************************");
		int choice = Tools.getInt(1, list.size());// �ӳ�����ѡ������
		return list.get(choice - 1);
	}

	/**
	 * ����ѡ����Ϣ ���ж���λ�Ƿ��ѡ ����ѡ����λ
	 * 
	 * @param userId
	 * @param sessionId
	 * @return
	 */
	private int insertSelect(int userId, int sessionId) {
		System.out.println("********************************\n");
		System.out.println("������Ҫѡ����λ�ţ�");
		int seatNum = Tools.getInt(1, getContainer(sessionId));
		// �ж��������λ���Ƿ�Ϊ��ѡ
		int flag = -2;
		Tickets tickets = ticketsDao.selectSeatFlag(sessionId, seatNum);
		if (tickets.getSeatFlag() == 1) {
			flag = -1;// ��ʾ�@����λ�Ѿ���ѡ
		} else if (tickets.getSeatFlag() == 0) {
			flag = seatNum;// ��ʾ�����λδ��ѡ��� ����ѡ��
		}
		return flag;
	}

	/**
	 * ���ݳ���id ��õ�ӰƱ������λ��
	 * 
	 * @param sessionId
	 * @return
	 */
	private int getContainer(int sessionId) {
		return movieSessionDao.getContainer(sessionId);
	}

	/**
	 * ���ݳ���id ��ó��ε�ӰƱ��ʣ����λ
	 * 
	 * @param sessionId
	 * @return
	 */
	private int getRemainder(int sessionId) {
		return movieSessionDao.getRemainder(sessionId);
	}

	/**
	 * ��ӡ��λ״̬��Ϣ�� ����ѡ��λ ��ѡ��λ
	 */
	public void showSeatInformation(int sessionId, int userId) {

		TreeMap<Integer, String> map1 = new TreeMap<Integer, String>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		List<Integer> list = ticketsDao.getSeatNum(sessionId);
		// ���ɱ�ѡ����λ�� �Ž�һ��TreeMap�� ��ʽΪ����λ��(����ѡ)
		for (int i = 0; i < list.size(); i++) {
			map1.put(list.get(i), "(��ѡ)");
		}
		// �����ɱ�ѡ����λ�� �Ž�����һ��TreeMap�� ��ʽΪ����λ��(����ѡ)
		// ��øó��ε�����
		int container = movieSessionDao.getContainer(sessionId);
		for (int i = 1; i <= container; i++) {
			boolean isIn = false;// ����һ��״̬ ���˵�������map1�е�����
			// �������ɱ�ѡ����λ�� ���˵� ����״̬
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == i) {
					isIn = true;// ���i�ǲ��ɱ�ѡ����λ�� �͹��˵� ��isIn��Ϊtrue
				}
			}
			if (!isIn) {
				map2.put(i, "(����ѡ)");
			}
		}
		// Ȼ����������ϲ� ���´浽һ��map������ ���ȥ��ʱ��ᰴ ������Ȼ����
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		// ��������map���� ȡ����ֵ�Դ浽�µ�map��
		// ��������ѡ��λ�ŵ�����
		Set<Entry<Integer, String>> set1 = map1.entrySet();
		for (Entry<Integer, String> entry : set1) {
			map.put(entry.getKey(), entry.getValue());
		}
		// ������ѡ����λ�ŵ�����
		Set<Entry<Integer, String>> set2 = map2.entrySet();
		for (Entry<Integer, String> entry : set2) {
			map.put(entry.getKey(), entry.getValue());
		}
		// ��ӡ��λ״̬��Ϣ
		int count = 6;// ��ӡ6������ ��һ��
		Set<Entry<Integer, String>> set = map.entrySet();
		System.out.println("\n");
		for (Entry<Integer, String> entry : set) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + "\t");
			count--;
			if (count == 0) {
				count = 6;
				System.out.println();
			}
		}
		System.out.println("\n");
	}
}
