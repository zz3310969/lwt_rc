package com.bsc.dao.system.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.dao.system.IHospitalDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class HospitalDAOImpl implements IHospitalDAO {
	DataAccessUtility utility;

	public boolean insert(Hospital hospital) throws Exception {
		return utility.insert(hospital);
	}

	public boolean delete(Hospital hospital) throws Exception {
		return utility.delete(hospital);
	}

	public boolean update(Hospital hospital) throws Exception {
		return utility.update(hospital);
	}

	public List<Hospital> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Hospital getModel(String id) throws Exception {
		return utility.getModel(Hospital.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public List<Hospitalrelation> findAllNotThen(String id) throws Exception {
		// TODO Auto-generated method stub
		
		HibernateTemplate template = this.utility.getHibernateTemplate();
		
		String hql = "FROM Hospitalrelation  where id.callerId=?";
		
		List<Hospitalrelation> list = template.find(hql,new Object[]{id});
		
		return list;
	}
	
	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}
