package com.bsc.dao.system;

import com.bsc.bean.Loginlog;
import com.bsc.bean.Systemuser;

/**
 * 系统用户管理接口定义
 * @author 韩进城
 *
 */
public interface IUserManagerDAO {

	/**
	 * 用户登录验证
	 * @param sys 用户名和密码
	 * @return 用户实体信息
	 * @throws Exception
	 */
	public Systemuser checkLogin(Systemuser sys) throws Exception;
	/**
	 * 写入登录日志
	 * @param sys 用户实体信息
	 * @throws Exception
	 */
	public Loginlog writerLoginLog(Systemuser sys) throws Exception;
	
}
