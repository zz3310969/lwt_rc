package com.bsc.dao.system.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Nation;
import com.bsc.dao.system.INationDAO;

public class NationDAOImpl extends HibernateDaoSupport implements INationDAO {

	public List<Nation> findAll() throws Exception {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().find("FROM Nation");
	}

	public Nation findById(int id) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "FROM Nation WHERE code = ?";
		
		List<Nation> list = this.getHibernateTemplate().find(hql,id);
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return null;
	}

}
