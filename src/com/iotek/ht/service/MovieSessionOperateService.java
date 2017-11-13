package com.iotek.ht.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.iotek.ht.db.dao.impl.CinemaDao;
import com.iotek.ht.db.dao.impl.MovieSessionDao;
import com.iotek.ht.db.dao.impl.MoviesDao;
import com.iotek.ht.db.dao.impl.TicketsDao;
import com.iotek.ht.entity.Cinema;
import com.iotek.ht.entity.MovieSession;
import com.iotek.ht.entity.Movies;
import com.iotek.ht.util.Tools;
import com.iotek.ht.view.MovieSessionMenu;

/**
 * �Գ��εĲ��� ���幦�ܵ�ʵ��
 * 
 * @author zhangjiaqi
 * 
 */
public class MovieSessionOperateService {
	// ���õ���Ӱ�� �� ��ӰԺ�������
	private MoviesDao moviesDao = new MoviesDao();
	private CinemaDao cinemaDao = new CinemaDao();
	private MovieSessionDao movieSessionDao = new MovieSessionDao();
	private static int count = 0;// ����һ������״̬

	/**
	 * ��������
	 */
	public void insertSession() {
		MovieSession movieSession = insertSessionShow();
		if (movieSessionDao.insert(movieSession)) {
			System.out.println("��ӳɹ���");
			int id = movieSessionDao.selectId(movieSession.getCinemaId(),
					movieSession.getMovieId());
			movieSession.setId(id);
			// ����һ������ �Ͳ�����Ӧ��Ʊ
			new TicketsDao().generateTickets(movieSession);
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	/**
	 * ɾ������
	 */
	public void deleteSession() {
		// ��ʾ���г��� ����ѡ�����ɾ��
		List<MovieSession> list = movieSessionDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			// ��ӡ���г�����Ϣ
			System.out.print((i + 1) + "==>" + list.get(i).getCinemaName()
					+ "\t");
			System.out.print(list.get(i).getAddress() + "��" + "\t");
			System.out.print(list.get(i).getHall() + "����" + "\t\t");
			System.out.print(list.get(i).getMovieName() + "\t\t");
			// System.out.println(list.get(i).getTime());
			String time = list.get(i).getTime();// ȡ��ʱ��
			// �Թ̶���ʽ���
			printTime(time);
		}
		int choice = Tools.getInt(1, list.size());
		int id = list.get(choice - 1).getId();
		System.out.println("ȷ��ɾ��?");
		System.out.println("1==>����");
		System.out.println("2==>ȷ��");
		int select = Tools.getInt(1, 2);
		if (select == 2) {
			// Ȼ�����idɾ������
			if (movieSessionDao.delete(id)) {
				System.out.println("ɾ���ɹ���");
				// ɾ������ʱ ɾ�������ĵ�ӰƱ
				new TicketsDao().deleteTickets(id);
			} else {
				System.out.println("ɾ��ʧ�ܣ�");
			}
		} else {
			System.out.println("���ѷ���ɾ����������");
		}
	}

	public void printTime(String time) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
		String[] str = time.split("����");
		Date begin;
		Date end;
		try {
			begin = sdf1.parse(str[0]);
			end = sdf1.parse(str[1]);
			System.out.print(sdf2.format(begin) + "����");
			System.out.println(sdf3.format(end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �޸ĳ���
	 */
	public void updateSession() {
		Scanner in = new Scanner(System.in);
		// ���ȴ�ӡ���г��� ѡ��Ҫ�޸ĵĳ���
		List<MovieSession> list = movieSessionDao.selectAll();
		if (list.size() != 0) {
			printSession(list);// ��ӡ���г�����Ϣ
			// ѡ��Ҫ�޸ĵĳ���
			System.out.println("ѡ����Ҫ�޸ĵĳ���:");
			int choice = Tools.getInt(1, list.size());
			int sessionId = list.get(choice - 1).getId();// ��¼Ҫ�޸ĵĳ��ε�id
			int hall = list.get(choice - 1).getHall();// ��¼����
			int cinemaId = list.get(choice - 1).getCinemaId();// ӰԺid
			System.out.println("ȷ��Ҫ�޸ģ�");
			System.out.println("1==>����");
			System.out.println("2==>����");
			if (Tools.getInt(1, 2) == 2) {
				Movies movie = null;
				System.out.println("������¼��Ҫ��ӳ�ĵ�Ӱ:");
				System.out.println("�����ѡ��:");
				movie = generateMovieList();
				int movieID = movie.getId();// ��Ϊ���α��һ������ֶδ��볡�α�
				System.out.println("��¼���ӳʱ��:");
				// ��ѡ������ ����ʱ��İ���ʱ ��Ҫ�ж���
				String time = null;
				try {
					time = generateTime(cinemaId, movieID, hall);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("������Ʊ��:");
				double price = in.nextDouble();
				MovieSession movieSession = new MovieSession(movieID, time, 0,
						0, 0, price, 0);
				// �޸�
				if (movieSessionDao.update(movieSession, sessionId)) {
					System.out.println("�޸ĳɹ���");
				} else {
					System.out.println("�޸�ʧ�ܣ�");
				}
			} else {
				System.out.println("���ѷ����޸ġ�����");
			}
		}
	}

	/**
	 * �鿴ָ����Ӱ�ĳ���
	 */
	public void showSessionFromMovie() {
		System.out.println("�������Ӱ��:");
		Scanner in = new Scanner(System.in);
		String movieName = in.next();
		List<MovieSession> list = movieSessionDao
				.selectSessionFromMovie(movieName);
		printSession(list);

	}

	/**
	 * ����õĳ����б��ӡ���
	 * 
	 * @param list
	 */
	public void printSession(List<MovieSession> list) {
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				MovieSession m = list.get(i);
				System.out.print((i + 1) + "\t==>" + m.getCinemaName() + "\t\t"
						+ m.getHall() + "����" + "\t\t");
				System.out.print(m.getAddress() + "��\t��");
				System.out.print(m.getMovieName() + "��\t\t\t����:");
				System.out.print(m.getPrice() + "Ԫ\t\tƬ��:");
				System.out.print(m.getLasting() + "����\t\t��ӳʱ��:");
				String time = m.getTime();
				printTime(time);
			}
			System.out.println();
		} else {
			System.out.println("δ�ҵ���س���");
		}
	}

	/**
	 * �鿴ָ����ӰԺ�ĳ���
	 */
	public void showSessionFromCinnema() {
		System.out.println("��ѡ���ӰԺ��:");
		String cinemaName = getCinemaList();
		List<MovieSession> list = movieSessionDao
				.selectSessionFromCinema(cinemaName);
		printSession(list);
	}

	/**
	 * �鿴���г���
	 */
	public void showAllSession() {
		List<MovieSession> list = movieSessionDao.selectAll();
		printSession(list);
	}

	/**
	 * ���ɵ�ӰԺ�б� ֻ�ǳ��α��еĵ�ӰԺ ����ѡ���ӰԺ��
	 * 
	 * @return ��ӰԺ��
	 */
	public String getCinemaList() {
		Scanner in = new Scanner(System.in);
		TreeMap<String, String> map = movieSessionDao.getCinemaName();
		// �ҵ����Ҳ����������Ҫ�ж�
		if (map.size() != 0) {
			System.out.println("********************************");
			System.out.println("��ѡ���ӰԺ:");
			// ����ҵ�����ӵ����εĵ�ӰԺ
			// ��ӡѡ���Ӧ�ĵ�ӰԺ
			Set<Entry<String, String>> entrySet = map.entrySet();
			int count = 0;
			for (Entry<String, String> entry : entrySet) {
				count++;
				System.out.println(count + "===>" + entry.getValue() + "--"
						+ entry.getKey() + "<===");
			}
			System.out.println("********************************");
			int choice = Tools.getInt(1, map.size());
			System.out.println("********************************");
			// ��¼����ѡ���Ӧ�ĵ�ӰԺ
			int location = 0;
			String cinemaName = null;
			for (Entry<String, String> entry : entrySet) {
				location++;
				if (location == choice) {
					cinemaName = entry.getValue();
				}
			}
			return cinemaName;
		}
		// ���û�ҵ����ؿ�ֵ
		return null;

	}

	/**
	 * ���볡�ε���Ϣ ���س��ε���Ϣ��Ϊ ������
	 * 
	 * @return
	 */
	private MovieSession insertSessionShow() {
		Scanner in = new Scanner(System.in);
		Movies movie = new Movies();
		Cinema cinema = new Cinema();
		System.out.println("��¼��Ҫ��ӳ��ӰԺ:");
		System.out.println("�����ѡ��:");
		cinema = generateCinemaList();
		int cinemaID = cinema.getId();// ��Ϊ���α��һ������ֶδ��볡�α�
		System.out.println("��¼��Ҫ��ӳ�ĵ�Ӱ:");
		System.out.println("�����ѡ��:");
		movie = generateMovieList();
		int movieID = movie.getId();// ��Ϊ���α��һ������ֶδ��볡�α�
		System.out.println("��¼��Ҫ��ӳӰԺ�ĳ�����:");
		// ����ӰԺȥ�Ҷ�Ӧ�ĳ������� Ȼ�����ѡ��
		int hall = generateHallsList(cinemaID);// ��¼ѡ��ķ�ӳ����
		System.out.println("��¼���ӳʱ��:");
		// ��ѡ������ ����ʱ��İ���ʱ ��Ҫ�ж���
		String time = null;
		try {
			time = generateTime(cinemaID, movieID, hall);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// �����ӰԺ������������ʱ���Ƿ��г�ͻ
		System.out.println("��¼�볡����������:");
		int container = Integer.parseInt(in.next());
		System.out.println("������Ʊ��:");
		double price = in.nextDouble();
		// ��ʼ��ʱ ��ʣ����λ���ó��������� ��ʾδ������λ��״̬
		return new MovieSession(movieID, time, cinemaID, container, container,
				price, hall);

	}

	/**
	 * ���ų���ʱ ������γ�ͻ ����ʱ��ε�ѡ��
	 * 
	 * @param cinemaId
	 * @param movieId
	 * @param hall
	 * @return
	 * @throws ParseException
	 */
	private String generateTime(int cinemaId, int movieId, int hall)
			throws ParseException {
		Scanner in = new Scanner(System.in);
		// ����movieid��ö�Ӧ��ʱ��
		int lasting = moviesDao.selectTime(movieId);
		// ���ҵ������ӰԺ������ŵĵ�Ӱ������ʱ���
		List<String> allTime = movieSessionDao.getAllTime(cinemaId, hall);
		// ϵͳֱ����ʾ¼����¡��ա�Сʱ�������ֶ�
		System.out.println("******************");
		System.out.println("��¼����ʼʱ��:");
		System.out.println("\t\t���������:");
		int year = in.nextInt();
		System.out.println("\t\t��ѡ���·�:");
		int month = Tools.getInt(1, 12);
		System.out.println("\t\t��ѡ�����:");
		// ����Ҫ��һ���ж��·ݶ�Ӧ��������ѡ��
		int day = Tools.getInt(1, getDays(year, month));
		System.out.println("\t\tѡ��Сʱ��:");
		int hour = Tools.getInt(0, 23);
		System.out.println("\t\tѡ�������:");
		int minute = Tools.getInt(0, 59);
		String beginTime = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute;
		// �ɳ��εĿ�ʼʱ���볡�ε�ʱ������ó��εĽ���ʱ��
		String endTime = getEndTime(lasting, beginTime);
		if (allTime != null && allTime.size() != 0) {// �鵽������а��Ź�ʱ�ε���� ��Ҫ�ж����޳�ͻ
			// �������õ���ʱ����ԡ���������
			List<String> begins = new ArrayList<String>();// ��ſ�ʼʱ��
			List<String> ends = new ArrayList<String>();// ��Ž���ʱ��
			for (int i = 0; i < allTime.size(); i++) {
				begins.add(allTime.get(i).split("����")[0]);// ǰ�����ǿ�ʼʱ��
				ends.add(allTime.get(i).split("����")[1]);// �󲿷��ǽ���ʱ��
			}
			// ����ģ���ʽ
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			boolean flag = true;// ��ʾ�޳�ͻ
			Date beginDate = sdf.parse(beginTime);
			Date endDate = sdf.parse(endTime);
			for (int i = 0; i < allTime.size(); i++) {
				Date begin = sdf.parse(begins.get(i));// ��õ�����ʱ��εĿ�ʼʱ��
				Date end = sdf.parse(ends.get(i));// ��õ�����ʱ��εĽ���ʱ��
				/*
				 * ¼��Ŀ�ʼʱ��������ʱ��ν���ʱ��֮�� ���� ¼��Ľ���ʱ��������ʱ��ο�ʼ֮ǰ �����Ͳ����г�ͻ ��������г�ͻ�����
				 * ��ô�ý� ״̬��Ϊfalse ��������г�ͻ
				 */
				// if (!(beginDate.after(end) || endDate.before(begin))) {
				// flag = false;// �г�ͻ
				// break;// ֻҪ�ҵ���ͻ������ѭ��
				// }
				// �����г�ͻ�����
				if (((beginDate.after(begin)) && (endDate.before(end)))
						|| ((beginDate.before(begin)) && (endDate.after(begin)))
						|| ((beginDate.before(end)) && (endDate.after(end)))
						|| ((beginDate.before(begin)) && (endDate.after(end)))) {
					flag = false;
					break;
				}
			}
			if (flag) {// �޳�ͻ
				count = 0;// ���û�г�ͻ��������Ҳ��ԭ
				return beginTime + "����" + endTime;// ���ص�ʱ���ԡ��������ָ�
			} else {
				count++;
				if (count == 2) {// �����ͻ���ξ��˳�
					count = 0;// ͬʱ��������ԭ
					System.out.println("ʱ��γ�ͻ!��������ʱ�䣺");
					System.out.println("������˼�����2�ζ�ʧ�ܣ�\n����ϵͳ�Զ��˳���");
					// ����ѭ��
					new MovieSessionMenu().movieSessionShow();
				}
				// �г�ͻѭ��ѡ��2�ξ��˳�ѭ��
				System.out.println("ʱ��γ�ͻ!��������ʱ�䣺");
				generateTime(cinemaId, movieId, hall);
			}

		} // �������ѡ���ʱ����û�а��Ź����ε� �Ϳ���ֱ����� �����жϳ�ͻ
		return beginTime + "����" + endTime;// ���ص�ʱ���ԡ��������ָ�

	}

	/**
	 * �ɳ��εĿ�ʼʱ���볡�ε�ʱ������ó��εĽ���ʱ��
	 * 
	 * @param lasting
	 * @param beginTime
	 * @return
	 */
	private String getEndTime(int lasting, String beginTime) {
		long time = 0L;// ����1970���׼ʱ����������ǰ��ʼʱ��ĺ�����
		Date beginDate = null;
		String endTime = null;
		// ����ģ���ʽ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			beginDate = sdf.parse(beginTime);
			time = beginDate.getTime();
			// ����ʱ��
			beginDate.setTime((long) (time + lasting * 60 * 1000));
			endTime = sdf.format(beginDate);// ����ʱ��
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endTime;
	}

	/**
	 * ���������������·� ���ص������� �ж�
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDays(int year, int month) {
		boolean isyear = false;
		int day = 0;
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			isyear = true;// ��־������
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			if (isyear) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		default:
			break;
		}
		return day;
	}

	private int generateHallsList(int cinemaID) {
		int halls = cinemaDao.selectById(cinemaID).getHalls();
		for (int i = 0; i < halls; i++) {
			System.out.println((i + 1) + "===>" + (i + 1) + "����" + "<===");
		}
		int choice = Tools.getInt(1, halls);// �ӳ�����ѡ������
		System.out.println("��ѡ��ĳ�����:" + choice);
		return choice;
	}

	/**
	 * ���ɵ�Ӱ�б����ѡ�� ��������ѡ��ĵ�Ӱ����Ӱid�Ķ�����Ϣ
	 * 
	 * @return movie
	 */
	private Movies generateMovieList() {
		// ����ѡ��ĵ�Ӱ�����Ӱid
		Movies movie = new Movies();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();
		List<Movies> moviesList = moviesDao.selectAll();
		for (int i = 0; i < moviesList.size(); i++) {
			// ����Ӱ���е�id �� ��Ӱ�� �Ž�treemap�� ��Ϊ��ֵ�� һһ��Ӧ
			// ���ｫ��Ӱ������ id��ֵ Ϊ����ͨ����Ӱ�����ҵ�id
			list.put(moviesList.get(i).getMovieName(), moviesList.get(i)
					.getId());
		}
		// �����е�Ӱ��
		List<String> movieNameList = new ArrayList<String>();
		for (int i = 0; i < moviesList.size(); i++) {
			movieNameList.add(moviesList.get(i).getMovieName());
		}
		// ��ӡѡ���Ӧ�ĵ�Ӱ
		for (int i = 0; i < movieNameList.size(); i++) {
			System.out
					.println((i + 1) + "===>" + movieNameList.get(i) + "<===");
		}
		int choice = Tools.getInt(1, movieNameList.size());
		// ��¼����ѡ���Ӧ�ĵ�Ӱ��
		String movieName = movieNameList.get(choice - 1);
		System.out.println("��ѡ��ĵ�Ӱ��:" + movieName);
		int id = list.get(movieName);// ���ݵ�Ӱ��ȥ�Ҽ� ��¼ѡ��ĵ�Ӱ����Ӧ�ĵ�Ӱid
		movie.setId(id);
		movie.setMovieName(movieName);
		return movie;

	}

	/**
	 * ���ɵ�ӰԺ�б����ѡ�� ��������ѡ��ĵ�ӰԺ����ӰԺid�Ķ�����Ϣ
	 * 
	 * @return
	 */
	private Cinema generateCinemaList() {
		// ����ѡ��ĵ�ӰԺ�����ӰԺid���ӰԺ��ַ
		Cinema cinema = new Cinema();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();
		List<Cinema> cinemaList = cinemaDao.selectAll();
		for (int i = 0; i < cinemaList.size(); i++) {
			// ����ӰԺ���е�id �� ��ӰԺ�� �Ž�treemap�� ��Ϊ��ֵ�� һһ��Ӧ
			// ���ｫ��ӰԺ������ id��ֵ Ϊ����ͨ����ӰԺ�����ҵ�id
			list.put(cinemaList.get(i).getCinemaName(), cinemaList.get(i)
					.getId());
		}
		// �����е�ӰԺ��
		List<String> cinemaNameList = new ArrayList<String>();
		for (int i = 0; i < cinemaList.size(); i++) {
			cinemaNameList.add(cinemaList.get(i).getCinemaName());
		}
		// ��ӡѡ���Ӧ�ĵ�ӰԺ
		for (int i = 0; i < cinemaNameList.size(); i++) {
			String name = cinemaNameList.get(i);
			// �������� ȥ�Ҽ�ֵ�Զ�Ӧ��id
			int id = list.get(name);
			System.out.println((i + 1) + "===>" + name + "--"
					+ cinemaDao.selectAddress(id) + "��" + "<===");
		}
		int choice = Tools.getInt(1, cinemaNameList.size());
		// ��¼����ѡ���Ӧ�ĵ�ӰԺ��
		String cinemaName = cinemaNameList.get(choice - 1);
		int id = list.get(cinemaName);// ��¼ѡ��ĵ�ӰԺ����Ӧ�ĵ�ӰԺid
		System.out.println("ѡ��ĵ�ӰԺ��:" + cinemaName + "--"
				+ cinemaDao.selectAddress(id) + "��");
		cinema.setId(id);
		cinema.setCinemaName(cinemaName);
		return cinema;

	}
}
