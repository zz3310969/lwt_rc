package com.bsc.service.system.impl;

import java.util.List;

import com.bsc.bean.Nation;
import com.bsc.dao.system.INationDAO;
import com.bsc.service.system.INationService;

public class NationServiceImpl implements INationService {

	private INationDAO nationdao;
	

	public List<Nation> findAll() {
		// TODO Auto-generated method stub
		
		try {
			List<Nation> list = this.nationdao.findAll();
			
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Nation findByid(int id) {
		// TODO Auto-generated method stub
		
		Nation nation = null;
		
		try {
			nation = this.nationdao.findById(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nation;
	}

	
	public INationDAO getNationdao() {
		return nationdao;
	}

	public void setNationdao(INationDAO nationdao) {
		this.nationdao = nationdao;
	}
}
