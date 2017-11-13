package com.iotek.ht.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.Tickets;

/**
 * 将购买的票的信息打印出来
 * 
 * @author zhangjiaqi
 * 
 */
public class FileinputTicketsBoughtService {
	/**
	 * 将每个用户购买的票写进文件中
	 * 
	 * @param ticketId
	 * @throws IOException
	 */
	public void fileIn(int ticketId) throws IOException {
		Tickets ticket = new TicketsDao().getTickets(ticketId);
		
		//记录所有用户购买过的记录
		File file2=new File("tiketsBought.txt");
		//打印用户买的票
		File file1 = new File("e:\\"+ticket.getUserName()+".txt");
		if (!file1.exists()) {// 如果没有这个文件 就重新创建
			file1.createNewFile();
		}
		if (!file2.exists()) {
			file2.createNewFile();
		}
		// 如果有 就进行读写操作
		// 只写进文件 已经付款的票
		// 付款后 获得所购买的票的所有信息
		FileWriter fw = new FileWriter(file1);//打印电影票给用户
		FileWriter f = new FileWriter(file2,true);//设置为追加
		fw.write("用户名:"+ticket.getUserName() + "\r\n观影影院:" + ticket.getCinemaName() + "\r\n场厅:"+ticket.getHall()+"\r\n观看影片:"
				+ ticket.getMovieName() + "\r\n座位号:" + ticket.getSeatNum() + "\r\n价格:"
				+ ticket.getPrice() + "\r\n场次时间:" + ticket.getTime() + "\r\n");
		f.write("用户名:"+ticket.getUserName() + "\r\n观影影院:" + ticket.getCinemaName() + "\r\n场厅:"+ticket.getHall()+"\r\n观看影片:"
				+ ticket.getMovieName() + "\r\n座位号:" + ticket.getSeatNum() + "\r\n价格:"
				+ ticket.getPrice() + "\r\n场次时间:" + ticket.getTime() + "\r\n\r\n");
		fw.close();
		f.close();

	}
}
