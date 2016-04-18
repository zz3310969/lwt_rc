package com.bsc.service.system;

import com.bsc.bean.Loginlog;
import com.bsc.bean.Role;
import com.bsc.bean.Systemuser;
/**
 * 用户管理服务层
 * @author 韩进城
 *
 */
public interface IUserLoginService {
	/**
	 * 用户登录
	 * @param sysuser
	 * @return
	 */
	public Systemuser loginService(Systemuser sysuser);
	
	/**
	 * 保存登录日志
	 * @param sysuser
	 * @return
	 */
	public Loginlog logService(Systemuser sysuser);
	
}
