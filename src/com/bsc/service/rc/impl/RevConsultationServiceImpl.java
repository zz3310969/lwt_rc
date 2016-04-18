package com.bsc.service.rc.impl;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.dao.rc.IRevConsultationDao;
import com.bsc.service.rc.IRevConsultationService;

public class RevConsultationServiceImpl implements IRevConsultationService {

	private IRevConsultationDao revConsultationDao;

	public List<Consultation> findAll(int cp, int ls, ConsultationQuery qy) {
		// TODO Auto-generated method stub
		
		List<Consultation> list = null;
		
		try {
			list = this.revConsultationDao.findAll(cp, ls, qy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public Long getMaxCount(ConsultationQuery qy) {
		// TODO Auto-generated method stub
		
		Long g = 0L;
		
		try {
			g = this.revConsultationDao.getMaxCount(qy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return g;
	}

	public Consultation findById(String id) {
		// TODO Auto-generated method stub
		Consultation cn = null;
		
		try {
			cn = this.revConsultationDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cn;
	}


	public IRevConsultationDao getRevConsultationDao() {
		return revConsultationDao;
	}

	public void setRevConsultationDao(IRevConsultationDao revConsultationDao) {
		this.revConsultationDao = revConsultationDao;
	}
}
