package com.iotek.ht.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.Tickets;

/**
 * �������Ʊ����Ϣ��ӡ����
 * 
 * @author zhangjiaqi
 * 
 */
public class FileinputTicketsBoughtService {
	/**
	 * ��ÿ���û������Ʊд���ļ���
	 * 
	 * @param ticketId
	 * @throws IOException
	 */
	public void fileIn(int ticketId) throws IOException {
		Tickets ticket = new TicketsDao().getTickets(ticketId);
		
		//��¼�����û�������ļ�¼
		File file2=new File("tiketsBought.txt");
		//��ӡ�û����Ʊ
		File file1 = new File("e:\\"+ticket.getUserName()+".txt");
		if (!file1.exists()) {// ���û������ļ� �����´���
			file1.createNewFile();
		}
		if (!file2.exists()) {
			file2.createNewFile();
		}
		// ����� �ͽ��ж�д����
		// ֻд���ļ� �Ѿ������Ʊ
		// ����� ����������Ʊ��������Ϣ
		FileWriter fw = new FileWriter(file1);//��ӡ��ӰƱ���û�
		FileWriter f = new FileWriter(file2,true);//����Ϊ׷��
		fw.write("�û���:"+ticket.getUserName() + "\r\n��ӰӰԺ:" + ticket.getCinemaName() + "\r\n����:"+ticket.getHall()+"\r\n�ۿ�ӰƬ:"
				+ ticket.getMovieName() + "\r\n��λ��:" + ticket.getSeatNum() + "\r\n�۸�:"
				+ ticket.getPrice() + "\r\n����ʱ��:" + ticket.getTime() + "\r\n");
		f.write("�û���:"+ticket.getUserName() + "\r\n��ӰӰԺ:" + ticket.getCinemaName() + "\r\n����:"+ticket.getHall()+"\r\n�ۿ�ӰƬ:"
				+ ticket.getMovieName() + "\r\n��λ��:" + ticket.getSeatNum() + "\r\n�۸�:"
				+ ticket.getPrice() + "\r\n����ʱ��:" + ticket.getTime() + "\r\n\r\n");
		fw.close();
		f.close();

	}
}
