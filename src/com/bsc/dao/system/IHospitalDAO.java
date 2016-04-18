package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalDAO {

	public boolean insert(Hospital hospital) throws Exception;

	public boolean delete(Hospital hospital) throws Exception;

	public boolean update(Hospital hospital) throws Exception;

	public Hospital getModel(String hospitalId) throws Exception;

	public List<Hospital> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

	public List<Hospitalrelation> findAllNotThen(String id) throws Exception;
}
