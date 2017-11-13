package com.iotek.ht.service;

import java.util.Scanner;

import javax.swing.JPasswordField;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;

/**
 * 用户注册功能
 * 
 * @author zhangjiaqi
 * 
 */
public class UserRegisterService {
	private UserDao userDao = new UserDao();

	/**
	 * 注册
	 * 
	 * @return
	 */
	public void regist() {
		// 获得所有注册信息的对象后 新增用户
		if (userDao.insert(insertShow())) {
			System.out.println("\n注册成功！");
			System.out.println("********************************");
		} else {
			System.out.println("\n注册失败！");
			System.out.println("********************************");
		}
	}

	/**
	 * 注册的信息输入操作 返回保存所有输入信息的用户对象
	 * 
	 * @return Users
	 */
	private Users insertShow() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用本电影购票系统^_^");
		System.out.println("********************************");
		System.out.println("下面为必填信息：");
		String userName = judgeName(in);// 调用判断重名的方法
		Users user = null;
		while (true) {
			System.out.print("请输入注册密码：");
			String pwd = in.nextLine();
			boolean isOk = judgeInsertBlock(pwd);
			if (isOk) {
				System.out.print("\n请输入密保问题(找回密码用)：");
				String question = in.nextLine();
				System.out.print("\n请输入密保答案");
				String answer = in.nextLine();
				Tools.getCode();
				// 所有注册用户权限都设为0 而且刚注册的余额为0元
				user = new Users(userName, pwd, question, answer, 0, 0);
				break;
			} else {
				System.out.println("密码中不能带空格！");
			}
			// JPasswordField jpwf = new JPasswordField(in.next());
			// jpwf.setEchoChar('*');
			// String pwd = String.valueOf(jpwf.getPassword());
		}
		return user;
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
			System.out.print("\n请输入注册用户名：");
			userName = in.nextLine();
			boolean isOk = judgeInsertBlock(userName);
			if (isOk) {// 不包含空格
				if (userDao.judgeNameExist(userName)) {
					System.out.println("\n用户名已存在！请重新输入");
				} else {
					break;
				}
			} else {
				System.out.println("注册名不能带空格！");
			}
		}
		return userName;
	}

	/**
	 * 判断输入是否带空格
	 * 
	 * @param userName
	 * @return true 不带空格 false 带空格
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
		boolean isOk = false;// 设置输入状态
		if (o == null) {
			isOk = false;
		} else {
			isOk = true;
		}
		return isOk;
	}
}
