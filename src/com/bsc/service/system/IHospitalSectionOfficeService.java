package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IHospitalSectionOfficeService {

	public ResultModel insert(Hospitalsectionoffice sectionOffice);

	public ResultModel delete(String hospitalId);

	public ResultModel update(Hospitalsectionoffice sectionOffice);

	public ResultModel getHospitalSectionOfficeById(String sectionOfficeId);

	public ResultModel getHospitalSectionOfficeList(QueryTerms queryTerms);

	public List<Hospitalsectionoffice> findByHospitalOffice(String id);
}
