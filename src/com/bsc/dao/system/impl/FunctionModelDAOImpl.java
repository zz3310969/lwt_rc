package com.bsc.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;
import com.bsc.dao.system.IFunctionModelDAO;

public class FunctionModelDAOImpl extends HibernateDaoSupport implements IFunctionModelDAO {
	/**
	 * 增加
	 */
	public boolean onCreate(Functionmodule t) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().save(t);
		
		return true;
	}
	/**
	 * 删除
	 */
	public boolean onRemove(Functionmodule t) throws Exception {
		
		this.getHibernateTemplate().delete(t);
		
		return true;
	}
	/**
	 * 修改
	 */
	public boolean onUpdate(Functionmodule t) throws Exception {
		
		this.getHibernateTemplate().update(t);
		
		return true;
	}
	/**
	 * 查询所有分页
	 */
	@SuppressWarnings("unchecked")
	public List<Functionmodule> findAll(final int cp,final int ls) throws Exception {
		// TODO Auto-generated method stub
		final String hql = "From Functionmodule ORDER BY moduleLevel";
		
		List<Functionmodule> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				
				Query query = session.createQuery(hql);
				
				query.setFirstResult((cp-1)*ls);
				
				query.setMaxResults(ls);
				
				return query.list();
				
			}
		});
		
	/*	Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria crit = session.createCriteria(Functionmodule.class);
		
		crit.setFirstResult((cp-1)*ls);
		crit.setMaxResults(ls);
		
		List<Functionmodule> list = crit.list();
		
		session.close();*/
		
		return list;
	}
	/**
	 * 查询一个
	 */
	public Functionmodule findById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "SELECT new Functionmodule(F.moduleId,F.moduleName) From Functionmodule F WHERE F.moduleId=?";
		
		List<Functionmodule> list = this.getHibernateTemplate().find(hql, id);
		
		if(!list.isEmpty()){
			
			for(Functionmodule ss:list){
				
				Hibernate.initialize(ss);
			}
			
			return list.get(0);
		}
		
		return null;
	}
	/**
	 * 获得全部记录数
	 */
	public Long getCount() throws Exception {
		
		String hql = "SELECT COUNT(moduleId) FROM Functionmodule" ;
		
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(!list.isEmpty()){
			
			return list.get(0);
			
		}
		
		return 0L;
	}
	/**
	 * 保存funpage信息
	 */
	public boolean onCreateFunctionPage(Functionpage funpage) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().saveOrUpdate(funpage);
		
		return true;
	}
	/**
	 * 删除funpage信息
	 */
	public boolean onDelete(Functionpage funpage) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().delete(funpage);
		
		return true;
	}
	/**
	 * 查询一个funpage
	 */
	public Functionpage findById(FunctionpageId id) throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<Functionpage> list = this.getHibernateTemplate().find("FROM Functionpage WHERE id.functionModuleId = ? " +
				"and id.webPageId = ?",new Object[]{id.getFunctionModuleId(),id.getWebPageId()});
		
				if(!list.isEmpty()){
					
					return list.get(0);
				}
				
		return  null;
	}
	/**
	 * 根据当前的模块查询上级模块信息
	 */
	public List<Functionmodule> findParentMode(String modeleve) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "SELECT new Functionmodule(F.moduleId,F.moduleName) FROM Functionmodule F WHERE F.moduleLevel = '"+modeleve+"'";
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Query query = session.createQuery(hql);
		
		//query.setString(0, modeleve);
		
		//query.setResultTransformer(Transformers.aliasToBean(Functionmodule.class));
		
		List<Functionmodule> list = query.list();
		
		session.close();
		
		return list;
	}
	
	/**
	 * 查询当前模块是否有子模块
	 */
	public List<Functionmodule> findSunMode(String modeid)
			throws Exception {
	
		String hql = "SELECT new Functionmodule(F.moduleId,F.moduleName) FROM Functionmodule F WHERE F.parentModuleId = '"+modeid+"'";
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Query query = session.createQuery(hql);
		
		List<Functionmodule> list = query.list();
		
		session.close();
		
		
		
		return list;
	}
	/**
	 * 模糊查询
	 */
	public List<Functionmodule> findAll(final int cp,final int ls,final String kw)
			throws Exception {
		// TODO Auto-generated method stub
		
		final String hql = "SELECT new Functionmodule(moduleId,moduleName) From Functionmodule WHERE moduleName like ? ORDER BY moduleLevel";
		
		@SuppressWarnings("unchecked")
		List<Functionmodule> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
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
	 * 查询模糊查询的全部记录数
	 */
	public Long getCont(String kw) throws Exception {
		// TODO Auto-generated method stub

		String hql = "SELECT COUNT(moduleId) From Functionmodule WHERE moduleName like ? ORDER BY moduleLevel";

		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql,
				new Object[] { "%" + kw + "%" });

		if (!list.isEmpty()) {

			return list.get(0);

		}

		return 0L;

	}
	
	public Functionpage findFunpageById(String pageid) throws Exception {
		// TODO Auto-generated method stub
		
		List<Functionpage> feFunctionpage = this.getHibernateTemplate().find("FROM Functionpage WHERE id.webPageId = ?",pageid);
		
		if(!feFunctionpage.isEmpty()){
			
			return feFunctionpage.get(0);
		}
		
		return null;
	}
	public Functionmodule findById(String id, String flag)
			throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "From Functionmodule F WHERE F.moduleId=?";
		
		List<Functionmodule> list = this.getHibernateTemplate().find(hql, id);
		
		if(!list.isEmpty()){
			
			for(Functionmodule ss:list){
				
				Hibernate.initialize(ss);
			}
			
			return list.get(0);
		}
		
		return null;
	}
}
