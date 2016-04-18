package com.bsc.service.system;

import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.HospitalrelationId;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalRelationService {

	public ResultModel insert(Hospitalrelation relation);

	public ResultModel delete(HospitalrelationId relationId);

	public ResultModel getHospitalRelationList(QueryTerms queryTerms);

}
