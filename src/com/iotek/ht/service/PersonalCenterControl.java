package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.OrderFormMenu;
import com.iotek.ht.view.PersonalCenterMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * 跳到个人中心实现块 的中转层
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterControl {
	private PersonalCenterService pcs = new PersonalCenterService();

	public void centerControl(int choice, int id) {
		switch (choice) {
		case 1:
			// 查看个人信息
			pcs.showInformation(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 2:
			// 修改密保信息
			pcs.updateQuestion(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 3:
			// 修改密码
			pcs.updatePwd(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 4:
			// 查看全部订单
			new OrderFormMenu().orderFormShow(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 0:
			System.out.println("返回上一层");
			new UserLogedOperateMenu().userLoginShow(id);
			;
			break;

		default:
			break;
		}

	}
}
