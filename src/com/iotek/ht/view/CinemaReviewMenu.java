package com.iotek.ht.view;

import com.iotek.ht.entity.Cinema;
import com.iotek.ht.service.CinemaReviewControl;
import com.iotek.ht.util.Tools;

/**
 * ֻ�е�½�Ŀ�������ӰԺ �ο�ֻ�ܿ� ����ֻ���ڵ�ӰԺ������Ӱ�Ĳ�������
 * 
 * @author zhangjiaqi
 * 
 */
public class CinemaReviewMenu {

	public void cinemaJudgeShow(int userId, Cinema cinema) {
		System.out.println("********************************");
		System.out.println("\n\t\t1==>��ӰԺ����<==");
		System.out.println("\t\t2==>дӰԺ����<==");
		System.out.println("\t\t3==>ɾӰԺ����<==");
		System.out.println("\t\t0==>������һ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");
		int choice = Tools.getInt(0, 3);
		new CinemaReviewControl().cinemaReviewControl(choice, userId, cinema);
	}
}
