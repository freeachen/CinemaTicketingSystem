package com.iotek.ht.view;

import com.iotek.ht.service.UserLookThroughMoviesReviewsControl;
import com.iotek.ht.util.Tools;

/**
 * �û������Ӱ ����Ӱ������
 * 
 * @author zhangjiaqi
 * 
 */
public class UserLookThroughMoviesReviewMenu {
	public void userLookThroughMoivesShow(int userId) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>����Ӱ������Ӱ��<==");
		System.out.println("\t\t2==>�� �� �� ����Ӱ��<==");
		System.out.println("\t\t3==>ȫ����Ӱ����Ӱ��<==");
		System.out.println("\t\t0==>       ��      ��       <==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 3);
		new UserLookThroughMoviesReviewsControl().userLookThroughMoviesControl(
				choice, userId);
	}
}
