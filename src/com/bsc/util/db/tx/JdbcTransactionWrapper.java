package com.bsc.util.db.tx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Jdbc事务处理包装接口
 * @author  詹波
 * @version  1.6<br>
 * Copyright (C), 2003-2008, 北京信息管理科学研究所<br>
 * This program is protected by copyright laws.<br>
 * Date: 2010-3-11
 */
public abstract class JdbcTransactionWrapper
{
	/**
	 * 在Jdbc事务中执行数据库操作
	 * <p>
	 * 请勿忘记调用close(rs, st)关闭ResultSet与Statement
	 * @param connection 数据操作所依赖的Connection connection
	 * @return 用户决定业务在执行过程中是否正确
	 * @throws Exception 如果数据库操作出现异常则抛出
	 */
	public abstract boolean execute(Connection connection) throws Exception;
	
	/**
	 * 关闭ResultSet与Statement
	 * @param rs ResultSet
	 * @param st Statement
	 * @throws Exception
	 */
	protected void close(ResultSet rs, Statement st) throws Exception {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
	}
}





