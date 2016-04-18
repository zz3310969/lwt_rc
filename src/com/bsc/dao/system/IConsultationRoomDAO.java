package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Consultationroom;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IConsultationRoomDAO {

	public boolean insert(Consultationroom room) throws Exception;

	public boolean delete(Consultationroom room) throws Exception;

	public boolean update(Consultationroom room) throws Exception;

	public Consultationroom getModel(String roomId) throws Exception;

	public List<Consultationroom> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}
