package com.iotek.ht.view;

import com.iotek.ht.service.MovieReviewControl;
import com.iotek.ht.util.Tools;

/**
 * 影评菜单
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieReviewsMenu {
	/**
	 * 只有登陆的可以评电影   游客只能看
	 * 
	 * @param userId
	 */
	public void movieJudgeShow(int userId,String movieName){
		System.out.println("********************************");
		System.out.println("\n\t\t1 ==>写   影  评<==");
		System.out.println("\t\t2==>删   影  评<==");
		System.out.println("\t\t3==>读   影  评<==");
		System.out.println("\t\t0==>返回上一层<==\n");
		System.out.println("********************************");
		System.out.println("请选择：");	
		int choice = Tools.getInt(0, 3);
		new MovieReviewControl().movieReviewControl(choice, userId,movieName);
	}
}
