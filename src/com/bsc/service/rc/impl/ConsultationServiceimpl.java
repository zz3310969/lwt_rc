package com.bsc.service.rc.impl;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.ConsultationQuery;
import com.bsc.dao.rc.IConsultationDao;
import com.bsc.service.rc.IConsultationService;

public class ConsultationServiceimpl implements IConsultationService{

	private IConsultationDao consultationDao;
	
	public List<Consultation> findAll(int cp, int ls) {
		// TODO Auto-generated method stub
		
		List<Consultation> list = null;
		
		try {
			list = this.consultationDao.findAll(cp, ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public Consultation findById(String id) {
		// TODO Auto-generated method stub
		
		Consultation con = null;
		
		try {
			con = this.consultationDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	/**
	 * 最大记录数
	 */
	public Long getMaxCount() {
		// TODO Auto-generated method stub
		
		Long count = 0L;
		
		try {
			count = this.consultationDao.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public IConsultationDao getConsultationDao() {
		return consultationDao;
	}

	public void setConsultationDao(IConsultationDao consultationDao) {
		this.consultationDao = consultationDao;
	}

	public List<Consultation> findAll(int cp, int ls, ConsultationQuery qy) {
		// TODO Auto-generated method stub
		List<Consultation> list = null;
		
		try {
			list = this.consultationDao.findAll(cp, ls, qy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public Long getMaxCount(ConsultationQuery qy) {
		// TODO Auto-generated method stub
		
		Long con = 0L;
		
		try {
			con = this.consultationDao.getCount(qy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
