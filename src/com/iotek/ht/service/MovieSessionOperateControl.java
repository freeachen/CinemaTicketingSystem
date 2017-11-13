package com.iotek.ht.service;

import com.iotek.ht.view.AdminLoginOperateMenu;
import com.iotek.ht.view.MovieSessionMenu;

/**
 * ��ת�����β������ܿ�� ��ת��
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionOperateControl {
	private MovieSessionOperateService msos = new MovieSessionOperateService();

	public void movieSessionControl(int choice) {
		switch (choice) {
		case 1:
			// ��������
			msos.insertSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 2:
			// ɾ������
			msos.deleteSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 3:
			// �޸ĳ���
			msos.updateSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 4:
			// �鿴ָ����Ӱ����
			msos.showSessionFromMovie();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 5:
			// �鿴ָ����ӰԺ����
			msos.showSessionFromCinnema();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 6:
			// �鿴���г���
			msos.showAllSession();
			new MovieSessionMenu().movieSessionShow();
			break;
		case 0:
			System.out.println("������һ��˵�");
			new AdminLoginOperateMenu().adminLoginShow();
			break;

		default:
			break;
		}

	}
}
