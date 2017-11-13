package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.db.dao.impl.UserDao;
import com.iotek.ht.entity.Users;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 登录的逻辑层 实现登录的具体方法
 * 
 * @author zhangjiaqi
 * 
 */
public class LoginService {
	private UserDao userDao = new UserDao();
	public static int userId = 0;// 设置一个全局变量 用户登陆后可以用自己的id

	/**
	 * 用户登录
	 */
	public void userLogin() {
		Users user = judgeLogin();
		if (user != null) {
			if (userDao.judgeUserOrAdmin(user)) {
				System.out.println("********************************");
				System.out.println("您为管理员账户！请选择管理员登陆");
			} else {
				System.out.print("\t   登录中");
				Tools.imitateLogin();
				System.out.println("\t  登录成功");
				System.out.println("********************************");
				// 登陆成功将全局变量userID 设置成登陆用户的id
				userId = user.getId();
				// 登录成功传递登录者的id到下一层 进行操作 记录哪个用户登陆的
				new UserLogedOperateMenu().userLoginShow(user.getId());
			}
		} else {
			System.out.println("登录失败！");
			System.out.println("********************************");
		}

	}

	/**
	 * 判断登录信息是否存在
	 * 
	 * @return
	 */
	public Users judgeLogin() {
		// 接收输入的用户名与密码 保存到Users对象中
		Users user = loginShow();
		int id = userDao.judgeLogin(user);
		// 判断数据库中是否存在该用户
		if (id == 0) {// 如果不存在该用户继而判断
			// 如果用户名对应账户存在，表示为密码错误
			if (userDao.judgeNameExist(user.getUserName())) {
				System.out.println("密码错误！请核对后再登录");
				System.out.println("********************************");
			} else {
				// 如果用户名对应账户不存在
				System.out.println("该用户名不存在！请注册或核对后再登录");
				System.out.println("********************************");
			}
			return null;// 如果不存在 返回null

		}
		user.setId(id);// 将返回的id保存在user中
		return user;// 如果存在 返回user
	}

	/**
	 * 管理员登录
	 */
	public void adminLogin() {
		Users user = judgeLogin();
		if (user != null) {
			if (userDao.judgeUserOrAdmin(user)) {
				System.out.print("\t   登录中");
				Tools.imitateLogin();
				System.out.println("\t  登录成功");
				System.out.println("********************************");
				new AdminLoginOperateMenu().adminLoginShow();
			} else {
				System.out.println("********************************");
				System.out.println("您为用户账户！请选择用户登陆");
			}
		} else {
			System.out.println("登录失败！");
			System.out.println("********************************");
		}
	}

	/**
	 * 登录提示
	 * 
	 * @return
	 */
	private Users loginShow() {
		System.out.println("********************************");
		System.out.print("请输入登录名：");
		Scanner in = new Scanner(System.in);
		String userName = in.nextLine();
		System.out.print("请输入登录密码：");
		String pwd = in.nextLine();
		Tools.getCode();// 输入验证码 不区分大小写
		System.out.println("\n********************************");
		return new Users(userName, pwd, null, null, 0, 0);
	}
}
