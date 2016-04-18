package com.bsc.dao.system.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Webpage;
import com.bsc.dao.system.IWebPageDAO;

public class WebPageDAOImpl extends HibernateDaoSupport implements IWebPageDAO {
	/**
	 * 查询所有webpage
	 */
	@SuppressWarnings("unchecked")
	public List<Webpage> findAll(final int pagesize,final int currentpage) throws Exception {
		// TODO Auto-generated method stub
		
		final String hql ="FROM Webpage";
		
		List<Webpage> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(hql);
				
				query.setFirstResult((currentpage -1 )* pagesize);
				
				query.setMaxResults(pagesize);
				
				return query.list();
			}
		});
		
		return list;
	}
	/**
	 * 根据ID查询一个WebPage
	 */
	public Webpage findByID(String pageId) throws Exception {
		// TODO Auto-generated method stub
		
		String hql ="FROM Webpage WHERE pageId = ?";
		
		List<Webpage> list = this.getHibernateTemplate().find(hql, pageId);
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return null;
	}
	/**
	 * 增加
	 */
	public boolean onCreatePage(Webpage page) throws Exception {
		// TODO Auto-generated method stub
		
		Serializable ss = this.getHibernateTemplate().save(page);
		
		if(ss != null){
			
			return true;
			
		}
		
		return false;
	}
	/**
	 * 删除
	 */
	public boolean onRemove(Webpage page) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().delete(page);
		
		return true;
	}
	/**
	 * 更新webpage
	 */
	public boolean onUpdate(Webpage page) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().update(page);
		
		return true;
	}
	/**
	 * 取得全部记录数
	 */
	public Long getCountPages() throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "SELECT count(pageId) FROM Webpage";
		
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(!list.isEmpty()){
			
			return list.get(0);
			
		}
		
		return 0L;
	}
	/**
	 * 删除全部
	 */
	public boolean deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		Session session = this.getSessionFactory().openSession();
		
		Query query = session.createQuery("DELETE from Webpage");
		
		int temp = query.executeUpdate();
		
		session.close();
		
		flag = temp>=0?true:false;
		
		return flag;
	}
	
	/**
	 * 模糊查询URL
	 */
	@SuppressWarnings("unchecked")
	public List<Webpage> findAll(final String kw, final int cp,final int ls) throws Exception {
		// TODO Auto-generated method stub
		
		final String hql = "FROM Webpage WHERE url like ?";
		
		List<Webpage> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hql);
				
				query.setString(0, "%"+kw+"%");
				query.setFirstResult((cp-1)*ls);
				query.setMaxResults(ls);
				return query.list();
			}
		});
		return list;
	}
	/**
	 * 模糊查询全部记录数
	 */
	public Long getCountPages(String kw) throws Exception {
		
		String hql = "SELECT count(pageId) FROM Webpage WHERE url like ?";
		
		List<Long> list = this.getHibernateTemplate().find(hql,"%"+kw+"%");
		
		if(!list.isEmpty()){
			
			return list.get(0);
			
		}
		
		return 0L;
		

	}
	/**
	 * 删除所有funpage
	 */
	public boolean deleteAllFunpage() throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		Session session = this.getSessionFactory().openSession();
		
		Query query = session.createQuery("DELETE from Functionpage");
		
		int temp = query.executeUpdate();
		
		session.close();
		
		flag = temp>=0?true:false;
		
		return flag;
	}

}
