package com.bsc.service.system.impl;

import com.bsc.bean.Loginlog;
import com.bsc.bean.Role;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.IRoleManager;
import com.bsc.dao.system.IUserManagerDAO;
import com.bsc.dao.system.impl.RoleManagerImpl;
import com.bsc.dao.system.impl.UserManagerDAOImpl;
import com.bsc.service.system.IUserLoginService;
/**
 * 用户登录实现
 * @author 韩进城
 *
 */
public class UserLoginServiceImpl implements IUserLoginService {
	
	private IUserManagerDAO userDao; //用户管理

	private IRoleManager rolemanager;  //角色管理
	
	/**
	 * 用户登录
	 */
	public Systemuser loginService(Systemuser sysuser) {
		// TODO Auto-generated method stub
		Systemuser user = null;
		
		try {
			
			user = userDao.checkLogin(sysuser);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	/**
	 * 写入登录日志
	 */
	public Loginlog logService(Systemuser sysuser) {
		// TODO Auto-generated method stub
		Loginlog log = null;
		try {
			log = this.userDao.writerLoginLog(sysuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
	}
	public IUserManagerDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserManagerDAO userDao) {
		this.userDao = userDao;
	}
	public IRoleManager getRolemanager() {
		return rolemanager;
	}
	public void setRolemanager(IRoleManager rolemanager) {
		this.rolemanager = rolemanager;
	}
	
}
