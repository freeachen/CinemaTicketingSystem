package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;

/**
 * ע����߼��� ʵ��ע��ľ��巽����
 * 
 * @author ht
 * 
 */
public class RegisterService {
	private UserDao userDao = new UserDao();

	/**
	 * ע��
	 * 
	 * @return
	 */
	public void regist() {
		if (userDao.insert(insertShow())) {
			System.out.println("ע��ɹ���");
		} else {
			System.out.println("ע��ʧ�ܣ�");
		}
	}

	/**
	 * �����û���Ϣ�Ĳ���
	 * 
	 * @return
	 */
	public Users insertShow() {
		Users user = null;
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ�ñ���Ӱ��Ʊϵͳ^_^");
		System.out.println("����Ϊ������Ϣ��");
		String userName = judgeName(in);
		System.out.println("������ע�����룺");
		String pwd = in.next();
		System.out.println("�������ܱ�����(�һ�������)��");
		String question = in.next();
		System.out.println("�������ܱ���");
		String answer = in.next();
		// ����ע���û�Ȩ�޶���Ϊ0 ���Ҹ�ע������Ϊ0Ԫ
		return new Users(userName, pwd, question, answer, 0, 0);
	}

	/**
	 * �ж�ע���û����Ƿ����
	 * 
	 * @param in
	 * @return
	 */
	private String judgeName(Scanner in) {
		String userName = null;
		while (true) {
			System.out.println("������ע���û�����");
			userName = in.next();
			if (userDao.judgeNameExist(userName)) {
				System.out.println("�û����Ѵ��ڣ�����������");
			} else {
				break;
			}
		}
		return userName;
	}

	private boolean insertJudge(Object o) {
		boolean isOk = false;// ��������״̬
		if (o == null) {
			isOk = false;
		} else {
			isOk = true;
		}
		return isOk;
	}
}
