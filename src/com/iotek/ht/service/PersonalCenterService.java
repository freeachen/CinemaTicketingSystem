package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;

/**
 * 个人中心的具体功能实现模块
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterService {
	private UserDao userDao = new UserDao();

	/**
	 * 显示用户信息
	 * 
	 * @param id
	 */
	public void showInformation(int id) {
		System.out.println("********************************");
		System.out.println(userDao.showFromId(id));
		System.out.println("********************************");
	}

	/**
	 * 修改密保信息
	 * 
	 * @param id
	 */
	public void updateQuestion(int id) {
		// 创建一个Users对象接收根据id查到的用户信息
		Scanner in = new Scanner(System.in);
		Users user = userDao.showFromId(id);
		System.out.println("********************************");
		System.out.println("您的原始密保问题是:");
		System.out.println(user.getQuestion());
		System.out.println("您的原始密保答案是:");
		System.out.println(user.getAnswer());
		System.out.println("1==>放弃");
		System.out.println("2==>继续");
		int choice = Tools.getInt(1, 2);
		if (choice == 1) {
			System.out.println("您放弃修改密保···");
		} else {
			System.out.println("请输入新的密保问题:");
			String newQuestion = in.next();
			System.out.println("请输入新的密保答案:");
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
	 * 修改用户密码
	 * 
	 * @param id
	 */
	public void updatePwd(int id) {
		// 用于保存用户输入信息
		Users user = new Users();
		System.out.println("********************************");
		while (true) {
			System.out.println("请输入新密码:");
			Scanner in = new Scanner(System.in);
			String newPwd = in.nextLine();
			boolean isOk = new UserRegisterService().judgeInsertBlock(newPwd);
			if (isOk) {// 当输入密码不带空格时 继续执行
				user.setId(id);
				user.setPwd(newPwd);
				judgeUpdatePwd(id, user);
				break;
			} else {
				System.out.println("密码不能带空格！");
			}
		}

	}

	/**
	 * 调用数据库修改密保语句 判断修改是否成功
	 * 
	 * @param id
	 * @param users
	 */
	private void judgeUpdateQuestion(int id, Users users) {
		if (userDao.updateQusetion(users)) {
			System.out.println("修改成功！");
			System.out.println("********************************");
			System.out.println("您的新的信息为:");
			showInformation(id);
			System.out.println("********************************");
		} else {
			System.out.println("修改失败！");
			System.out.println("********************************");
		}
	}

	/**
	 * 调用数据库修改密码语句 判断修改是否成功
	 * 
	 * @param id
	 * @param users
	 */
	private void judgeUpdatePwd(int id, Users users) {
		if (userDao.updatePwd(users)) {
			System.out.println("********************************");
			System.out.println("修改成功！");
			System.out.println("您的新的信息为:");
			showInformation(id);
			System.out.println("********************************");
		} else {
			System.out.println("修改失败！");
			System.out.println("********************************");
		}
	}
}
