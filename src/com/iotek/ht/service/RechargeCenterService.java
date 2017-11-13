package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;

/**
 * ��ֵ����
 * 
 * @author zhangjiaqi
 * 
 */
public class RechargeCenterService {

	public void recharge(int id) {
		// ��ֵǰ����ʾ��ǰ���
		System.out.println("�������Ϊ��"
				+ new UserDao().showFromId(id).getRemainder());
		Scanner in = new Scanner(System.in);
		System.out.println("�������ֵ�Ľ��:");
		double remainder;
		remainder = judgeMoneyInsertMatch();
		boolean isOk = new UserDao().updateRemainder(id, remainder);
		if (isOk) {
			System.out.println("��ֵ�ɹ���");
			// ���²鿴���û������
			double money = new UserDao().showFromId(id).getRemainder();
			System.out.println("���Ϊ" + money);
		} else {
			System.out.println("��ֵʧ�ܣ�");
		}
	}

	/**
	 * �������Ϊ���� ��������Ҫƥ��
	 * @return
	 */
	private double judgeMoneyInsertMatch() {
		Scanner in =new Scanner(System.in);
		System.out.println("������:");
		double remainder=0;
		try {
			remainder = in.nextDouble();
			if (remainder<0) {
				System.out.println("�������Ϊ������");
				remainder=judgeMoneyInsertMatch();
			}
		} catch (Exception e) {
			System.out.println("ֻ���������֣�");
			remainder=judgeMoneyInsertMatch();
		}
		return remainder;
	}
}
