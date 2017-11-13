package com.iotek.ht.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Tools {
	private static int num = 0;// 计数器

	/**
	 * 加载配置文件
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
	 * 产生验证码
	 * 
	 * @return
	 */
	public static void getCode() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] codeSequence = str.toCharArray();
		// 定义一个动态字符串数组存放产生的验证码
		StringBuilder sb = new StringBuilder();
		// 计数器 产生四个字符的验证码
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
		String code = sb.toString();// 验证码
		System.out.println("请输入验证码:" + code);
		String input = in.nextLine();
		if (!input.equalsIgnoreCase(code)) {
			System.out.println("验证码错误！请重新输入:");
			getCode();
		}else {
			System.out.println("验证码正确!");
		}
	}

	/**
	 * 限制必须输入一个在参数以内的数字
	 * 
	 * @param num
	 * @return
	 */
	public static int getInt(int min, int max) {
		int num = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("请输入" + min + "—" + max + "的数字:==>");
		try {
			num = in.nextInt();
			if (!(min <= num && num <= max)) {
				num = getInt(min, max);
			}
		} catch (Exception e) {// 如果捕获到输入异常 重新输入
			num = getInt(min, max);
		}
		return num;
	}

	/**
	 * 模拟登录时的延时
	 */
	public static void imitateLogin() {
		int count = 3;
		while (count > 0) {
			count--;
			System.out.print("\n\t\t·");
			try {
				// 模拟登录的等待时间1.5秒
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println();
	}

}
