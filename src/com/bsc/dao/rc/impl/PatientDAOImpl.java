package com.bsc.dao.rc.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bsc.bean.Patient;
import com.bsc.dao.rc.IPatient;

public class PatientDAOImpl extends HibernateDaoSupport implements IPatient {
	/**
	 * 增加
	 */
	public boolean onCreate(Patient t) throws Exception {
		// TODO Auto-generated method stub

		this.getHibernateTemplate().save(t);

		return true;
	}

	/**
	 * 删除
	 */
	public boolean onRemove(Patient t) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
		return true;
	}

	/**
	 * 更新
	 */
	public boolean onUpdate(Patient t) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
		return true;
	}

	/**
	 * 查询全部
	 */
	public List<Patient> findAll(final int cp, final int ls) throws Exception {
		// TODO Auto-generated method stub

		final String hql = "FROM Patient";

		@SuppressWarnings("unchecked")
		List<Patient> list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub

						Query query = session.createQuery(hql);

						query.setFirstResult((cp - 1) * ls);
						query.setMaxResults(ls);

						return query.list();
					}
				});

		return list;
	}

	/**
	 * 查询一个对象信息
	 */
	public Patient findById(String id) throws Exception {
		// TODO Auto-generated method stub

		String hql = "FROM Patient WHERE id=?";

		List<Patient> patients = this.getHibernateTemplate().find(hql, id);

		if (!patients.isEmpty()) {

			return patients.get(0);
		}

		return null;
	}

	/**
	 * 取得全部记录数
	 */
	public Long getCount() throws Exception {
		// TODO Auto-generated method stub

		List<Long> list = this.getHibernateTemplate().find(
				"SELECT COUNT(id) FROM Patient");

		if (!list.isEmpty()) {

			return list.get(0);
		}

		return 0L;
	}

	public boolean IsNullOrEmpty(String str) {
		if (str == null || "".equals(str))
			return true;
		else
			return false;
	}
	
	public static boolean checkExist(Object[] StrArray,String key)
	{
		for(int i=0;i<StrArray.length;i++)
		{
			if(String.valueOf(StrArray[i]) == key)
				return true;
		}
		return false;
	}
	
	public List<Patient> findAll(int cp, int ls, String kw, String val)
			throws Exception {

		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = null;
		
		String[] p = new String[]{"name"};
		
		Map<String, String> para_map = new HashMap<String, String>();

		if (!IsNullOrEmpty(kw))
			para_map.put("idType", kw);

		if (!IsNullOrEmpty(val))
		{
			if (!IsNullOrEmpty(kw)){
				para_map.put("idNumber", val);
			}else{
				para_map.put("name", val);
			}
		}

		StringBuffer sb = new StringBuffer("FROM Patient ");

		if (para_map != null && !para_map.isEmpty()) {
			sb.append(" WHERE ");

			int index = 0;
			for (Iterator i = para_map.keySet().iterator(); i.hasNext();) {
				
				if(checkExist(p,"name"))
					sb.append(" " + i.next() + " like ? ");
				else
					sb.append(" " + i.next() + " = ? ");

				if (index < para_map.size()-1)
					sb.append(" AND ");
				
				index++;
			}
			query = session.createQuery(sb.toString());
			int vindex = 0;
			for (Iterator i = para_map.keySet().iterator(); i.hasNext();) {
				Object key = i.next();
				Object value = para_map.get(key);
				
				if(checkExist(p,String.valueOf(key)))
					query.setString(vindex++, "%"+ String.valueOf(value) +"%"); 
				else
					query.setString(vindex++, String.valueOf(value));
				
				
			}
		}else{
			query = session.createQuery(sb.toString());
		}
		List<Patient> list = query.list();
		
		session.close();
		
		return list;
	}


	/**
	 * 模糊查询全部记录数
	 */
	public Long getCount(String kw, String val) throws Exception {

		List<Long> list = null;
		StringBuffer sb = new StringBuffer("SELECT COUNT(id) FROM Patient ");

		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = null;
		
		Map<String, String> para_map = new HashMap<String, String>();
		
		String[] p = new String[]{"name"};
		
		if (!IsNullOrEmpty(kw))
			para_map.put("idType", kw);

		if (!IsNullOrEmpty(val))
		{
			if (!IsNullOrEmpty(kw)){
				para_map.put("idNumber", val);
			}else{
				para_map.put("name", val);
			}
		}

		if (para_map != null && !para_map.isEmpty()) {
			sb.append(" WHERE ");

			int index = 0;
			for (Iterator i = para_map.keySet().iterator(); i.hasNext();) {
				
				sb.append(" " + i.next() + " = ? ");

				if (++index < para_map.size()-1)
					sb.append(" AND ");
				
				index++;
			}
			query = session.createQuery(sb.toString());
			int vindex = 0;
			for (Iterator i = para_map.keySet().iterator(); i.hasNext();) {
				Object value = para_map.get(i.next());
				query.setString(vindex++, value.toString());
			}
		}else{
			query = session.createQuery(sb.toString());
		}
		list = query.list();
		
		if (list != null && !list.isEmpty()) {

			return list.get(0);
		}

		return 0L;
	}
	/**
	 * 证件类型和id，是否存在此用户
	 */
	public Patient findTypeIDUser(String id, String type) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "FROM Patient WHERE idType=? AND idNumber = ?";
		
		List<Patient> list = this.getHibernateTemplate().find(hql,new Object[]{type,id});
		
		if(!list.isEmpty()){
			
			return list.get(0);
		}
		
		return null;
	}
}
