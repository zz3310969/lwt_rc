package com.bsc.util.db.tx;

import org.hibernate.Session;

/**
 * Hql更新原子操作，用来辅助Dao.doInTransaction()
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-6-3
 */
public class HqlUpdateOperation implements AtomOperation
{
	private String hql;
	
	/**
	 * 构造hql操作
	 * @param hql hql操作语句
	 */
	public HqlUpdateOperation(String hql) {
		this.hql = hql;
	}
	
	/**
	 * 执行原子操作
	 */
	public boolean execute(Session session) throws Exception{
		boolean flag = true;
		try{
		  session.createQuery(hql).executeUpdate();
		}catch(Exception e){
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
}
