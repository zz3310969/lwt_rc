package com.bsc.dao.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bsc.bean.Systemuser;
import com.bsc.dao.rc.impl.PatientDAOImpl;
import com.bsc.dao.system.ISystemUserDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class SystemUserDAOImpl implements ISystemUserDAO {
	DataAccessUtility utility;

	public boolean insert(Systemuser user) throws Exception {
		return utility.insert(user);
	}

	public boolean delete(Systemuser user) throws Exception {
		return utility.delete(user);
	}

	public boolean update(Systemuser user) throws Exception {
		return utility.update(user);
	}

	public List<Systemuser> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Systemuser getModel(String id) throws Exception {
		return utility.getModel(Systemuser.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public List<Systemuser> findHospOfficeUsers(String hospid, String officeid,
			String name) throws Exception {
		// TODO Auto-generated method stub
		
		HibernateTemplate hibernateTemplate = this.utility.getHibernateTemplate();
		
		StringBuffer sb = new StringBuffer();
		
		Map<String,String> map = new HashMap<String,String>();
		
		String sss[] = new String []{"realName"};
		
		sb.append("FROM Systemuser ");
		
		if(hospid != null && !"".equals(hospid)){
			
			map.put("hospital.id", hospid);
		}
		
		if(officeid!=null && !"".equals(officeid)){
			map.put("hospitalSectionOfficeId", officeid);
		}
		if(name != null && !"".equals(name)){
			map.put("realName", name);
		}
		
		if(!map.isEmpty()){
			
			sb.append(" WHERE ");
			
			int temp = 0;
			
			for(java.util.Iterator<String> i = map.keySet().iterator(); i.hasNext();){
				
				String key = i.next();
				
				if (temp>0 && temp <map.size())
					sb.append(" AND ");
				
				if(PatientDAOImpl.checkExist(sss, key)){
					
					sb.append(key).append(" like ? ");
					
				}else{
					
					sb.append(key).append(" = ? ");
				}
				
				temp++;
			}
		}
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		
		Query query = session.createQuery(sb.toString());
		
		int vindex = 0;
		for (java.util.Iterator<String> i =  map.keySet().iterator(); i.hasNext();) {
			
			String value = map.get(i.next());
			
			if(PatientDAOImpl.checkExist(sss,value))
				query.setString(vindex++, "%"+ value +"%"); 
			else
				query.setString(vindex++, value);
		}
		
		List<Systemuser> list = query.list();
		
		session.close();
		
		return list;
	}
	
	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}
