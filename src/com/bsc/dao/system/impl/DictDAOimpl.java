package com.bsc.dao.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Dictionarytype;
import com.bsc.dao.system.IDict;

public class DictDAOimpl extends HibernateDaoSupport implements IDict {
	
	public boolean onCreate(Dictionary t) throws Exception {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().save(t);
		
		return true;
	}

	public boolean onRemove(Dictionary t) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
		return true;
	}

	public boolean onUpdate(Dictionary t) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
		return true;
	}

	public List<Dictionary> findAll(final int cp,final int ls) throws Exception {
		// TODO Auto-generated method stub
		
		final String hql ="FROM Dictionary";
		
		@SuppressWarnings("unchecked")
		List<Dictionary> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				
				Query query = session.createQuery(hql);
				
				query.setFirstResult((cp-1)*ls);
				query.setMaxResults(ls);
				
				return query.list();
			}
		});
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public Dictionary findById(String id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "FROM Dictionary WHERE id = ?";
		
		List<Dictionary> list = null;
		
		int temp = Integer.parseInt(id);
		
		list = this.getHibernateTemplate().find(hql,temp);
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return null;
	}

	public Long getCount() throws Exception {
		// TODO Auto-generated method stub
		
		String hql ="SELECT COUNT(id) FROM Dictionary";
		
		List<Long> list = this.getHibernateTemplate().find(hql);
		
		if(!list.isEmpty()){
			
			return list.get(0);
			
		}
		
		return 0L;
	}
	/**
	 * 取得该type的所有信息
	 */
	public List<Dictionary> findByIds(String id) throws Exception {
		// TODO Auto-generated method stub
		
		List<Dictionary> list = this.getHibernateTemplate().find("From Dictionary where dictionarytype.id = ?", id);
		
		return list;
	}
	/**
	 * 查询所有类别信息
	 */
	public List<Dictionarytype> findAllType() throws Exception {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().find("FROM Dictionarytype");
	}
	/**
	 * 模糊查询
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> findAllkw(String typeName,String kw,int cp,int ls)
			throws Exception {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("FROM Dictionary ");
		
		Map<String,String> map = new HashMap<String,String>();
		String temp ="";
		if(typeName !=null && !"".equals(typeName)){
			
			map.put(typeName," dictionarytype.id=?");
			temp =" AND ";
		}
		if(kw != null && !"".equals(kw)){
			map.put(kw,temp+" name like ?");
		}
		
		if(map.size()>0){
			sql.append("WHERE ");
			
			if(map.containsKey(typeName)){
				
				sql.append(map.get(typeName));
			}
			if(map.containsKey(kw)){
				sql.append(map.get(kw));
			}
		}
		
		List<Dictionary> list = null;
		
		Session session = this.getSessionFactory().openSession();
		
		Query query = session.createQuery(sql.toString());
		
		if(map.size()>0){
			int count =0;
			if(map.containsKey(typeName)){
				query.setString(count,typeName);
				count++;
			}
			if(map.containsKey(kw)){
				query.setString(count,"%"+kw+"%");
			}
		}
		query.setFirstResult((cp-1)*ls);
		query.setMaxResults(ls);
		list = query.list();
		
		session.close();
		
		return list;
	}
	/**
	 * 模糊查询全部记录数
	 */
	public Long getKwMaxCount(String typeName, String kw)
			throws Exception {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT COUNT(id) FROM Dictionary ");
		
		Map<String,String> map = new HashMap<String,String>();
		String temp ="";
		if(typeName !=null && !"".equals(typeName)){
			map.put(typeName," dictionarytype.id=?");
			temp =" AND ";
		}
		if(kw != null && !"".equals(kw)){
			map.put(kw,temp+" name like ?");
		}
		
		if(map.size()>0){
			sql.append("WHERE ");
			
			if(map.containsKey(typeName)){
				
				sql.append(map.get(typeName));
			}
			if(map.containsKey(kw)){
				sql.append(map.get(kw));
			}
		}
		
		List<Long> list = null;
		
		Session session = this.getSessionFactory().openSession();
		
		Query query = session.createQuery(sql.toString());
		
		if(map.size()>0){
			int count =0;
			if(map.containsKey(typeName)){
				query.setString(count,typeName);
				count++;
			}
			if(map.containsKey(kw)){
				query.setString(count,"%"+kw+"%");
			}
		}
		list =query.list();
		
		session.close();
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return 0L;
	}

}
