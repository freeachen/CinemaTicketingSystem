package com.iotek.ht.db.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.iotek.ht.entity.Hall;

/**
 * 对场厅的数据库操作
 * @author zhangjiaqi
 *
 */
public class HallDao extends BaseDao{
	/**
	 * 新增场厅
	 * @param hall
	 * @return
	 */
	public boolean insert(Hall hall){
		String sql="insert into hall(hallnum,sessionid) values (?,?)";
		List<Object>params=new ArrayList<Object>();
		params.add(hall.getHallNum());
		params.add(hall.getSessionId());
		return sendBackWithNums(sql, params);
	}
}
