package com.iotek.ht.service;

import com.iotek.ht.view.LoginMenu;
import com.iotek.ht.view.MainMenu;
import com.iotek.ht.view.OrderFormMenu;
import com.iotek.ht.view.PersonalCenterMenu;
import com.iotek.ht.view.UserLogedOperateMenu;

/**
 * ������������ʵ�ֿ� ����ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class PersonalCenterControl {
	private PersonalCenterService pcs = new PersonalCenterService();

	public void centerControl(int choice, int id) {
		switch (choice) {
		case 1:
			// �鿴������Ϣ
			pcs.showInformation(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 2:
			// �޸��ܱ���Ϣ
			pcs.updateQuestion(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 3:
			// �޸�����
			pcs.updatePwd(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 4:
			// �鿴ȫ������
			new OrderFormMenu().orderFormShow(id);
			new PersonalCenterMenu().centerShow(id);
			break;
		case 0:
			System.out.println("������һ��");
			new UserLogedOperateMenu().userLoginShow(id);
			;
			break;

		default:
			break;
		}

	}
}
