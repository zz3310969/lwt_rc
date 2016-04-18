package com.bsc.dao.rc;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;

public interface IRevConsultationDao {
	/**
	 * 查询
	 * @param cp
	 * @param ls
	 * @param qy
	 * @return
	 * @throws Exception
	 */
	public List<Consultation> findAll(int cp,int ls,ConsultationQuery qy) throws Exception;
	/**
	 * 最大记录数
	 * @param qy
	 * @return
	 * @throws Exception
	 */
	public Long getMaxCount(ConsultationQuery qy) throws Exception;
	/**
	 * 查询一条记录信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Consultation findById(String id) throws Exception;
	
}
