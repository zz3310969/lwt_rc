package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.HospitalrelationId;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalRelationDAO {

	public boolean insert(Hospitalrelation relation) throws Exception;

	public boolean delete(HospitalrelationId relationId) throws Exception;

	public boolean update(Hospitalrelation relation) throws Exception;

	public Hospitalrelation getModel(HospitalrelationId relationId)
			throws Exception;

	public List<Hospitalrelation> getList(QueryTerms queryTerms)
			throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}
