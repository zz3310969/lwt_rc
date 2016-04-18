package com.bsc.util.db.tx;

import org.hibernate.Session;

/**
 * 数据库事务处理包装接口
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2009-6-8
 */
public interface TransactionWrapper
{
	/**
	 * 
	 * @param session 数据操作所依赖的Hibernate Session
	 * @return 用户决定业务在执行过程中是否正确
	 * @throws Exception 如果数据库操作出现异常则抛出
	 */
	public boolean execute(Session session) throws Exception;
}





