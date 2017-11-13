package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;

/**
 * 注册的逻辑层 实现注册的具体方法块
 * 
 * @author ht
 * 
 */
public class RegisterService {
	private UserDao userDao = new UserDao();

	/**
	 * 注册
	 * 
	 * @return
	 */
	public void regist() {
		if (userDao.insert(insertShow())) {
			System.out.println("注册成功！");
		} else {
			System.out.println("注册失败！");
		}
	}

	/**
	 * 输入用户信息的操作
	 * 
	 * @return
	 */
	public Users insertShow() {
		Users user = null;
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用本电影购票系统^_^");
		System.out.println("下面为必填信息：");
		String userName = judgeName(in);
		System.out.println("请输入注册密码：");
		String pwd = in.next();
		System.out.println("请输入密保问题(找回密码用)：");
		String question = in.next();
		System.out.println("请输入密保答案");
		String answer = in.next();
		// 所有注册用户权限都设为0 而且刚注册的余额为0元
		return new Users(userName, pwd, question, answer, 0, 0);
	}

	/**
	 * 判断注册用户名是否存在
	 * 
	 * @param in
	 * @return
	 */
	private String judgeName(Scanner in) {
		String userName = null;
		while (true) {
			System.out.println("请输入注册用户名：");
			userName = in.next();
			if (userDao.judgeNameExist(userName)) {
				System.out.println("用户名已存在！请重新输入");
			} else {
				break;
			}
		}
		return userName;
	}

	private boolean insertJudge(Object o) {
		boolean isOk = false;// 设置输入状态
		if (o == null) {
			isOk = false;
		} else {
			isOk = true;
		}
		return isOk;
	}
}
