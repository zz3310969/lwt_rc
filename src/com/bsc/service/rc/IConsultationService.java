package com.bsc.service.rc;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.bean.Systemuser;

public interface IConsultationService {
	/**
	 * 查询分页会诊记录
	 * @param cp
	 * @param ls
	 * @return
	 */
	public List<Consultation> findAll(int cp,int ls);
	/**
	 * 查询一个会诊信息
	 * @param id
	 * @return
	 */
	public Consultation findById(String id);
	/**
	 * 最大记录数
	 * @return
	 */
	public Long getMaxCount();
	/**
	 * 模糊查询
	 * @param cp
	 * @param ls
	 * @param qy
	 * @return
	 */
	public List<Consultation> findAll(int cp,int ls,ConsultationQuery qy);
	/**
	 * 模糊的最大记录数
	 * @param qy
	 * @return
	 */
	public Long getMaxCount(ConsultationQuery qy);
}
