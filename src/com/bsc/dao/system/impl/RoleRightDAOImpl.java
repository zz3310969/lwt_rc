package com.bsc.dao.system.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Roleright;
import com.bsc.dao.system.IRoleRightDAO;
/**
 * 角色权限实现DAO
 * @author hanjin7278
 *
 */
public class RoleRightDAOImpl extends HibernateDaoSupport implements IRoleRightDAO {

	public boolean onCreate(Roleright t) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().saveOrUpdate(t);
		
		return true;
	}

	public boolean onRemove(Roleright t) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().delete(t);
		
		return true;
	}

	public boolean onUpdate(Roleright t) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().update(t);
		
		return true;
	}

	public List<Roleright> findAll(int cp, int ls) throws Exception {
		// TODO Auto-generated method stub
		
		List<Roleright> list = null;
		
		list = this.getHibernateTemplate().find("FROM Roleright");
		
		return list;
	}

	public Roleright findById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		List<Roleright> list = null;
		
		list = this.getHibernateTemplate().find("FROM Roleright WHERE id.roleId=?", id);
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return null;
	}

	public Long getCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
