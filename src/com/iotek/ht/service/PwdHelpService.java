package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * �һ��ܴa����ʵ��ģ��
 * 
 * @author zhangjiaqi
 * 
 */
public class PwdHelpService {
	private UserDao userDao = new UserDao();

	/**
	 * �һ�����
	 */
	public void findPwd() {
		System.out.println("********************************");
		System.out.print("�������û�����");
		Scanner in = new Scanner(System.in);
		String userName = in.next();
		// ��user���󱣴��û����ܱ��������
		Users user = userDao.getQuestion(userName);
		System.out.print("�����ܱ�����Ϊ��");
		System.out.println(user.getQuestion());
		System.out.print("�������ܱ���:");
		String answer = in.next();
		System.out.println("\n********************************");
		String pwd = null;
		if (answer.equals(user.getAnswer())) {
			System.out.println("��֤�ɹ���������¼�����½�󾡿��޸����룡");
			pwd = user.getPwd();
			Tools.imitateLogin();
			// �����û�����������û���id ��¼����
			int id = userDao.judgeLogin(user);
			System.out.println("��¼�ɹ�!�����޸�����Ŷ~~~");
			new UserLogedOperateMenu().userLoginShow(id);

		}
	}
}
