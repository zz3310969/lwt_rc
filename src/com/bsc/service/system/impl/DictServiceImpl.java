package com.bsc.service.system.impl;

import java.util.List;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Dictionarytype;
import com.bsc.dao.system.IDict;
import com.bsc.dao.system.impl.DictDAOimpl;
import com.bsc.service.system.IDictService;

public class DictServiceImpl implements IDictService {
	
	private IDict dictdao;
	
	/**
	 * 取得该类型的所有信息
	 */
	public List<Dictionary> getDicts(String typeCode) {
		// TODO Auto-generated method stub
		
		List<Dictionary> list = null;
		
		try {
			list = this.dictdao.findByIds(typeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Dictionary> findAll(int cp, int ls) {
		// TODO Auto-generated method stub
		
		List<Dictionary> list = null;
		
		try {
			list = this.dictdao.findAll(cp, ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public Long getMaxCount() {
		// TODO Auto-generated method stub
		
		try {
			return this.dictdao.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}
	/**
	 * 类别信息列表
	 */
	public List<Dictionarytype> findAllType() {
		// TODO Auto-generated method stub
		List<Dictionarytype> list = null;
		
		try {
			list = this.dictdao.findAllType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 模糊查询
	 */
	public List<Dictionary> findAllkw(String typeName, String kw, int cp, int ls) {
		// TODO Auto-generated method stub
		List<Dictionary> list = null;
		
		try {
			list = this.dictdao.findAllkw(typeName, kw, cp, ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 取出模糊查询全部记录数
	 */
	public Long getKwMaxCount(String typeName, String kw) {
		// TODO Auto-generated method stub
		
		try {
			return this.dictdao.getKwMaxCount(typeName, kw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}
	/**
	 * 删除
	 */
	public boolean onDelete(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			Dictionary dictionary = this.dictdao.findById(id+"");
			
			flag = this.dictdao.onRemove(dictionary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 修改
	 */
	public boolean onUpdate(Dictionary dict) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.dictdao.onUpdate(dict);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 根据ID查询
	 */
	public Dictionary findByid(String id) {
		// TODO Auto-generated method stub
		
		try {
			Dictionary dictionary = this.dictdao.findById(id);
			
			return dictionary;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 增加
	 */
	public boolean onCreate(Dictionary dict) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.dictdao.onCreate(dict);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public IDict getDictdao() {
		return dictdao;
	}

	public void setDictdao(IDict dictdao) {
		this.dictdao = dictdao;
	}
	
}
