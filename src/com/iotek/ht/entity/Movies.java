package com.iotek.ht.entity;

/**
 * 电影类
 * 
 * @author zhangjiaqi
 * 
 */
public class Movies {
	private int id;// 电影编号
	private String movieName;// 电影名称
	private String lead;// 电影主演
	private String time;// 上映时间
	private String movieDescribe;// 电影描述
	private String movieType;// 电影类型
	private int flag;// 设置电影的状态 判断是否在上映 还是下架了
	private int duration;// 播放时长

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
		return  "《"+movieName + "》\t\t<"
				+ lead+">领衔主演" + "\t\t<" + time + ">时上映\t\t讲的是:" + movieDescribe
				+ "\t\t\t" + movieType + "片\t" + "\t长达"
				+ duration + "分钟";
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
