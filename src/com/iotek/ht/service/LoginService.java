package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * ��¼���߼��� ʵ�ֵ�¼�ľ��巽��
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginService {
	private UserDao userDao = new UserDao();
	public static int userId = 0;// ����һ��ȫ�ֱ��� �û���½��������Լ���id

	/**
	 * �û���¼
	 */
	public void userLogin() {
		Users user = judgeLogin();
		if (user != null) {
			if (userDao.judgeUserOrAdmin(user)) {
				System.out.println("********************************");
				System.out.println("��Ϊ����Ա�˻�����ѡ�����Ա��½");
			} else {
				System.out.print("\t   ��¼��");
				Tools.imitateLogin();
				System.out.println("\t  ��¼�ɹ�");
				System.out.println("********************************");
				// ��½�ɹ���ȫ�ֱ���userID ���óɵ�½�û���id
				userId = user.getId();
				// ��¼�ɹ����ݵ�¼�ߵ�id����һ�� ���в��� ��¼�ĸ��û���½��
				new UserLogedOperateMenu().userLoginShow(user.getId());
			}
		} else {
			System.out.println("��¼ʧ�ܣ�");
			System.out.println("********************************");
		}

	}

	/**
	 * �жϵ�¼��Ϣ�Ƿ����
	 * 
	 * @return
	 */
	public Users judgeLogin() {
		// ����������û��������� ���浽Users������
		Users user = loginShow();
		int id = userDao.judgeLogin(user);
		// �ж����ݿ����Ƿ���ڸ��û�
		if (id == 0) {// ��������ڸ��û��̶��ж�
			// ����û�����Ӧ�˻����ڣ���ʾΪ�������
			if (userDao.judgeNameExist(user.getUserName())) {
				System.out.println("���������˶Ժ��ٵ�¼");
				System.out.println("********************************");
			} else {
				// ����û�����Ӧ�˻�������
				System.out.println("���û��������ڣ���ע���˶Ժ��ٵ�¼");
				System.out.println("********************************");
			}
			return null;// ��������� ����null

		}
		user.setId(id);// �����ص�id������user��
		return user;// ������� ����user
	}

	/**
	 * ����Ա��¼
	 */
	public void adminLogin() {
		Users user = judgeLogin();
		if (user != null) {
			if (userDao.judgeUserOrAdmin(user)) {
				System.out.print("\t   ��¼��");
				Tools.imitateLogin();
				System.out.println("\t  ��¼�ɹ�");
				System.out.println("********************************");
				new AdminLoginOperateMenu().adminLoginShow();
			} else {
				System.out.println("********************************");
				System.out.println("��Ϊ�û��˻�����ѡ���û���½");
			}
		} else {
			System.out.println("��¼ʧ�ܣ�");
			System.out.println("********************************");
		}
	}

	/**
	 * ��¼��ʾ
	 * 
	 * @return
	 */
	private Users loginShow() {
		System.out.println("********************************");
		System.out.print("�������¼����");
		Scanner in = new Scanner(System.in);
		String userName = in.nextLine();
		System.out.print("�������¼���룺");
		String pwd = in.nextLine();
		Tools.getCode();// ������֤�� �����ִ�Сд
		System.out.println("\n********************************");
		return new Users(userName, pwd, null, null, 0, 0);
	}
}
