package com.bsc.service.system.impl;

import java.util.List;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;
import com.bsc.dao.system.IFunctionModelDAO;
import com.bsc.dao.system.impl.FunctionModelDAOImpl;
import com.bsc.service.system.IFunctionModelService;

public class FunctionModeServiceImpl implements IFunctionModelService {

	private IFunctionModelDAO functionMode; // 注入dao层模块管理

	/**
	 * 取得全部记录数
	 */
	public List<Functionmodule> findAll(int cp, int ls) {
		// TODO Auto-generated method stub
		List<Functionmodule> list = null;
		try {
			list = this.functionMode.findAll(cp, ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 取得全部记录数
	 */
	public Long getCount(String kw) {
		// TODO Auto-generated method stub
		Long ll = 0L;

		if (kw != null && !"".equals(kw)) {

			try {
				ll = this.functionMode.getCont(kw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				ll = this.functionMode.getCount();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ll;
	}
	/**
	 * 保存funpage信息
	 */
	public boolean onCreateFunPage(List<Functionpage> funs) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		if(!funs.isEmpty()){
			
			for(Functionpage fn:funs){
				
				try {
					flag = this.functionMode.onCreateFunctionPage(fn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		return flag;
	}
	
	/**
	 * 取得一个对象信息
	 */
	public Functionmodule findById(String modeid) {
		// TODO Auto-generated method stub
		
		try {
			Functionmodule fun = this.functionMode.findById(modeid,"");
			
			return fun;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 删除funpage
	 */
	public boolean onDeleteFunpage(Functionpage page) {
		// TODO Auto-generated method stub
		
		try {
			
			return this.functionMode.onDelete(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 删除funmode
	 */
	public boolean onRemoveMode(Functionmodule fun) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = this.functionMode.onRemove(fun);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 增加mode
	 */
	public boolean onCreateMode(Functionmodule mode) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.functionMode.onCreate(mode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 修改mode
	 */
	public boolean onUpdateMode(Functionmodule mode) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.functionMode.onUpdate(mode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 查询当前模块的上级模块
	 */
	public List<Functionmodule> findParentMode(String modeleve) {
		// TODO Auto-generated method stub
		
		try {
			List<Functionmodule> list = this.functionMode.findParentMode(modeleve);
			
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 取得Functionpage对象
	 */
	public Functionpage findByIdFunPage(FunctionpageId funpageid) {
		// TODO Auto-generated method stub
		
		Functionpage fpage = null;
		
		try {
			fpage = this.functionMode.findById(funpageid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fpage;
	}
	/**
	 * 查询当前模块是否有子模块
	 */
	public List<Functionmodule> findSunMode(String modeid) {
		// TODO Auto-generated method stub
		
		List<Functionmodule> list = null;
		
		try {
			list = this.functionMode.findSunMode(modeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 分页模糊查询
	 */
	public List<Functionmodule> findAll(int cp, int ls, String kw) {
		// TODO Auto-generated method stub
		
		List<Functionmodule> list = null;
		
		if(kw !=null && !"".equals(kw)){
			
			try {
				list = this.functionMode.findAll(cp, ls, kw);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			try {
				list = this.functionMode.findAll(cp, ls);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
	/**
	 * 查询是否有上层引用
	 */
	public Functionpage findFunpageById(String pageid) {
		// TODO Auto-generated method stub
		Functionpage fn = null;
		
		try {
			fn = this.functionMode.findFunpageById(pageid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fn;
	}
	
	public IFunctionModelDAO getFunctionMode() {
		return functionMode;
	}

	public void setFunctionMode(IFunctionModelDAO functionMode) {
		this.functionMode = functionMode;
	}

}
