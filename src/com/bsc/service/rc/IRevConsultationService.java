package com.bsc.service.rc;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;

public interface IRevConsultationService {
	/**
	 * 查询
	 * @param cp
	 * @param ls
	 * @param qy
	 * @return
	 */
	public List<Consultation> findAll(int cp,int ls,ConsultationQuery qy);
	/**
	 * 最大记录数
	 * @param qy
	 * @return
	 */
	public Long getMaxCount(ConsultationQuery qy);
	/**
	 * 一个对象信息
	 * @param id
	 * @return
	 */
	public Consultation findById(String id);
}
