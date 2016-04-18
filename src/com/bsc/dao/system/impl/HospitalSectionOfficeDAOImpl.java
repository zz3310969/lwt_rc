package com.bsc.dao.system.impl;

import java.util.List;
import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.dao.system.IHospitalSectionOfficeDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class HospitalSectionOfficeDAOImpl implements IHospitalSectionOfficeDAO {
	DataAccessUtility utility;

	public boolean insert(Hospitalsectionoffice user) throws Exception {
		return utility.insert(user);
	}

	public boolean delete(Hospitalsectionoffice user) throws Exception {
		return utility.delete(user);
	}

	public boolean update(Hospitalsectionoffice user) throws Exception {
		return utility.update(user);
	}

	public List<Hospitalsectionoffice> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Hospitalsectionoffice getModel(String id) throws Exception {
		return utility.getModel(Hospitalsectionoffice.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}
	public List<Hospitalsectionoffice> findByHospitalOffice(String id) throws Exception{
		
		return this.utility.getHibernateTemplate().find("FROM Hospitalsectionoffice where hospital.id=?",id);
	}
}
