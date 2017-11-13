package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 找回密碼具体实现模块
 * 
 * @author zhangjiaqi
 * 
 */
public class PwdHelpService {
	private UserDao userDao = new UserDao();

	/**
	 * 找回密码
	 */
	public void findPwd() {
		System.out.println("********************************");
		System.out.print("请输入用户名：");
		Scanner in = new Scanner(System.in);
		String userName = in.next();
		// 用user对象保存用户的密保问题与答案
		Users user = userDao.getQuestion(userName);
		System.out.print("您的密保问题为：");
		System.out.println(user.getQuestion());
		System.out.print("请输入密保答案:");
		String answer = in.next();
		System.out.println("\n********************************");
		String pwd = null;
		if (answer.equals(user.getAnswer())) {
			System.out.println("验证成功！即将登录、请登陆后尽快修改密码！");
			pwd = user.getPwd();
			Tools.imitateLogin();
			// 根据用户名和密码查用户的id 登录操作
			int id = userDao.judgeLogin(user);
			System.out.println("登录成功!尽快修改密码哦~~~");
			new UserLogedOperateMenu().userLoginShow(id);

		}
	}
}
