package com.iotek.ht.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Tools {
	private static int num = 0;// ������

	/**
	 * ���������ļ�
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Properties myLoad() throws IOException {
		Properties prop = new Properties();
		BufferedReader br = new BufferedReader(
				new FileReader("data.properties"));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] arr = line.split("=");
			prop.setProperty(arr[0], arr[1]);
		}
		br.close();
		return prop;
	}

	/**
	 * ������֤��
	 * 
	 * @return
	 */
	public static void getCode() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] codeSequence = str.toCharArray();
		// ����һ����̬�ַ��������Ų�������֤��
		StringBuilder sb = new StringBuilder();
		// ������ �����ĸ��ַ�����֤��
		int count = 0;
		Random random = new Random();
		while (true) {
			char c = codeSequence[random.nextInt(codeSequence.length)];
			if (sb.indexOf(c + "") == -1) {
				sb.append(c);
				count++;
				if (count == 4) {
					break;
				}
			}
		}
		Scanner in = new Scanner(System.in);
		String code = sb.toString();// ��֤��
		System.out.println("��������֤��:" + code);
		String input = in.nextLine();
		if (!input.equalsIgnoreCase(code)) {
			System.out.println("��֤���������������:");
			getCode();
		}else {
			System.out.println("��֤����ȷ!");
		}
	}

	/**
	 * ���Ʊ�������һ���ڲ������ڵ�����
	 * 
	 * @param num
	 * @return
	 */
	public static int getInt(int min, int max) {
		int num = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("������" + min + "��" + max + "������:==>");
		try {
			num = in.nextInt();
			if (!(min <= num && num <= max)) {
				num = getInt(min, max);
			}
		} catch (Exception e) {// ������������쳣 ��������
			num = getInt(min, max);
		}
		return num;
	}

	/**
	 * ģ���¼ʱ����ʱ
	 */
	public static void imitateLogin() {
		int count = 3;
		while (count > 0) {
			count--;
			System.out.print("\n\t\t��");
			try {
				// ģ���¼�ĵȴ�ʱ��1.5��
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println();
	}

}
