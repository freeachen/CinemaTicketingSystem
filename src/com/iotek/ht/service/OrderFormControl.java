package com.iotek.ht.service;

import com.iotek.ht.view.OrderFormMenu;
import com.iotek.ht.view.PersonalCenterMenu;

/**
 * ��ת���Զ��������� ��ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class OrderFormControl {
	private OrderFormService ofs = new OrderFormService();

	public void orderFormControl(int choice, int userId) {
		switch (choice) {
		case 1:
			// �鿴���ж���
			ofs.showAllOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 2:
			// �鿴δ��ɶ���
			ofs.showUndoneOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 3:
			// ȡ��δ��ɶ���
			ofs.deleteUndoneOrder(userId);
			new OrderFormMenu().orderFormShow(userId);
			break;
		case 0:
			// ������һ��
			new PersonalCenterMenu().centerShow(userId);
			break;

		default:
			break;
		}

	}
}
