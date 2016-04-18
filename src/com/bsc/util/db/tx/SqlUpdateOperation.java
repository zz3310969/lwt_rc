package com.bsc.util.db.tx;

import org.hibernate.Session;

/**
 * Sql更新原子操作，用来辅助Dao.doInTransaction()
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-9-8
 */
public class SqlUpdateOperation implements AtomOperation
{
	private String sql;
	
	/**
	 * 构造sql操作
	 * @param sql sql操作语句
	 */
	public SqlUpdateOperation(String sql) {
		this.sql = sql;
	}
	
	/**
	 * 执行原子操作
	 */
	public boolean execute(Session session) throws Exception{
		return session.createSQLQuery(sql).executeUpdate() > 0;
	}
}
