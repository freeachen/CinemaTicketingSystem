package com.iotek.ht.view;

import com.iotek.ht.service.MovieReviewControl;
import com.iotek.ht.util.Tools;

/**
 * Ӱ���˵�
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieReviewsMenu {
	/**
	 * ֻ�е�½�Ŀ�������Ӱ   �ο�ֻ�ܿ�
	 * 
	 * @param userId
	 */
	public void movieJudgeShow(int userId,String movieName){
		System.out.println("********************************");
		System.out.println("\n\t\t1 ==>д   Ӱ  ��<==");
		System.out.println("\t\t2==>ɾ   Ӱ  ��<==");
		System.out.println("\t\t3==>��   Ӱ  ��<==");
		System.out.println("\t\t0==>������һ��<==\n");
		System.out.println("********************************");
		System.out.println("��ѡ��");	
		int choice = Tools.getInt(0, 3);
		new MovieReviewControl().movieReviewControl(choice, userId,movieName);
	}
}
