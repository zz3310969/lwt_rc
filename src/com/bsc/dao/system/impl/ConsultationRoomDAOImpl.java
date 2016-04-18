package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Consultationroom;
import com.bsc.dao.system.IConsultationRoomDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class ConsultationRoomDAOImpl implements IConsultationRoomDAO {
	DataAccessUtility utility;

	public boolean insert(Consultationroom room) throws Exception {
		return utility.insert(room);
	}

	public boolean delete(Consultationroom room) throws Exception {
		return utility.delete(room);
	}

	public boolean update(Consultationroom room) throws Exception {
		return utility.update(room);
	}

	public List<Consultationroom> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Consultationroom getModel(String id) throws Exception {
		return utility.getModel(Consultationroom.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}
	
	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}
