package com.bsc.dao.rc;

import java.util.List;

import com.bsc.bean.Patient;
import com.bsc.dao.IDAO;
/**
 * 病人信息管理
 * @author 韩进城
 *
 */
public interface IPatient extends IDAO<Patient> {
	/**
	 * 查询分页带模糊查询
	 * @param cp
	 * @param ls
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	List<Patient> findAll(int cp,int ls,String kw,String val) throws Exception;
	/**
	 * 模糊查询全部记录数
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	public Long getCount(String kw,String val) throws Exception;
	/**
	 * 查询当前此证件类型和id用户的信息
	 * @param id 编号
	 * @param type 证件类型
	 * @return
	 * @throws Exception
	 */
	public Patient findTypeIDUser(String id,String type) throws Exception;
}
