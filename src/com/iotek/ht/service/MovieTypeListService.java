package com.iotek.ht.service;

import java.util.Scanner;

import com.iotek.ht.util.Tools;

public class MovieTypeListService {
	/**
	 * ����Աֱ��ѡ���Ӱ���� ����������
	 * 
	 * @param choice
	 * @return
	 */
	public String movieTypeListControl() {
		int choice=Tools.getInt(1,13);
		String type = null;
		switch (choice) {
		case 1:
			type = "����";
			break;
		case 2:
			type = "ϲ��";
			break;

		case 3:
			type = "����";
			break;

		case 4:
			type = "�ƻ�";
			break;

		case 5:
			type = "���";
			break;

		case 6:
			type = "�ֲ�";
			break;

		case 7:
			type = "��¼";
			break;

		case 8:
			type = "����";
			break;

		case 9:
			type = "ս��";
			break;

		case 10:
			type = "ð��";
			break;

		case 11:
			type = "����";
			break;

		case 12:
			type = "����";
			break;
			
		case 13:
			type = "����";
			break;
			
		default:
			break;
		}
		return type;
	}
}
