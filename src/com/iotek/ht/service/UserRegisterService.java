package com.iotek.ht.service;

import java.util.Scanner;

import javax.swing.JPasswordField;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;

/**
 * �û�ע�Ṧ��
 * 
 * @author zhangjiaqi
 * 
 */
public class UserRegisterService {
	private UserDao userDao = new UserDao();

	/**
	 * ע��
	 * 
	 * @return
	 */
	public void regist() {
		// �������ע����Ϣ�Ķ���� �����û�
		if (userDao.insert(insertShow())) {
			System.out.println("\nע��ɹ���");
			System.out.println("********************************");
		} else {
			System.out.println("\nע��ʧ�ܣ�");
			System.out.println("********************************");
		}
	}

	/**
	 * ע�����Ϣ������� ���ر�������������Ϣ���û�����
	 * 
	 * @return Users
	 */
	private Users insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ�ñ���Ӱ��Ʊϵͳ^_^");
		System.out.println("********************************");
		System.out.println("����Ϊ������Ϣ��");
		String userName = judgeName(in);// �����ж������ķ���
		Users user = null;
		while (true) {
			System.out.print("������ע�����룺");
			String pwd = in.nextLine();
			boolean isOk = judgeInsertBlock(pwd);
			if (isOk) {
				System.out.print("\n�������ܱ�����(�һ�������)��");
				String question = in.nextLine();
				System.out.print("\n�������ܱ���");
				String answer = in.nextLine();
				Tools.getCode();
				// ����ע���û�Ȩ�޶���Ϊ0 ���Ҹ�ע������Ϊ0Ԫ
				user = new Users(userName, pwd, question, answer, 0, 0);
				break;
			} else {
				System.out.println("�����в��ܴ��ո�");
			}
			// JPasswordField jpwf = new JPasswordField(in.next());
			// jpwf.setEchoChar('*');
			// String pwd = String.valueOf(jpwf.getPassword());
		}
		return user;
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
			System.out.print("\n������ע���û�����");
			userName = in.nextLine();
			boolean isOk = judgeInsertBlock(userName);
			if (isOk) {// �������ո�
				if (userDao.judgeNameExist(userName)) {
					System.out.println("\n�û����Ѵ��ڣ�����������");
				} else {
					break;
				}
			} else {
				System.out.println("ע�������ܴ��ո�");
			}
		}
		return userName;
	}

	/**
	 * �ж������Ƿ���ո�
	 * 
	 * @param userName
	 * @return true �����ո� false ���ո�
	 */
	public boolean judgeInsertBlock(String userName) {
		byte[] bytes = userName.getBytes();
		boolean isOk = true;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == ' ') {
				isOk = false;
				break;
			}
		}
		return isOk;
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
