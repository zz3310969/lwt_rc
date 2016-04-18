package com.bsc.dao.system.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Loginlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.IUserManagerDAO;
/**
 * DAO层用户管理接口实现
 * @author hanjin7278
 *
 */
public class UserManagerDAOImpl extends HibernateDaoSupport implements IUserManagerDAO {

	/**
	 * 查询当前用户
	 */
	public Systemuser checkLogin(Systemuser sys) throws Exception {
		// TODO Auto-generated method stub
		Systemuser user = null;
		
		String sql ="FROM Systemuser WHERE userName = ? AND password = ?";
		
		List<Systemuser> list = this.getHibernateTemplate().find(sql,new Object[]{sys.getUserName(),sys.getPassword()});
		
		if(!list.isEmpty()){
			
			user = list.get(0);
		}
		
		return user;
	}
	/**
	 * 写入登录日志
	 */
	public Loginlog writerLoginLog(Systemuser sys) throws Exception {
		// TODO Auto-generated method stub
		Loginlog login = new Loginlog();
		
		login.setLogId(UUID.randomUUID().toString());
		login.setLoginTime(new Date());
		login.setSystemuser(sys);
		
		this.getHibernateTemplate().save(login);
		
		return login;
	}

}
