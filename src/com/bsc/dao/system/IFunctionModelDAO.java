package com.bsc.dao.system;


import java.util.List;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;
import com.bsc.dao.IDAO;
/**
 * 模块管理dao接口
 * @author 韩进城
 *
 */
public interface IFunctionModelDAO extends IDAO<Functionmodule>{
	/**
	 * 保存funpage信息
	 * @param funpage
	 * @return
	 * @throws Exception
	 */
	public boolean onCreateFunctionPage(Functionpage funpage) throws Exception;
	
	/**
	 * 删除funpage信息
	 * @param funpage
	 * @return
	 * @throws Exception
	 */
	public boolean onDelete(Functionpage funpage) throws Exception;
	/**
	 * 查询一个funcationpage
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Functionpage findById(FunctionpageId id) throws Exception;
	
	/**
	 * 查询当前模块的上层模块
	 * @param modeid
	 * @return
	 * @throws Exception
	 */
	public List<Functionmodule> findParentMode(String modeleve) throws Exception;
	/**
	 * 查询当前模块是否有子模块
	 * @param functionmodule
	 * @return
	 * @throws Exception
	 */
	public List<Functionmodule> findSunMode(String modeid) throws Exception;
	/**
	 * 分页和模糊查询
	 * @param cp 当前页
	 * @param ls 每页显示条数
	 * @param kw 模糊查询关键字
	 * @return
	 * @throws Exception
	 */
	public List<Functionmodule> findAll(int cp,int ls,String kw) throws Exception;
	/**
	 * 模糊查询的取出全部记录数
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	public Long getCont(String kw) throws Exception;
	/**
	 * 根据funpageid查询是否有上层引用
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	public Functionpage findFunpageById(String pageid) throws Exception;
	/**
	 * flag 没用
	 * @param id
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public Functionmodule findById(String id,String flag) throws Exception;
}
