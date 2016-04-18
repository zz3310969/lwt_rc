package com.bsc.service.rc.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Consultation;
import com.bsc.bean.Patient;
import com.bsc.dao.rc.IConsultationDao;
import com.bsc.dao.rc.IPatient;
import com.bsc.service.rc.IPatientService;
/**
 * 病人信息服务层实现
 * @author 韩进城
 *
 */
public class PatientServiceImpl implements IPatientService {

	private IPatient patientdao;
	private IConsultationDao consultationDao;
	
	/**
	 * 增加病人信息
	 */
	public boolean onCreatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			patient.setId(UUID.randomUUID().toString());
			patient.setAddTime(new Date());
			flag = this.patientdao.onCreate(patient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 增加会诊信息
	 */
	public boolean onCreateConsult(Consultation con) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.consultationDao.onCreate(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 查询分页病人信息
	 */
	public List<Patient> findAll(int cp, int ls) {
		// TODO Auto-generated method stub
		
		List<Patient> list = null;
		 
		try {
			list = this.patientdao.findAll(cp, ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 取得全部记录数
	 */
	public Long getCount() {
		// TODO Auto-generated method stub
		
		try {
			Long ongLong = this.patientdao.getCount();
			
			return ongLong;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}
	/**
	 * 模糊查询分页
	 */
	public List<Patient> findAll(int cp, int ls, String kw,String val) {
		// TODO Auto-generated method stub
		
		List<Patient> list = null;
		
		try {
			list = this.patientdao.findAll(cp, ls, kw,val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 模糊查询全部记录数
	 */
	public Long getCount(String kw,String val) {
		// TODO Auto-generated method stub
		
		try {
			Long ongLong = this.patientdao.getCount(kw,val);
			
			return ongLong;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}
	/**
	 * 查询一条记录数
	 */
	public Patient findById(String id) {
		// TODO Auto-generated method stub
		Patient patient = null;
		
		try {
			patient = this.patientdao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	}
	/**
	 * 修改记录数
	 */
	public boolean onUpdatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.patientdao.onUpdate(patient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 删除病人信息
	 */
	public boolean onDeletePatient(Patient patient) {
		// TODO Auto-generated method stub
		
		boolean flag = false; 
		
		try {
			flag = this.patientdao.onRemove(patient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * id和证件类型
	 */
	public Patient findTypeAndIDPatient(String id, String type) {
		// TODO Auto-generated method stub
		
		Patient patient = null;
		
		try {
			patient = this.patientdao.findTypeIDUser(id, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	}
	
	//_start
	public IPatient getPatientdao() {
		return patientdao;
	}

	public IConsultationDao getConsultationDao() {
		return consultationDao;
	}

	public void setConsultationDao(IConsultationDao consultationDao) {
		this.consultationDao = consultationDao;
	}

	public void setPatientdao(IPatient patientdao) {
		this.patientdao = patientdao;
	}
	//_end
	
	
}
