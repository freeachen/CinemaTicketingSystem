package com.iotek.ht.service;

import java.util.List;
import java.util.Scanner;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.CinemaReviewMenu;
import com.iotek.ht.view.LookThroughSeatMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.MovieTypeListMenu;
import com.iotek.ht.view.TouristCinemaLookedOperateMenu;

/**
 * ����Ա��ӰԺ�Ĳ��� ��ʵ�ַ���
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaListOperateService {
	private CinemaDao cinemaDao = new CinemaDao();

	/**
	 * ������ӰԺ
	 */
	public void insertCinema() {
		Cinema cinema = insertShow();
		if (cinema == null) {
			System.out.println("�����ӰԺ�Ѿ����ڣ�������ӣ�");
		} else if (cinemaDao.insert(cinema)) {
			System.out.println("�����ɹ���");
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	/**
	 * ������ӰԺʱ�Ŀ�ʼ��ʾ
	 * 
	 * @return
	 */
	public Cinema insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("�������������ӵ�ӰԺ�б�:");
		System.out.println("�������ӰԺ������:");
		String cinemaName = in.nextLine();
		System.out.println("�������ӰԺ�ĵ�ַ:");
		String cinemaAddress = in.nextLine();
		System.out.println("�����볡��������:");
		int halls = in.nextInt();
		// ����һ��״̬��¼�Ƿ���������ӰԺ
		boolean isTrue = cinemaDao.judgeExist(cinemaName, cinemaAddress);
		if (isTrue) {
			return null;
		}
		return new Cinema(cinemaName, cinemaAddress, halls);

	}

	/**
	 * ɾ����ӰԺ
	 */
	public void deleteCinema() {
		String cinemaName = deleteShow();
		// �ж���û�������ӰԺ��id
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// �ҵ���Ϣ��
			System.out.println("�ҵ�����Ϣ:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
			if (cinemaList.size() != 1) {// �ҵ�������Ϣ����� ��Ҫѡ��ɾ��
				System.out.println("ѡ����Ҫ���ҵĵ�Ӱ:");
				int choice = Tools.getInt(1, cinemaList.size());
				int id = cinemaList.get(choice - 1).getId();
				deleteCinema(id);
			} else {
				// ���ֻ��һ��
				deleteCinema(cinemaList.get(0).getId());
			}
		} else {
			System.out.println("δ�ҵ���Ӧ��Ϣ��");
		}
	}

	private void deleteCinema(int id) {
		System.out.println("ȷ��Ҫɾ��ô��");
		System.out.println("1==>����");
		System.out.println("0==>����");
		if (Tools.getInt(0, 1) == 0) {
			// ����еĻ�
			if (cinemaDao.delete(id)) {
				System.out.println("ɾ���ɹ���");
			} else {
				System.out.println("ɾ��ʧ�ܣ�");
			}
		} else {
			System.out.println("���ѷ���ɾ����������");
		}
	}

	/**
	 * ���Ҫɾ����ӰԺ������
	 * 
	 * @return
	 */
	private String deleteShow() {
		System.out.println("������Ҫɾ���ĵ�ӰԺ������:");
		Scanner in = new Scanner(System.in);
		String cinemaName = in.next();
		return cinemaName;
	}

	/**
	 * �޸ĵ�Ӱ��Ϣ
	 */
	public void updateCinema() {
		Scanner in = new Scanner(System.in);
		System.out.println("������Ҫ�ĵĵ�ӰԺ������:");
		String cinemaName = in.next();
		// ���ݵ�ӰԺ��ȥ�������з���������ֵĵ�ӰԺ ����һ����ӰԺ����
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// �ҵ���Ϣ��
			System.out.println("�ҵ�����Ϣ:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
			if (cinemaList.size() != 1) {// �ҵ�������Ϣ����� ��Ҫѡ��ɾ��
				System.out.println("ѡ����Ҫ���ҵĵ�Ӱ:");
				int choice = Tools.getInt(1, cinemaList.size());
				int id = cinemaList.get(choice - 1).getId();// ����idȥ�޸���Ϣ
				insertNewCinema(id);
			} else {
				// ���ֻ��һ��
				insertNewCinema(cinemaList.get(0).getId());
			}
		} else {
			System.out.println("δ�ҵ���Ӧ��Ϣ��");
		}

	}

	/**
	 * ѡ��Ҫ�޸ĵ�ӰԺ��Ĳ���
	 * 
	 * @param id
	 */
	private void insertNewCinema(int id) {
		System.out.println("ȷ��Ҫ�޸ģ�");
		System.out.println("1==>����");
		System.out.println("0==>����");
		if (Tools.getInt(0, 1) == 0) {// ����
			Scanner in = new Scanner(System.in);
			System.out.println("�������µĵ�ӰԺ����:");
			String newCinemaName = in.next();
			System.out.println("�������µĵ�ӰԺ�ϵ�ַ:");
			String newCinemaAddress = in.next();
			System.out.println("�������µĳ�������:");
			int newHalls = in.nextInt();
			// ��ʾ���ڸõ�ӰԺ �����޸�
			if (cinemaDao.update(id, new Cinema(newCinemaName,
					newCinemaAddress, newHalls))) {
				// �޸ĳɹ�
				System.out.println("�޸ĳɹ���");
			} else {
				System.out.println("�޸�ʧ�ܣ�");
			}
		} else {
			System.out.println("���ѷ����޸ġ�����");
		}

	}

	/**
	 * �鿴��Ӱ����Ӧ��Ӱ��Ϣ
	 */
	public void showFromName() {
		Scanner in = new Scanner(System.in);
		System.out.println("������Ҫ�鿴�ĵ�ӰԺ��:");
		String cinemaName = in.next();
		// ���ݵ�ӰԺ��ȥ�������з���������ֵĵ�ӰԺ ����һ����ӰԺ����
		List<Cinema> cinemaList = cinemaDao.selectName(cinemaName);
		if (cinemaList.size()!=0) {// �ҵ���Ϣ��
			System.out.println("�ҵ�����Ϣ:");
			for (int i = 0; i < cinemaList.size(); i++) {
				System.out.println((i + 1) + "==>" + cinemaList.get(i));
			}
		}else {
			System.out.println("δ�ҵ������Ϣ");
		}
	}

	/**
	 * �û����οͲ鿴���е�ӰԺ
	 */
	public void showAll(int id,int flag) {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "��");
			}
			// ����selectSeatService���ӡ��λ��ķ���
			new TouristCinemaLookedOperateMenu()
					.touristCinemaLookedOperateShow(cinemaName(list),id,flag);
		} else {
			System.out.println("û�����ӰԺ�ĳ�����λ��Ŷ");
		}
	}

	/**
	 * ����Ա�鿴���е�ӰԺ
	 */
	public void adminShowAll() {
		List<Cinema> list = cinemaDao.selectAll();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + "==>"
						+ list.get(i).getCinemaName() + "--"
						+ list.get(i).getCinemaAddress() + "��");
			}
			System.out.println("*********************\n");
		} else {
			System.out.println("û�����ӰԺ�ĳ�����λ��Ŷ");
		}
	}

	/**
	 * ���������ҵĵ�ӰԺ���ļ��Ͻ���ѡ��Ҫ��ѯ����λ��Ϣ
	 * 
	 * @param list
	 * @return
	 */
	public Cinema cinemaName(List<Cinema> list) {
		System.out.println("\n\n*************************");
		System.out.println("��ʾ:\nѡ����Ӧ��ӰԺ�鿴��λ����Ӱ��\nѡ��0==>����");
		System.out.println("*************************");
		int choice = Tools.getInt(0, list.size());
		if (choice == 0) {
			new MainMenu().mainShow();
		}
		return list.get(choice - 1);
	}
}
