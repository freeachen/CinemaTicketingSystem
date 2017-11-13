package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;

/**
 * �������ĵľ��幦��ʵ��ģ��
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterService {
	private UserDao userDao = new UserDao();

	/**
	 * ��ʾ�û���Ϣ
	 * 
	 * @param id
	 */
	public void showInformation(int id) {
		System.out.println("********************************");
		System.out.println(userDao.showFromId(id));
		System.out.println("********************************");
	}

	/**
	 * �޸��ܱ���Ϣ
	 * 
	 * @param id
	 */
	public void updateQuestion(int id) {
		// ����һ��Users������ո���id�鵽���û���Ϣ
		Scanner in = new Scanner(System.in);
		Users user = userDao.showFromId(id);
		System.out.println("********************************");
		System.out.println("����ԭʼ�ܱ�������:");
		System.out.println(user.getQuestion());
		System.out.println("����ԭʼ�ܱ�����:");
		System.out.println(user.getAnswer());
		System.out.println("1==>����");
		System.out.println("2==>����");
		int choice = Tools.getInt(1, 2);
		if (choice == 1) {
			System.out.println("�������޸��ܱ�������");
		} else {
			System.out.println("�������µ��ܱ�����:");
			String newQuestion = in.next();
			System.out.println("�������µ��ܱ���:");
			String newAnswer = in.next();
			System.out.println("********************************");
			Users users = new Users();
			users.setId(id);
			users.setQuestion(newQuestion);
			users.setAnswer(newAnswer);
			judgeUpdateQuestion(id, users);
		}

	}

	/**
	 * �޸��û�����
	 * 
	 * @param id
	 */
	public void updatePwd(int id) {
		// ���ڱ����û�������Ϣ
		Users user = new Users();
		System.out.println("********************************");
		while (true) {
			System.out.println("������������:");
			Scanner in = new Scanner(System.in);
			String newPwd = in.nextLine();
			boolean isOk = new UserRegisterService().judgeInsertBlock(newPwd);
			if (isOk) {// ���������벻���ո�ʱ ����ִ��
				user.setId(id);
				user.setPwd(newPwd);
				judgeUpdatePwd(id, user);
				break;
			} else {
				System.out.println("���벻�ܴ��ո�");
			}
		}

	}

	/**
	 * �������ݿ��޸��ܱ���� �ж��޸��Ƿ�ɹ�
	 * 
	 * @param id
	 * @param users
	 */
	private void judgeUpdateQuestion(int id, Users users) {
		if (userDao.updateQusetion(users)) {
			System.out.println("�޸ĳɹ���");
			System.out.println("********************************");
			System.out.println("�����µ���ϢΪ:");
			showInformation(id);
			System.out.println("********************************");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
			System.out.println("********************************");
		}
	}

	/**
	 * �������ݿ��޸�������� �ж��޸��Ƿ�ɹ�
	 * 
	 * @param id
	 * @param users
	 */
	private void judgeUpdatePwd(int id, Users users) {
		if (userDao.updatePwd(users)) {
			System.out.println("********************************");
			System.out.println("�޸ĳɹ���");
			System.out.println("�����µ���ϢΪ:");
			showInformation(id);
			System.out.println("********************************");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
			System.out.println("********************************");
		}
	}
}
