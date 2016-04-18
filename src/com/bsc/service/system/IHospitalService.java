package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalService {

	public ResultModel insert(Hospital hospital);

	public ResultModel delete(String hospitalId);

	public ResultModel update(Hospital hospital);

	public ResultModel getHospitalById(String hospitalId);

	public ResultModel getHospitalList(QueryTerms queryTerms);

	public List<Hospitalrelation> findAllNotThen(String hospid);
	
}
