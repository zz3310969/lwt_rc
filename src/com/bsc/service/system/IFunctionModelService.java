package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;

/**
 * 模块管理Service层
 * @author 韩进城
 *
 */
public interface IFunctionModelService {
	/**
	 * 取得全部
	 * @return
	 */
	public List<Functionmodule> findAll(int cp,int ls) ;
	/**
	 * 分页模糊查询
	 * @param cp
	 * @param ls
	 * @param kw
	 * @return
	 */
	public List<Functionmodule> findAll(int cp,int ls,String kw) ;
	
	/**
	 * 取得全部记录数
	 * @return
	 */
	public Long getCount(String kw);
	/**
	 * 保存funpage信息
	 * @param funs
	 * @return
	 */
	public boolean onCreateFunPage(List<Functionpage> funs);
	/**
	 * 删除functionpage
	 * @param id
	 * @return
	 */
	public boolean onDeleteFunpage(Functionpage page);
	
	/**
	 * 获得一个对象Mode
	 * @param modeid
	 * @return
	 */
	public Functionmodule findById(String modeid);
	/**
	 * 删除funmode
	 * @param fun
	 * @return
	 */
	public boolean onRemoveMode(Functionmodule fun);
	
	/**
	 * 增加mode
	 * @param mode
	 * @return
	 */
	public boolean onCreateMode(Functionmodule mode);
	/**
	 * 修改mode
	 * @param mode
	 * @return
	 */
	public boolean onUpdateMode(Functionmodule mode);
	
	/**
	 * 查询当前模块的上级模块
	 * @param modeleve
	 * @return
	 */
	public List<Functionmodule> findParentMode(String modeleve);
	/**
	 * 查询FuncationPage对象
	 * @param funpageid
	 * @return
	 */
	public Functionpage findByIdFunPage(FunctionpageId funpageid);
	/**
	 * 查询当前模块是否有子模块
	 * @param modeid
	 * @return
	 */
	public List<Functionmodule> findSunMode(String modeid);
	/**
	 * 查询当前funpage中是否有上层引用
	 * @param pageid
	 * @return
	 */
	public Functionpage findFunpageById(String pageid);
}
