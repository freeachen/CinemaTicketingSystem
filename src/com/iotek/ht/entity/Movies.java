package com.iotek.ht.entity;

/**
 * ��Ӱ��
 * 
 * @author zhangjiaqi
 * 
 */
public class Movies {
	private int id;// ��Ӱ���
	private String movieName;// ��Ӱ����
	private String lead;// ��Ӱ����
	private String time;// ��ӳʱ��
	private String movieDescribe;// ��Ӱ����
	private String movieType;// ��Ӱ����
	private int flag;// ���õ�Ӱ��״̬ �ж��Ƿ�����ӳ �����¼���
	private int duration;// ����ʱ��

	public Movies() {
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return  "��"+movieName + "��\t\t<"
				+ lead+">��������" + "\t\t<" + time + ">ʱ��ӳ\t\t������:" + movieDescribe
				+ "\t\t\t" + movieType + "Ƭ\t" + "\t����"
				+ duration + "����";
	}

	public Movies(String movieName, String lead, String time,
			String movieDescribe, String movieType, int flag, int duration) {
		super();
		this.movieName = movieName;
		this.lead = lead;
		this.time = time;
		this.movieDescribe = movieDescribe;
		this.movieType = movieType;
		this.flag = flag;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMovieDescribe() {
		return movieDescribe;
	}

	public void setMovieDescribe(String movieDescribe) {
		this.movieDescribe = movieDescribe;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
