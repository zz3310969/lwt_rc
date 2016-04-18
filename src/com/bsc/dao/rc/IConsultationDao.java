package com.bsc.dao.rc;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.dao.IDAO;

public interface IConsultationDao extends IDAO<Consultation> {
	/**
	 * 模糊查询
	 * @param cp
	 * @param ls
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<Consultation> findAll(int cp,int ls,ConsultationQuery qy) throws Exception;
	/**
	 * 模糊查询全部记录数
	 * @param qy
	 * @return
	 * @throws Exception
	 */
	public Long getCount(ConsultationQuery qy) throws Exception;
}
