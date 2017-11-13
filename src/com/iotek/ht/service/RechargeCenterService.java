package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;

/**
 * 充值中心
 * 
 * @author zhangjiaqi
 * 
 */
public class RechargeCenterService {

	public void recharge(int id) {
		// 充值前先显示当前余额
		System.out.println("您的余额为："
				+ new UserDao().showFromId(id).getRemainder());
		Scanner in = new Scanner(System.in);
		System.out.println("请输入充值的金额:");
		double remainder;
		remainder = judgeMoneyInsertMatch();
		boolean isOk = new UserDao().updateRemainder(id, remainder);
		if (isOk) {
			System.out.println("充值成功！");
			// 重新查看该用户的余额
			double money = new UserDao().showFromId(id).getRemainder();
			System.out.println("余额为" + money);
		} else {
			System.out.println("充值失败！");
		}
	}

	/**
	 * 输入金额不嫩为负数 而且输入要匹配
	 * @return
	 */
	private double judgeMoneyInsertMatch() {
		Scanner in =new Scanner(System.in);
		System.out.println("请输入:");
		double remainder=0;
		try {
			remainder = in.nextDouble();
			if (remainder<0) {
				System.out.println("输入金额不能为负数！");
				remainder=judgeMoneyInsertMatch();
			}
		} catch (Exception e) {
			System.out.println("只能输入数字！");
			remainder=judgeMoneyInsertMatch();
		}
		return remainder;
	}
}
