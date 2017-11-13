package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.util.Tools;

public class MovieTypeListService {
	/**
	 * 管理员直接选择电影类型 不用再输入
	 * 
	 * @param choice
	 * @return
	 */
	public String movieTypeListControl() {
		int choice=Tools.getInt(1,13);
		String type = null;
		switch (choice) {
		case 1:
			type = "动作";
			break;
		case 2:
			type = "喜剧";
			break;

		case 3:
			type = "爱情";
			break;

		case 4:
			type = "科幻";
			break;

		case 5:
			type = "奇幻";
			break;

		case 6:
			type = "恐怖";
			break;

		case 7:
			type = "纪录";
			break;

		case 8:
			type = "犯罪";
			break;

		case 9:
			type = "战争";
			break;

		case 10:
			type = "冒险";
			break;

		case 11:
			type = "动画";
			break;

		case 12:
			type = "剧情";
			break;
			
		case 13:
			type = "其他";
			break;
			
		default:
			break;
		}
		return type;
	}
}
