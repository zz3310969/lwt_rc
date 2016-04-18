package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalSectionOfficeDAO {

	public boolean insert(Hospitalsectionoffice sectionOffice) throws Exception;

	public boolean delete(Hospitalsectionoffice sectionOffice) throws Exception;

	public boolean update(Hospitalsectionoffice sectionOffice) throws Exception;

	public Hospitalsectionoffice getModel(String sectionOffice)
			throws Exception;

	public List<Hospitalsectionoffice> getList(QueryTerms queryTerms)
			throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

	public List<Hospitalsectionoffice> findByHospitalOffice(String id) throws Exception;
}
