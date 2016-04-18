package com.bsc.util.db.tx;

import org.hibernate.Session;

/**
 * 数据库原子操作接口，用来辅助Dao.doInTransaction()
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-6-3
 */
public interface AtomOperation
{
	/**
	 * 执行原子操作
	 */
	public boolean execute(Session session) throws Exception;
}


