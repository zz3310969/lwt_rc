package com.bsc.util.db.tx;

import org.hibernate.Session;

/**
 * 实体更新原子操作，用来辅助Dao.doInTransaction()
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-6-3
 */
public class EntityUpdateOperation implements AtomOperation
{
	private Object entity;
	
	/**
	 * 构造EntityUpdateOperation
	 * @param entity 待更新的实体
	 */
	public EntityUpdateOperation(Object entity){
		this.entity = entity;
	}
	
	/**
	 * 执行原子操作
	 */
	public boolean execute(Session session) throws Exception {
		session.update(entity);
		return true;
	}
}


