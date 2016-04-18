package com.bsc.service.rc;

import java.util.List;

import com.bsc.bean.Consultation;
import com.bsc.bean.Patient;

/**
 * 病人管理服务
 * @author 韩进城
 *
 */
public interface IPatientService {
	/**
	 * 添加病人信息
	 * @param patient
	 * @return
	 */
	public boolean onCreatePatient(Patient patient);
	/**
	 * 添加会诊信息
	 * @param con
	 * @return
	 */
	public boolean onCreateConsult(Consultation con);
	/**
	 * 分页查询
	 * @param cp
	 * @param ls
	 * @return
	 */
	public List<Patient> findAll(int cp,int ls);
	/**
	 * 取得全部记录数
	 * @return
	 */
	public Long getCount();
	
	/**
	 * 分页查询
	 * @param cp当前页
	 * @param ls 每页显示记录数
	 * @param kw 模糊关键字
	 * @return
	 */
	List<Patient> findAll(int cp,int ls,String kw,String val);
	/**
	 * 模糊查询记录数
	 * @param kw
	 * @return
	 */
	public Long getCount(String kw,String val);
	/**
	 * 查询一个记录数
	 * @param id
	 * @return
	 */
	public Patient findById(String id);
	/**
	 * 修改
	 * @param patient
	 * @return
	 */
	public boolean onUpdatePatient(Patient patient);
	/**
	 * 删除
	 * @param patient
	 * @return
	 */
	public boolean onDeletePatient(Patient patient);
	/**
	 * 当前id和证件类型，是否存在此用户
	 * @param id
	 * @param type
	 * @return
	 */
	public Patient findTypeAndIDPatient(String id,String type);
}
